package com.redsun.platf.web.framework;

import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.redsun.platf.dao.DataAccessObjectFactory;
import com.redsun.platf.service.sys.ConfigLoaderService;
import com.redsun.platf.service.sys.impl.ApplicationConfigLoader;
import com.redsun.platf.sys.EPApplicationAttributes;
import com.redsun.platf.sys.SystemConfiguration;
import com.redsun.platf.web.webservice.ServiceFactory;

/**
 * <p>
 * Title: com.walsin.platf.web.framework.StandardController
 * </p>
 * <p>
 * Description: 標準控制父類別
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: FreeLance
 * </p>
 * 
 * @author Jason Huang
 * @version 1.0
 */

//@SessionAttributes("appConfig")
// 必須在session httprequest 裡getSession().setAttribute("pet",object<? of Person>)
@Component
public class StandardController extends ActionHandleController {

	Logger log = com.redsun.platf.util.LogUtils.getLogger(getClass());

	/** 環境系統參數 */
	private SystemConfiguration systemConfiguration;

	/** DAO集合物件 */
	private DataAccessObjectFactory dataAccessObjectFactory;

	/** Service集合物件 */
	private ServiceFactory serviceFactory;

//	private static final ConfigLoaderService appConfig = ConfigLoaderService
//			.getInstance();

	/*
	 * init 僅加載一次 set appconfig to context
	 */
	@PostConstruct
	public void initConfig() {

//		getServletContext().setAttribute(
//				EPApplicationAttributes.APPLICATION_CONFIG, appConfig);
//
//		System.out.println("............"+appConfig.getThemes().size());
//		log.info("[redsun] init configurator ok!");

	}

	
	/**
	 * 取得Bean物件
	 * 
	 * @param beanName
	 * @return
	 */
	protected Object getBean(String beanName) {
		return getApplicationContext().getBean(beanName);
	}

	/**
	 * 取得多國語系Message
	 * 
	 * @param code
	 *            代碼
	 * @return Message
	 */
	protected String getMessage(String code) {
		try {
			return getApplicationContext()
					.getMessage(code, null, Locale.TAIWAN);
		} catch (NoSuchMessageException ex) {
			return code;
		}
	}

	/**
	 * 取得多國語系Message
	 * 
	 * @param code
	 *            代碼
	 * @param obj
	 *            參數
	 * @return Message
	 */
	protected String getMessage(String code, Object[] obj) {
		try {
			return getApplicationContext().getMessage(code, obj, Locale.TAIWAN);
		} catch (NoSuchMessageException ex) {
			return code;
		}
	}

	/**
	 * 顯示語言訊息
	 * 
	 * @param model
	 * @param code
	 */
//	protected void putLocale(Map<String, Object> model, String code) {
//		List<Language> ls = ApplicationConfigLoader.getInstance()
//				.getLanguages();
//
//		for (Language language : ls) {
//			if (code.equalsIgnoreCase(language.getLanguage()))
//				model.put(EPApplicationAttributes.LOCALE_NAME, code);
//
//		}
//
//	}

	/**
	 * 顯示皮膚訊息
	 * 
	 * @param model
	 * @param code
	 */
//	protected void putTheme(Map<String, Object> model, String code) {
//		model.put(EPApplicationAttributes.LOCALE_NAME, getMessage(code));
//	}

	/**
	 * 顯示錯誤訊息
	 * 
	 * @param model
	 * @param code
	 */
	protected void putErrorMessage(Map<String, Object> model, String code) {
		model.put(EPApplicationAttributes.ERROR_MESSAGE, getMessage(code));
	}

	/**
	 * 顯示警告訊息
	 * 
	 * @param model
	 * @param code
	 */
	protected void putWarnMessage(Map<String, Object> model, String code) {
		model.put(EPApplicationAttributes.WARN_MESSAGE, getMessage(code));
	}

	/**
	 * 顯示提示訊息
	 * 
	 * @param model
	 * @param code
	 */
	protected void putInfoMessage(Map<String, Object> model, String code) {
		model.put(EPApplicationAttributes.INFO_MESSAGE, getMessage(code));
	}

	/**
	 * 顯示錯誤訊息
	 * 
	 * @param model
	 * @param code
	 * @param values
	 */
	protected void putErrorMessage(Map<String, Object> model, String code,
			Object[] values) {
		model.put(EPApplicationAttributes.ERROR_MESSAGE,
				getMessage(code, values));
	}

	/**
	 * 顯示警告訊息
	 * 
	 * @param model
	 * @param code
	 * @param values
	 */
	protected void putWarnMessage(Map<String, Object> model, String code,
			Object[] values) {
		model.put(EPApplicationAttributes.WARN_MESSAGE,
				getMessage(code, values));
	}

	/**
	 * 顯示提示訊息
	 * 
	 * @param model
	 * @param code
	 * @param values
	 */
	protected void putInfoMessage(Map<String, Object> model, String code,
			Object[] values) {
		model.put(EPApplicationAttributes.INFO_MESSAGE,
				getMessage(code, values));
	}

	/**
	 * 顯示提示訊息(包含link)
	 * 
	 * @param model
	 * @param code
	 */
	protected void putLinkMessage(Map<String, Object> model, String code,
			String link) {
		model.put(EPApplicationAttributes.LINK_MESSAGE, getMessage(code) + link);
	}

	/**
	 * 顯示提示訊息(包含link)
	 * 
	 * @param model
	 * @param code
	 * @param values
	 */
	protected void putLinkMessage(Map<String, Object> model, String code,
			Object[] values, String link) {
		model.put(EPApplicationAttributes.LINK_MESSAGE,
				getMessage(code, values) + link);
	}

	public DataAccessObjectFactory getDataAccessObjectFactory() {
		return dataAccessObjectFactory;
	}

	public void setDataAccessObjectFactory(
			DataAccessObjectFactory dataAccessObjectFactory) {
		this.dataAccessObjectFactory = dataAccessObjectFactory;
	}

	public SystemConfiguration getSystemConfiguration() {
		return systemConfiguration;
	}

	public void setSystemConfiguration(SystemConfiguration systemConfiguration) {
		this.systemConfiguration = systemConfiguration;
	}

	public ServiceFactory getServiceFactory() {
		return serviceFactory;
	}

	public void setServiceFactory(ServiceFactory serviceFactory) {
		this.serviceFactory = serviceFactory;
	}

	@Override
	public void doFinally(HttpServletRequest request) {
	}

	@Override
	public void doCatch(Throwable ex) {
		// 系統例外紀錄
		// getServiceFactory().getSystemService().recordException(new
		// SystemException(getClass(), ExceptionType.SYSTEM_EXCEPTION,
		// ExceptionFunction.IMMEDIATE, ex.getMessage(), ex));

	}
}
