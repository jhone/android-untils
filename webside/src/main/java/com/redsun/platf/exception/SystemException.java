package com.redsun.platf.exception;

/**
 * <p>Title: com.walsin.platf.system.EPExceptionLog</p>
 * <p>Description: 系統例外紀錄物件</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: FreeLance</p>
 * @author Jason Huang
 * @version 1.0
 */
public class SystemException extends Exception {
    
    private static final long serialVersionUID = -6727253755812644457L;

    private ExceptionType type;
    
    private ExceptionFunction function;
    
    private String documentNumber;
    
    private ExceptionDocument document;
    
    private String documentOwner;
    
    private Class<?> classType;
    
    /**
     * 建構子
     * @param classType class物件
     * @param type 例外錯誤類型
     * @param function  例外事件執行方式
     * @param message 錯誤訊息
     * @param cause 原始例外物件
     */
    public SystemException(Class<?> classType, ExceptionType type, ExceptionFunction function, String message, Throwable cause) {
        super(message, cause);
        this.type = type;
        this.function = function;
        this.classType = classType;
    }
    
    /**
     * 建構子
     * @param classType class物件
     * @param type 例外錯誤類型
     * @param function  例外事件執行方式
     * @param document 例外錯誤文件
     * @param documentNumber 例外錯誤文件編號
     * @param documentOwner 例外錯誤文件所有人
     * @param message 錯誤訊息
     * @param cause 原始例外物件
     */
    public SystemException(Class<?> classType, ExceptionType type, ExceptionFunction function, ExceptionDocument document, String documentNumber, String documentOwner, String message, Throwable cause) {
        super(message, cause);
        this.type = type;
        this.document = document;
        this.function = function;
        this.classType = classType;
        this.documentNumber = documentNumber;
        this.documentOwner = documentOwner;
    }

    public Class<?> getClassType() {
        return classType;
    }

    public ExceptionFunction getFunction() {
        return function;
    }
    
    public ExceptionType getType() {
        return type;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public ExceptionDocument getDocument() {
        return document;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDocumentOwner() {
        return documentOwner;
    }
}
