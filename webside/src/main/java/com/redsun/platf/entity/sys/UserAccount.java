package com.redsun.platf.entity.sys;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.redsun.platf.entity.BaseEntity;

/**
 * <p>
 * Title: com.walsin.platf.orm.entity.UserAccount
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: FreeLance
 * </p>
 * 
 * @author Peace Yang
 * @version 1.0
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "SYS_USERACCOUNT")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserAccount extends BaseEntity {

    private static final long serialVersionUID = 5513616837541962762L;

    private String accountId;
    private String userName;
    private String ipAddress;
    private String hostName;

  
    public synchronized String getUserName() {
	return userName;
    }

    public synchronized String getIpAddress() {
	return ipAddress;
    }

    public synchronized String getHostName() {
	return hostName;
    }

    public synchronized void setUserName(String userName) {
	this.userName = userName;
    }

    public synchronized void setIpAddress(String ipAddress) {
	this.ipAddress = ipAddress;
    }

    public synchronized void setHostName(String hostName) {
	this.hostName = hostName;
    }

    public String getAccountId() {
	return accountId;
    }

    public void setAccountId(String accountId) {
	this.accountId = accountId;
    }

}