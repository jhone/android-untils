package com.redsun.platf.web.framework;

import java.beans.PropertyEditorSupport;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.Assert;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.LastModified;
import org.springframework.web.servlet.mvc.multiaction.InternalPathMethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import com.redsun.platf.dao.interceptor.RequestThreadResourceManager;
import com.redsun.platf.entity.sys.UserAccount;

/**
 * <p>Title: com.walsin.platf.web.framework.ActionHandleController</p>
 * <p>Description: Multi Method Reflection 交易控制項抽象父類別</p>
 * <p>Copyright: Copyright (c) IBM Corp. 2008. All Rights Reserved.</p>
 * <p>Company: FreeLance</p>
 * @author Jason Huang
 * @version 1.0
 */
public abstract class ActionHandleController extends LoggingSupportController implements LastModified, BeanNameAware {
		
    /** Controller頁面顯示之Map */
	private Map<String, String> viewMap = new HashMap<String, String>();
	
	private MethodNameResolver methodNameResolver = new InternalPathMethodNameResolver();

    /** Suffix for last-modified methods */
    public static final String LAST_MODIFIED_METHOD_SUFFIX = "LastModified";

    /** Default command name used for binding command objects: "command" */
    public static final String DEFAULT_COMMAND_NAME = "command";
    
    /** Methods, keyed by name */
    private Map<String, Method> handlerMethodMap = new HashMap<String, Method>();

    /** LastModified methods, keyed by handler method name (without LAST_MODIFIED_SUFFIX) */
    private Map<String, Method> lastModifiedMethodMap = new HashMap<String, Method>();

    /** Object we'll invoke methods on. Defaults to this. */
    private Object delegate;
    
    private String beanName;
    
    // private static Pattern paramPattern = Pattern.compile("[0-9a-zA-z]*");
    
    private static final String DEFAULT_EXCEPTION_VEIW_KEY = "exception";
    public static final String DEFAULT_EXCEPTION_VIEW_NAME = "/exception";
    public static final String EXCEPTION_CODE = "exception.code";
    public static final String EXCEPTION_DESC = "exception.desc"; 
    
    /**
     * 建構子
     */
    public ActionHandleController() {
        this.delegate = this;
        registerHandlerMethods(this.delegate);
    }
    
    /**
     * 建構子
     * @param delegate 代理物件
     */
    public ActionHandleController(Object delegate) {
        setDelegate(delegate);
    }
    
