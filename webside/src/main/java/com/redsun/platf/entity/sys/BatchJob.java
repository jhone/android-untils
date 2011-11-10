package com.redsun.platf.entity.sys;

import java.util.Date;

import com.redsun.platf.entity.BaseEntity;

/**
 * <p>Title: com.walsin.platf.orm.entity.BatchJob</p>
 * <p>Description: EP_SYS_BATCHJOB</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: FreeLance</p>
 * @author Jason Huang
 * @version 1.0
 */
public class BatchJob extends BaseEntity{

    private static final long serialVersionUID = 1954733153514164643L;
    
//    /** 背景程式ID */
//    private Integer id;

    /** 背景程式名稱 */
    private String batchjobName;

    /** 排程方式 */
    private String batchjobType;

    /** 排程參數 */
    private String parameter;

    /** 起始日期 */
    private Date startDate;

    /** 截止日期 */
    private Date endDate;

    /** 最後執行日期 */
    private Date lastExecDate;

    /** 指定程式內設定的Trigger名稱 */
    private String triggerName;

    /** 指定程式內設定的JobDeatil名稱 */
    private String jobdetailName;

    /** 每月或每週的執行時間,格式 時:分:秒 */
    private String executeTime;

    /** 狀態 */
    private int state;
    
 

    public String getBatchjobName() {
        return batchjobName;
    }

    public void setBatchjobName(String batchjobName) {
        this.batchjobName = batchjobName;
    }

    public String getBatchjobType() {
        return batchjobType;
    }

    public void setBatchjobType(String batchjobType) {
        this.batchjobType = batchjobType;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getLastExecDate() {
        return lastExecDate;
    }

    public void setLastExecDate(Date lastExecDate) {
        this.lastExecDate = lastExecDate;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getJobdetailName() {
        return jobdetailName;
    }

    public void setJobdetailName(String jobdetailName) {
        this.jobdetailName = jobdetailName;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
