package com.redsun.platf.util;

import org.apache.log4j.Logger;

/**
 * <p>Title: com.walsin.platf.util.LogUtils</p>
 * <p>Description:取得 Log4j Logger 工具 </p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: FreeLance</p>
 * @author Jason Huang
 * @version 1.0
 */
public abstract class LogUtils {
	
	/**
	 * 取得Log4j Logger
	 * @param object 指定物件
	 * @return Logger
	 */
	public static Logger getLogger(Object object) {
		return (object == null) ? Logger.getRootLogger():getLogger(object.getClass());
	}
	
	/**
	 * 取得Log4j Logger
	 * @param clazz 指定 Class
	 * @return Logger
	 */
	public static Logger getLogger(Class<?> clazz) {
		return Logger.getLogger(clazz);
	}
	
	/**
	 * 取得Log4j Logger
	 * @param name 指定名稱
	 * @return Logger
	 */
	public static Logger getLogger(String name) {
		return Logger.getLogger(name);
	}
}

