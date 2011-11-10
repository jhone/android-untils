package com.redsun.platf.entity.sys;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.redsun.platf.entity.BaseEntity;

/**
 * <p>
 * Title: com.walsin.platf.orm.entity.SystemTxn
 * </p>
 * <p>
 * Description: 交易選單資料主檔EP_SYS_TXN
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
@Entity
@Table(name = "SYS_TXN")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SystemTxn extends BaseEntity {

    private static final long serialVersionUID = -2707788189716932398L;

    /** 交易代碼 kb */
    private String txnNo;

    /** 交易名稱 prompt */
    private String txnName;

    /** 交易程式URL doprg */
    private String txnUrl;

    /** 交易項目描述 message */
    private String txnDesc;

    /** 是否顯示公司別下拉式選單 */
    private String displayCompanyScrollbar;

    // /** 上階ID parentid */
    // private String upperLevelId;

    /** 顯示順序 */
    private Integer dispOrder;

    /** 父選單 */
    private SystemTxn parentId;

    /** 子選單 */
    private List<SystemTxn> childTxn;

    /** report file name **/
    private String reportFilename;

    @Column(length = 100,nullable=false,unique = true)
    public String getTxnName() {
	return txnName;
    }

    public void setTxnName(String txnName) {
	this.txnName = txnName;
    }

    @Column(length = 100)
    public String getTxnUrl() {
	return txnUrl;
    }

    public void setTxnUrl(String txnUrl) {
	this.txnUrl = txnUrl;
    }

    @Column(length = 200)
    public String getTxnDesc() {
	return txnDesc;
    }

    public void setTxnDesc(String txnDesc) {
	this.txnDesc = txnDesc;
    }

    public String getDisplayCompanyScrollbar() {
	return displayCompanyScrollbar;
    }

    public void setDisplayCompanyScrollbar(String displayCompanyScrollbar) {
	this.displayCompanyScrollbar = displayCompanyScrollbar;
    }

    // public String getUpperLevelId() {
    // return upperLevelId;
    // }
    //
    // public void setUpperLevelId(String upperLevelId) {
    // this.upperLevelId = upperLevelId;
    // }

    public Integer getDispOrder() {
	return dispOrder;
    }

    public void setDispOrder(Integer dispOrder) {
	this.dispOrder = dispOrder;
    }

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "parent_id")
    public List<SystemTxn> getChildTxn() {
	return childTxn;
    }

    public void setChildTxn(List<SystemTxn> childTxn) {
	this.childTxn = childTxn;
    }

    @Column(nullable=false)
    public String getTxnNo() {
	return txnNo;
    }

    public void setTxnNo(String txnNo) {
	this.txnNo = txnNo;
    }

    public String getReportFilename() {
	return reportFilename;
    }

    public void setReportFilename(String reportFilename) {
	this.reportFilename = reportFilename;
    }

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//    @JoinColumn(name = "parent_id")
    public SystemTxn getParentId() {
	return parentId;
    }

    public void setParentId(SystemTxn parentId) {
	this.parentId = parentId;
    }

    @Override
    public String toString() {
	return "SystemTxn [childTxn=" + childTxn + ", dispOrder=" + dispOrder
		+ ", displayCompanyScrollbar=" + displayCompanyScrollbar
		+ ", parentId=" + parentId + ", reportFilename="
		+ reportFilename + ", txnDesc=" + txnDesc + ", txnName="
		+ txnName + ", txnNo=" + txnNo + ", txnUrl=" + txnUrl + "]";
    }
}
