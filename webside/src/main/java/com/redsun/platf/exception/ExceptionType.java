package com.redsun.platf.exception;

/**
 * <p>Title: com.walsin.platf.system.ExceptionType</p>
 * <p>Description: 錯誤類型 (COM: 通訊錯誤, SAP: SAP錯誤,  NOTES: NOTES錯誤,  MISC: 其他錯誤, SYS: 系統錯誤)</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: FreeLance</p>
 * @author Jason Huang
 * @version 1.0
 */
public enum ExceptionType {
    
    SYSTEM_EXCEPTION("SYS", "系統錯誤"),
    
    RFC_EXCEPTION("RFC", "RFC連線失敗"),
    
    SAP_EXCEPTION("SAP", "SAP錯誤"),
    
    NOTE_EXCEPTION("NOTES", "NOTES錯誤"),
    
    MISC_EXCPPTION("MISC", "MISC"),
    
    DB_EXCPPTION("DB", "DB資料錯誤"),
    
    SYS_EXCPETION("SYS", "系統錯誤"),
    
    SEND_MAIL_EXCEPTION("MAIL", "發送MAIL失敗");
    
    private String typeCode;
    
    private String typeName;

    private ExceptionType(String typeCode, String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public String getTypeName() {
        return typeName;
    }
}