    @Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    Map<String, Object> model = new HashMap<String, Object>();
	    try{
	        // 編碼檢核
	        // encodeingCheck(request, model);
	        
	        // 取得Method Name	        
	        String methodName = methodNameResolver.getHandlerMethodName(request);
	        
	        // 執行Method,返回View Name
	        String viewName = invokeNamedMethod(methodName, request, response, model);
	        
	        // 已於Method回應
	        if(viewName == null) return null;
	        
	        // 取得實際對應之View Name
	        String realViewName = handleViewName(viewName);
	        
	        // 回傳處理結果給 Dispatcher
	        return new ModelAndView(realViewName, model);
	        
	    } catch (NoSuchMethodException nsmex) {
            throw nsmex;
        } catch (InvocationTargetException itex) {
            return handleException(request, response, model, itex.getTargetException());            
        } catch (Exception ex) {
            return handleException(request, response, model, ex);
        } finally{
            doFinally(request);
        }
	}
    
    
    @Override
	public long getLastModified(HttpServletRequest request) {
        try {
            String handlerMethodName = methodNameResolver.getHandlerMethodName(request);
            Method lastModifiedMethod = lastModifiedMethodMap.get(handlerMethodName);
            if (lastModifiedMethod != null) {
                try {
                    // Invoke the last-modified method...
                    Long wrappedLong = (Long) lastModifiedMethod.invoke(delegate, new Object[] {request});
                    return (wrappedLong != null ? wrappedLong.longValue() : -1);
                    
                }   catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        catch (NoSuchRequestHandlingMethodException ex) {
        }
        return -1L;
	}
	
	protected String handleViewName(String viewName) throws Exception {
	    return  viewMap.containsKey(viewName) ? viewMap.get(viewName) : viewName;
	}
	
	/**
	 * 處理錯誤訊息
	 * @param request
	 * @param response
	 * @param model
	 * @param throwable
	 * @return
	 */
	protected ModelAndView handleException(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model, Throwable throwable){
	    // 建立錯誤訊息明細
	    StringWriter errorDesc = new StringWriter();
	    throwable.printStackTrace(new PrintWriter(errorDesc));
	    
	    // 放入錯誤訊息資料
	    model.put(EXCEPTION_CODE, throwable.getMessage());
	    model.put(EXCEPTION_DESC, errorDesc.toString());
	    
	    // 取得錯誤訊息預設頁面
	    String viewName = handleExceptionViewName();
	    
	    // 額外錯誤訊息處理
	    doCatch(throwable);
	    
	    // 返回處理結果
	    return new ModelAndView(viewName, model);
	}
    
	/**
	 * 取得發生錯誤的Veiw Name
	 * @return 返回處理錯誤的Veiw Name
	 */
    protected String handleExceptionViewName() {
        String viewName = viewMap.get(DEFAULT_EXCEPTION_VEIW_KEY);
        return (viewName == null) ? DEFAULT_EXCEPTION_VIEW_NAME : viewName;
    }

    /**
     * 透過Reflection方式執行
     * @param methodName
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    protected final String invokeNamedMethod(String methodName, HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws Exception {
        // 取得 HandlerMethodMap 中已註冊之方法
        Method method = (Method)handlerMethodMap.get(methodName);
        if (method == null) {
            throw new NoSuchRequestHandlingMethodException(methodName, getClass());
        }

        // 設定 Method 傳入參數 
        Class<?>[] paramTypes = method.getParameterTypes();
        List<Object> params = new ArrayList<Object>(paramTypes.length);      
        params.add(request);
        params.add(response);
        params.add(RequestThreadResourceManager.getResource(UserAccount.class));
        params.add(model);
        
        // 若參數個數 >= 5, 則表示須注入 Command Object
        if (paramTypes.length >= 5 && !paramTypes[paramTypes.length - 1].equals(HttpSession.class)) {
            Object command = BeanUtils.instantiateClass(paramTypes[paramTypes.length - 1]);
            params.add(command);
            bind(request, command);
        }
        
        // 呼叫實作 Method 並回傳值
        return (String) method.invoke(delegate, params.toArray(new Object[params.size()]));
    }
    

    /**
     * 
     * @param request
     * @param formClass
     * @return
     * @throws Exception
     */
    protected Object bindForm(HttpServletRequest request, Class<?> formClass) throws Exception {
        Object form = BeanUtils.instantiateClass(formClass);
        bind(request, form);
        return form;
    }   

    /**
     * 
     * @param request
     * @param command
     * @throws Exception
     */
    protected void bind(HttpServletRequest request, Object command) throws Exception {
        // 建立 ServletRequest Data Binder
        ServletRequestDataBinder binder = new ServletRequestDataBinder(command, DEFAULT_COMMAND_NAME);
        initBinder(request, binder);
        
        // Bind Data to Command Object
        binder.bind(request);
        binder.closeNoCatch();
    }
    
    /**
     * 初始化 Binder, 可以客製化轉換處理
     * @param request
     * @param binder
     * @throws Exception
     */
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        binder.registerCustomEditor(java.util.Date.class,     new CustomDateEditor(new SimpleDateFormat("yyyy/MM/dd"), true));
        binder.registerCustomEditor(java.sql.Timestamp.class, new TimeStampEditor()); 
    }
    
    /**
     * 設定代理物件 Delegator
     * @param delegate
     */
    public final void setDelegate(Object delegate) {
        Assert.notNull(delegate, "Delegate must not be null");
        this.delegate = delegate;
        
        //  註冊所有 Controller 內所有實現的 Method
        registerHandlerMethods(delegate);
        
        // Delegator 必須至少實作一 Method
        if (handlerMethodMap.isEmpty()) {
            throw new IllegalStateException("No handler methods in class [" + delegate.getClass() + "]");
        }
    }

    /**
     * 註冊所有能處理的方法.
     * @param delegate 委託對象
     */
    private void registerHandlerMethods(Object delegate) {
        this.handlerMethodMap.clear();
        this.lastModifiedMethodMap.clear();

        Method[] methods = delegate.getClass().getMethods();
        for (Method method : methods) {
            if (isHandlerMethod(method)) {
                registerHandlerMethod(method);
                registerLastModifiedMethodIfExists(delegate, method);
            }
        }
    }

    /**
     * 判斷是否為處理一般 Request 的 Method, 僅支援以下實作
     * 例如: _state=view, 則會呼叫 Controller 所實作的以下method其一:
     * <ol>
     * <li>String view(HttpServletRequest, HttpServletResponse, Map)
     * <li>String view(HttpServletRequest, HttpServletResponse, Map, Command)
     * </ol>
     */
    private boolean isHandlerMethod(Method method) {
        Class<?> returnType = method.getReturnType();
        if (String.class.equals(returnType)) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            return (parameterTypes.length >= 3 && HttpServletRequest.class.equals(parameterTypes[0]) && HttpServletResponse.class.equals(parameterTypes[1]));
        }
        return false;
    }

    /**
     * 註冊 Controller 實作之處理方法
     * @param method 處理方法
     */
    private void registerHandlerMethod(Method method) {
        handlerMethodMap.put(method.getName(), method);
    }
    
    /**
     * 註冊 Controller 實作之LastModified 處理方法
     * @param delegate 委託者
     * @param method 處理方法
     */
    private void registerLastModifiedMethodIfExists(Object delegate, Method method) {
        try {
            Method lastModifiedMethod = delegate.getClass().getMethod(method.getName() + LAST_MODIFIED_METHOD_SUFFIX, new Class[] {HttpServletRequest.class});
            Class<?> returnType = lastModifiedMethod.getReturnType();
            if (!(long.class.equals(returnType) || Long.class.equals(returnType))) {
                throw new IllegalStateException("last-modified method [" + lastModifiedMethod + "] declares an invalid return type - needs to be 'long' or 'Long'");
            }
            lastModifiedMethodMap.put(method.getName(), lastModifiedMethod);
        }   catch (NoSuchMethodException ex) {}
    }
