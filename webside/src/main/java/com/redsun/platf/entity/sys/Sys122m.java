package com.redsun.platf.entity.sys;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import com.redsun.platf.entity.BaseEntity;

/**
 * <p>Title: com.walsin.platf.orm.entity.Sys122m</p>
 * <p>Description: 授權程式主檔表格 SYS122M</p>
 * <p>Copyright: Copyright (c) Walsin Corp. 2008. All Rights Reserved.</p>
 * <p>Company: FreeLance</p>
 * @author Peace Yang
 * @version 1.0
 */
public class Sys122m extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Sys122mId sys122mId;
	/**ip**/
	@Column(name="IP_ADDRESS",length=15)	
	private String ipAddress;
	@Column(name="HOST_DES", length=100)
	private String hostDes;
	@Column(name="DESC", length=100)
	private String type;


	public synchronized String getType() {
		return type;
	}

	public synchronized void setType(String type) {
		this.type = type;
	}

	public Sys122m() {
	}

	public Sys122m(Sys122mId id) {
		this.sys122mId = id;
	}

	public Sys122m(Sys122mId id, String ipAddress, String hostDes,String type,
			String creUser, Date creDat, String maiUser, Date maiDate) {
		this.sys122mId = id;
		this.ipAddress = ipAddress;
		this.hostDes = hostDes;
		this.type = type;

	}

	

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getHostDes() {
		return this.hostDes;
	}

	public void setHostDes(String hostDes) {
		this.hostDes = hostDes;
	}

	public Sys122mId getSys122mId() {
	    return sys122mId;
	}

	public void setSys122mId(Sys122mId sys122mId) {
	    this.sys122mId = sys122mId;
	}

	
}
