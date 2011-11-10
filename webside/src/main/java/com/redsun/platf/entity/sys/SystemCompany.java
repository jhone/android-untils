package com.redsun.platf.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.redsun.platf.entity.IdEntity;

/**
 * <p>
 * Title: com.walsin.platf.system.SystemConfiguration
 * </p>
 * <p>
 * Description: 系統環境設定檔
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
// 表名与类名不相同时重新定义表名.
@Table(name = "SYS_COMPANY")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SystemCompany  extends IdEntity{

    
    /** 代碼 */
    private String companyNo;

    /** 名稱 */
    private String companyName;

    /** Mail */
    private String email;

    /** tel */
    private String tel;

    /** fax */
    private String fax;

    /** 稅號 */
    private String taxNo;
    
    /** 帳號 */
    private String bankNo;
    
    /** 開戶行 **/
    private String bankName;

    // 字段非空且唯一, 用于提醒Entity使用者及生成DDL.
    @Column(name="company_no",nullable = false, unique = true)
    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
    
      public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String toString() {
	return "SystemCompany [bankName=" + bankName + ", bankNo=" + bankNo
		+ ", companyName=" + companyName + ", companyNo=" + companyNo
		+ ", email=" + email + ", fax=" + fax + ", taxNo=" + taxNo
		+ ", tel=" + tel + "]";
    }

   

}