//    
//    /**
//     * 編碼檢核
//     * @param request
//     * @return
//     */
//    private void encodeingCheck(HttpServletRequest request, Map<String, Object> model) throws EPException{
//        try{
//            Enumeration<?> en = request.getParameterNames();
//            while (en.hasMoreElements()) {
//                String paramName = (String) en.nextElement();
//                String currentString = request.getParameter(paramName);
//                if(!paramPattern.matcher(currentString).matches()){
//                    String big5String = new String(currentString.getBytes("MS950"));
//                    if(!StringUtils.equals(currentString, big5String)) {
//                       throw new EPException("本系統只支援繁體中文字編碼(MS950)之文字,不支援其他編碼之文字(" + currentString + ")");
//                    }
//                }
//            }
//        } catch(Exception ex){
//            error("編碼檢核失敗:" + ex.getMessage(), ex);
//            throw new EPException("編碼檢核失敗:" + ex.getMessage(), ex);
//        }
//    }
        
    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Map<String, String> getViewMap() {
        return viewMap;
    }
    
    public void setViewMap(Map<String, String> viewMap) {
        this.viewMap = viewMap;
    }
    public final void setMethodNameResolver(MethodNameResolver methodNameResolver) {
        this.methodNameResolver = methodNameResolver;
    }
    
    public final MethodNameResolver getMethodNameResolver() {
        return methodNameResolver;
    }
    
    public abstract void doFinally(HttpServletRequest request);
    
    public abstract void doCatch(Throwable ex);
}

/**
 * <p>Title: com.walsin.platf.web.framework.TimeStampEditor</p>
 * <p>Description: Timestamp 格式轉換</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: FreeLance</p>
 * @author Jason Huang
 * @version 1.0
 */
class TimeStampEditor extends PropertyEditorSupport {  
    
    public void setAsText(String text) throws IllegalArgumentException {  
        Timestamp time = null;
        if(StringUtils.isNotBlank(text)) {
            try {
                time = new Timestamp(new SimpleDateFormat("yyyy/MM/dd hh:mm").parse(text).getTime());
            } catch (ParseException e) {
                throw new IllegalArgumentException(e.getMessage(), e);
            }
        }
        setValue(time);  
    }  
} 
