package com.redsun.platf.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * <p>Title: com.walsin.platf.util.JspUtils</p>
 * <p>Description: JSP 網頁相關工具</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: FreeLance</p>
 * @author Jason Huang
 * @version 1.0
 */
public abstract class JspUtils {

	/**
	 * 取得語系
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return 語系
	 */
	public static Locale getLocale(HttpServletRequest request) {
		return RequestContextUtils.getLocale(request);
	}

	/**
	 * 取得語言 Ex: zh_TW -> zh, en_US -> en
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return 語言
	 */
	public static String getLanguage(HttpServletRequest request) {
		return RequestContextUtils.getLocale(request).getLanguage();
	}

	/**
	 * 跳脫 Html 語法
	 * 
	 * @param source
	 *            原字串
	 * @return
	 */
	public static String escapeHtml(String source) {
		return StringEscapeUtils.escapeHtml(source);
	}

	/**
	 * 取得 Spring WebApplicationContext
	 * 
	 * @param request
	 *            HTTP Request
	 * @return WebApplicationContext
	 */
	public static WebApplicationContext getContext(HttpServletRequest request) {
		return RequestContextUtils.getWebApplicationContext(request);
	}

	/**
	 * 取得訊息
	 * 
	 * @param request
	 * @param code
	 * @return
	 */
	public static String getMessage(HttpServletRequest request, String code) {
		return getContext(request).getMessage(code, new String[] {},
				getLocale(request));
	}

	/**
	 * 取得訊息
	 * @param request
	 * @param code
	 * @param arguments
	 * @return
	 */
	public static String getMessage(HttpServletRequest request, String code, Object[] arguments) {
		return getContext(request).getMessage(code, arguments, getLocale(request));
	}

	/**
	 * 取得訊息
	 * @param request
	 * @param code
	 * @param arguments
	 * @param defaultMessage
	 * @return
	 */
	public static String getMessage(HttpServletRequest request, String code,
			Object[] arguments, String defaultMessage) {
		return getContext(request).getMessage(code, arguments, defaultMessage,
				getLocale(request));
	}
}
