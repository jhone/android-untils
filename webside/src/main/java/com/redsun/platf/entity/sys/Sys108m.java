package com.redsun.platf.entity.sys;

import java.io.Serializable;

import com.redsun.platf.entity.BaseEntity;

/**
 * <p>Title: com.walsin.platf.orm.entity.Sys108m</p>
 * <p>Description: 主機清單表格 SYS118M</p>
 * <p>Copyright: Copyright (c) Walsin Corp. 2008. All Rights Reserved.</p>
 * <p>Company: FreeLance</p>
 * @author Peace Yang
 * @version 1.0
 */
public class Sys108m extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String osHost;
	private String osLoginPath;
	private String osAuditPath;
	private String dbAuditPath;
	private String ftpId;
	private String ftpPasswd;
	private String localDest;

	public Sys108m() {
	}

	public Sys108m(String osHost, String osLoginPath, String osAuditPath,
			String dbAuditPath,String ftpId,String ftpPasswd,String localDest) {
		this.osHost = osHost;
		this.osLoginPath = osLoginPath;
		this.osAuditPath = osAuditPath;
		this.dbAuditPath = dbAuditPath;
		this.ftpId = ftpId;
		this.ftpPasswd = ftpPasswd;
		this.localDest = localDest;
	}
	
	public String getOsHost() {
		return this.osHost;
	}

	public void setOsHost(String osHost) {
		this.osHost = osHost;
	}

	public String getOsLoginPath() {
		return this.osLoginPath;
	}

	public void setOsLoginPath(String osLoginPath) {
		this.osLoginPath = osLoginPath;
	}

	public String getOsAuditPath() {
		return this.osAuditPath;
	}

	public void setOsAuditPath(String osAuditPath) {
		this.osAuditPath = osAuditPath;
	}

	public String getDbAuditPath() {
		return this.dbAuditPath;
	}

	public void setDbAuditPath(String dbAuditPath) {
		this.dbAuditPath = dbAuditPath;
	}

	public synchronized String getFtpId() {
		return ftpId;
	}

	public synchronized void setFtpId(String ftpId) {
		this.ftpId = ftpId;
	}

	public synchronized String getFtpPasswd() {
		return ftpPasswd;
	}

	public synchronized void setFtpPasswd(String ftpPasswd) {
		this.ftpPasswd = ftpPasswd;
	}

	public synchronized String getLocalDest() {
		return localDest;
	}

	public synchronized void setLocalDest(String localDest) {
		this.localDest = localDest;
	}
	
}
