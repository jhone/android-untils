package com.redsun.platf.exception;

/**
 * <p>Title: com.walsin.platf.system.ExceptionDocument</p>
 * <p>Description: 例外錯誤文件定義主檔</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: FreeLance</p>
 * @author Jason Huang
 * @version 1.0
 */
public enum ExceptionDocument {
    
    PR(new Integer("1"), "請購單"),
    
    PO(new Integer("2"), "訂購單"),
    
    MN(new Integer("5"), "料號"),
    
    /** 背景排程 */
    SAP_MATERIAL(new Integer("101"), "料號主檔排程"),
    
    SAP_CURRENCY(new Integer("102"), "幣別主檔排程"),
    
    SAP_PLANT(new Integer("103"), "公司主檔排程"),
    
    SAP_COMPANY(new Integer("104"), "BOM主檔排程"),
    
    SAP_PURGROUP(new Integer("105"), "採購群組主檔排程"),
    
    SAP_RATE(new Integer("106"), "匯率主檔排程"),

    SAP_PLANT_RELATED(new Integer("107"), "公司與工廠關聯排程"),
    
    SAP_PRICEBANK(new Integer("108"), "SAP草案協議主檔徘程"),
    
    SAP_PURPARTNERS(new Integer("109"), "SAP下載採購夥伴資料主檔徘程"),
    
    SAP_MATERIALTYPE(new Integer("15"), "物料類型檔排程"),

    SAP_STORAGE_LOCATION(new Integer("22"),"儲存地點排程"),
    
    SAP_EXPENSE(new Integer("23"),"預估費用排程"),
    
    SAP_BUSINESS_PROCESS(new Integer("24"),"企業流程排程"),

    
    NOTE_EMPLOYEE(new Integer("201"), "人事主檔排程");
    
    
    private Integer documentId;
    
    private String documentName;

    private ExceptionDocument(Integer documentId, String documentName) {
        this.documentId = documentId;
        this.documentName = documentName;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public String getDocumentName() {
        return documentName;
    }
}
