package com.redsun.platf.exception;

/**
 * <p>Title: com.walsin.platf.system.ExceptionLogStatus</p>
 * <p>Description: 例外錯誤狀態(1:未處理,2:處理中,3:已處理)</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: FreeLance</p>
 * @author Jason Huang
 * @version 1.0
 */
public enum ExceptionLogStatus {
    
    INCOMPLETE("W", "未處理"),
    
    IN_PROCESS("P", "處理中"),
    
    COMPLETE("F", "已處理");
    
    private String code;
    
    private String name;

    private ExceptionLogStatus(String code, String name) {
        this.name = name;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
