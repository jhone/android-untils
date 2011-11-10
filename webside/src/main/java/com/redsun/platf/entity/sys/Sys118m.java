package com.redsun.platf.entity.sys;

import java.io.Serializable;

import com.redsun.platf.entity.BaseEntity;

/**
 * <p>Title: com.walsin.platf.orm.entity.Sys118m</p>
 * <p>Description: 特定使用者主檔表格 SYS118M</p>
 * <p>Copyright: Copyright (c) Walsin Corp. 2008. All Rights Reserved.</p>
 * <p>Company: FreeLance</p>
 * @author Peace Yang
 * @version 1.0
 */
@SuppressWarnings("unused")
public class Sys118m  extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String userName;
	private String userDes;
	

	public Sys118m() {
	}

	public Sys118m(String userId) {
		this.userId = userId;
	}

	public Sys118m(String userId, String userName, String userDes) {
		this.userId = userId;
		this.userName = userName;
		this.userDes = userDes;	
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserDes() {
		return this.userDes;
	}

	public void setUserDes(String userDes) {
		this.userDes = userDes;
	}

}
