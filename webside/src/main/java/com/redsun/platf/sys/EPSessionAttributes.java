package com.redsun.platf.sys;

/**
 * <p>Title: com.walsin.platf.web.EPSessionAttributes</p>
 * <p>Description: EP系統Session Scope參數名稱列表</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: FreeLance</p>
 * @author Jason Huang
 * @version 1.0
 */
public interface EPSessionAttributes {
    
	   /** EP登入者基本資料 */
    public static final String EP_USER_INFO = "_epUser";
    
    /** EP登入者選單 */
    public static final String EP_USER_MENU = "_epUserMenu";
}
