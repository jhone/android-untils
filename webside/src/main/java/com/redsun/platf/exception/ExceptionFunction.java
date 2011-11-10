package com.redsun.platf.exception;

/**
 * <p>Title: com.walsin.platf.system.exception.ExceptionFunction</p>
 * <p>Description: 例外事件執行方式(B:即時,O:批次)</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: FreeLance</p>
 * @author Jason Huang
 * @version 1.0
 */
public enum ExceptionFunction {
    
    IMMEDIATE("B", "即時"),
    
    BATCH("O", "批次");
    
    private String code;
    
    private String name;

    private ExceptionFunction(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
