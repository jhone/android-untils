package com.redsun.platf.entity.sys;

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
 * Description: 系統環境檔
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
@Table(name = "SYS_VALUE")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SystemValue extends IdEntity {

    /** no */
    private String sysNo;

    /** key */
    private String sysKey;

    /** value */
    private String sysValue;

    /** desc */
    private String description;

//    @Override
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "Fid")
//    public Long getId() {
//	return id;
//    }

    public String getSysNo() {
	return sysNo;
    }

    public void setSysNo(String sysNo) {
	this.sysNo = sysNo;
    }

    public String getSysKey() {
	return sysKey;
    }

    public void setSysKey(String sysKey) {
	this.sysKey = sysKey;
    }

    public String getSysValue() {
	return sysValue;
    }

    public void setSysValue(String sysValue) {
	this.sysValue = sysValue;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @Override
    public String toString() {
	return "SystemValue [description=" + description + ", sysKey=" + sysKey
		+ ", sysNo=" + sysNo + ", sysValue=" + sysValue + "]";
    }

}
