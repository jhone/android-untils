package com.redsun.platf.sys;

/**
 * <p>
 * Title: com.walsin.platf.web.EPApplicationAttributes
 * </p>
 * <p>
 * Description: EP系統Application Scope參數名稱列表
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
public interface EPApplicationAttributes {

	public final static String MENU_FIRST_PATH = "_epMenuFirst";

	public final static String MENU_SECOND_PATH = "_epMenuSecond";

	public final static String ERROR_MESSAGE = "_epErrorMsg";

	public final static String WARN_MESSAGE = "_epWarnMsg";

	public final static String INFO_MESSAGE = "_epInfoMsg";

	public final static String LINK_MESSAGE = "_epLinkMsg";

	public final static String EP_COMPANY = "_epComapnyId";

	public final static String TARGET_COMPANY = "_targetCompany";

	//theme
	public final static String THEME_NAME = "com.redsun.web.theme";
    //language
	public final static String LOCALE_NAME = "com.redsun.web.locale";
	//theme & language loader object
	public final static String APPLICATION_CONFIG = "com.redsun.web.appConfig";
}
