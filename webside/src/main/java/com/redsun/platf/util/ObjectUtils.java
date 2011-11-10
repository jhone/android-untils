package com.redsun.platf.util;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

//import com.walsin.platf.web.framework.annotation.EPTransaction;

/**
 * <p>Title: com.walsin.platf.util.ObjectUtils</p>
 * <p>Description: 常用物件方法</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: FreeLance</p>
 * @author Jason Huang
 * @version 1.0
 */
public class ObjectUtils extends org.apache.commons.lang.ObjectUtils{
    
    /**
     * 如果來源值為NULL時,返回預設值
     * @param <T> 型態
     * @param value 來源值
     * @param defaultValue 預設值
     * @return
     */
    public static <T> T defaultNull(T value, T defaultValue){
        return value == null ? defaultValue : value;
    }
    
    /**
     * 取得物件的TXNID
     * @param clazz
     * @return
     */
//pyc change to     @Transactional
    public static String getTxnId(Class<?> clazz){
        Annotation[] annotations = clazz.getAnnotations();
        for(Annotation annotation : annotations) {
            if(annotation instanceof Transactional) {
                return ((Transactional)annotation).value();
//                return ((EPTransaction)annotation).value();
            }
        }
        return StringUtils.EMPTY;
    }
    
    /**
     * 將兩個同類型的不同物件實體合併，使兩個物件內的屬性值整合在一起，傳入的類別其屬性必須都有相對應的 setter及getter 
     * @param originalObj
     * @param newObj
     * @param props
     * @return
     * @throws Exception
     */
    public static Object valueMergeIncludeNullValue(Object originalObj, Object newObj, List<String> props) throws Exception {
        
        Map<?,?> map = PropertyUtils.describe(originalObj);
        Iterator<?> it = map.keySet().iterator();
        while(it.hasNext()) {
            String propNm = it.next().toString();
            if(propNm.equals("class")) continue;
            if(!props.contains(propNm)) continue;
            Object value = PropertyUtils.getProperty(originalObj, propNm);
            PropertyUtils.setProperty(newObj, propNm, value);
        }
        return newObj;
    }
    
    /**
     * 將來源物件的指定屬性值複製至目標物件
     * @param originalObj 來源物件 
     * @param newObj 目標物件
     * @param props 指定屬性值
     * @throws Exception
     */
    public static void valueMapping(Object originalObj, Object newObj, List<String> props) throws Exception {
        Boolean findInList = (props != null);
        
        Map<?, ?> originalBeanMap = PropertyUtils.describe(originalObj);
        Map<?, ?> newBeanProps = PropertyUtils.describe(newObj);
        
        List<String> newBeanPropsInList = new ArrayList<String>();
        Iterator<?> nit = newBeanProps.keySet().iterator();
        while(nit.hasNext()) {
            String propNm = nit.next().toString();
            newBeanPropsInList.add(propNm);
        }
        
        Iterator<?> it = originalBeanMap.keySet().iterator();
        while(it.hasNext()) {
            String propNm = it.next().toString();
            if(propNm.equals("class")) continue;
            Object value = PropertyUtils.getProperty(originalObj, propNm);
            if(findInList && props.contains(propNm))
                PropertyUtils.setProperty(newObj, propNm, value);
            else if(findInList && !props.contains(propNm))
                continue;
            else if(newBeanPropsInList.contains(propNm)){
                PropertyUtils.setProperty(newObj, propNm, value);
            }
        }
    }
}
