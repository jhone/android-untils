package com.redsun.platf.exception;


/**
 * <p>Title: com.mxic.platf.web.support.ActionHandleException</p>
 * <p>Description: Multi Method Reflection 交易控制例外物件</p>
 * <p>Copyright: Copyright (c) IBM Corp. 2008. All Rights Reserved.</p>
 * <p>Company: IBM GBS Team</p>
 * @author Jimmy Chen
 * @version 1.0
 */
public class ActionHandleException extends Exception {
    private static final long serialVersionUID = 4682385320988944252L;
    
    /**
     * 建構子
     * @param errorCode 錯誤代碼
     */
    public ActionHandleException(String errorCode) {
        super(errorCode);
    }
    
    /**
     * 取得錯誤代碼
     * @return 錯誤代碼
     */
    public String getErrorCode() {
        return getMessage();
    }
    
}
