package com.redsun.platf.web.interceptor;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.redsun.platf.entity.sys.SystemLanguage;
import com.redsun.platf.entity.sys.SystemTheme;
import com.redsun.platf.service.sys.ConfigLoaderService;
import com.redsun.platf.sys.EPApplicationAttributes;
import com.redsun.platf.util.LogUtils;

/**
 * 標准request 的攔截器 全局配置
 * 
 * @author dick pan <mvc:interceptors> <bean
 *         class="cn.li.controller.StandardRequestInterceptor" /> <bean
 *         class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor">
 *         <property name="paramName" value="language" /> </bean>
 *         </mvc:interceptors>
 */
@Component
public class StandardRequestInterceptor implements HandlerInterceptor {

	private Logger log=LogUtils.getLogger(getClass());

	
	@Resource(name = "applicationConfigLoader")
	ConfigLoaderService configLoader;

	
//	private Theme currentThreme;
//	private Language currentLanguage;

	private List<SystemTheme> themes;
	private List<SystemLanguage> languages;


	public void init(){
	
	}

	@SuppressWarnings("unchecked")
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		if (themes==null)
		 themes= configLoader.getInstance().getThemes();
		if (languages==null)
			languages= configLoader.getInstance().getLanguages();

		Map<String, String> themeConfig = (Map<String, String>) request
				.getSession().getAttribute(EPApplicationAttributes.THEME_NAME);
		
		if (themeConfig == null)
			request.getSession().setAttribute(EPApplicationAttributes.THEME_NAME, themes);
		else
			log.debug("[redsun.web]config reload only");
		
		
		Map<String, String> languageConfig = (Map<String, String>) request
		.getSession().getAttribute(EPApplicationAttributes.LOCALE_NAME);
		if (languageConfig == null)
			request.getSession().setAttribute(EPApplicationAttributes.LOCALE_NAME, languages);
		else
			log.debug("[redsun.web]config reload only");

//		log.debug(request.getSession().getAttribute(themeConfigString));
		
		
		System.out.println(languages);
		System.out.println("count of" +languages.size() );
		System.out.println(themes);
		System.out.println("count of" +themes.size() );
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		System.out.println("poster Handler config=");
//		System.out
//				.println(request.getSession().getAttribute(themeConfigString));

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		System.out.println("afterCompletion");
//		System.out
//				.println(request.getSession().getAttribute(themeConfigString));

	}

}
