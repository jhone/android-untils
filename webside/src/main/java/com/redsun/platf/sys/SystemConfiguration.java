package com.redsun.platf.sys;

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
 * @version 1.0 when init into application read from properties file
 * 
 */

public class SystemConfiguration {

	/** 開發者模式 */
	private boolean developerMode;

	/** 建立Mail Url */
	private String mailGeneratorUrl;

	/** Batch建立Mail Url */
	private String batchMailGeneratorUrl;

	/** 產生Mail路徑 */
	private String mailOutputPath;
	/** 檔案位置 */
	private String attachmentPath;

	/** 簽名檔位置 **/
	private String signaturePath;

	/** 範本檔案位置 **/
	private String tempFilePath;

	/** 報表檔案暫存位置 **/
	private String reportFilePath;

	/** 報表來源位置 **/
	private String reportSourcePath;

	/** JCO LOG XML位置 **/
	private String jcoLogXMLPath;

	/** 簽核引擎逾期通知日數 **/
	private Integer overdueDays;

	/** web service帳號,密碼 **/
	private String wsAccount;

	private String wsPassword;

	/** 密碼錯誤上限(次數) */
	private Integer pwdErrorTimes;
	/** 密碼有效時間(天數) */
	private Integer pwdEffective;

	/** 密碼不可重複(次數) */
	private Integer pwdUseTimes;

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public String getBatchMailGeneratorUrl() {
		return batchMailGeneratorUrl;
	}

	public String getJcoLogXMLPath() {
		return jcoLogXMLPath;
	}

	public String getMailGeneratorUrl() {
		return mailGeneratorUrl;
	}

	public String getMailOutputPath() {
		return mailOutputPath;
	}

	public Integer getOverdueDays() {
		return overdueDays;
	}

	public Integer getPwdEffective() {
		return pwdEffective;
	}

	public Integer getPwdErrorTimes() {
		return pwdErrorTimes;
	}

	public Integer getPwdUseTimes() {
		return pwdUseTimes;
	}

	public String getReportFilePath() {
		return reportFilePath;
	}

	public String getReportSourcePath() {
		return reportSourcePath;
	}

	public String getSignaturePath() {
		return signaturePath;
	}

	public String getTempFilePath() {
		return tempFilePath;
	}

	public String getWsAccount() {
		return wsAccount;
	}

	public String getWsPassword() {
		return wsPassword;
	}

	public boolean isDeveloperMode() {
		return developerMode;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public void setBatchMailGeneratorUrl(String batchMailGeneratorUrl) {
		this.batchMailGeneratorUrl = batchMailGeneratorUrl;
	}

	public void setDeveloperMode(boolean developerMode) {
		this.developerMode = developerMode;
	}

	public void setJcoLogXMLPath(String jcoLogXMLPath) {
		this.jcoLogXMLPath = jcoLogXMLPath;
	}

	public void setMailGeneratorUrl(String mailGeneratorUrl) {
		this.mailGeneratorUrl = mailGeneratorUrl;
	}

	public void setMailOutputPath(String mailOutputPath) {
		this.mailOutputPath = mailOutputPath;
	}

	public void setOverdueDays(Integer overdueDays) {
		this.overdueDays = overdueDays;
	}

	public void setPwdEffective(Integer pwdEffective) {
		this.pwdEffective = pwdEffective;
	}

	public void setPwdErrorTimes(Integer pwdErrorTimes) {
		this.pwdErrorTimes = pwdErrorTimes;
	}

	public void setPwdUseTimes(Integer pwdUseTimes) {
		this.pwdUseTimes = pwdUseTimes;
	}

	public void setReportFilePath(String reportFilePath) {
		this.reportFilePath = reportFilePath;
	}

	public void setReportSourcePath(String reportSourcePath) {
		this.reportSourcePath = reportSourcePath;
	}

	public void setSignaturePath(String signaturePath) {
		this.signaturePath = signaturePath;
	}

	public void setTempFilePath(String tempFilePath) {
		this.tempFilePath = tempFilePath;
	}

	public void setWsAccount(String wsAccount) {
		this.wsAccount = wsAccount;
	}

	public void setWsPassword(String wsPassword) {
		this.wsPassword = wsPassword;
	}
}
