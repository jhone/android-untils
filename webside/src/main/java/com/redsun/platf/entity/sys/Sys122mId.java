package com.redsun.platf.entity.sys;

import javax.persistence.Column;

/**
 * <p>Title: com.walsin.platf.orm.entity.Sys122mId</p>
 * <p>Description: 授權程式主檔表格SYS122M 鍵值檔</p>
 * <p>Copyright: Copyright (c) Walsin Corp. 2008. All Rights Reserved.</p>
 * <p>Company: FreeLance</p>
 * @author Peace Yang
 *  * @version 1.0
 */

public class Sys122mId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column( name="USER_HOST",length=30)
	private String userHost;
	
	@Column( name="IS_AUTH",length=1)
	private String isAuth;

	public Sys122mId() {
	}

	public Sys122mId(String userHost, String isAuth) {
		this.userHost = userHost;
		this.isAuth = isAuth;
	}

	public String getUserHost() {
		return this.userHost;
	}

	public void setUserHost(String userHost) {
		this.userHost = userHost;
	}

	public String getIsAuth() {
		return this.isAuth;
	}

	public void setIsAuth(String isAuth) {
		this.isAuth = isAuth;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Sys122mId))
			return false;
		Sys122mId castOther = (Sys122mId) other;

		return ((this.getUserHost() == castOther.getUserHost()) || (this
				.getUserHost() != null
				&& castOther.getUserHost() != null && this.getUserHost()
				.equals(castOther.getUserHost())))
				&& ((this.getIsAuth() == castOther.getIsAuth()) || (this
						.getIsAuth() != null
						&& castOther.getIsAuth() != null && this.getIsAuth()
						.equals(castOther.getIsAuth())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserHost() == null ? 0 : this.getUserHost().hashCode());
		result = 37 * result
				+ (getIsAuth() == null ? 0 : this.getIsAuth().hashCode());
		return result;
	}

}
