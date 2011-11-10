package com.redsun.platf.entity.sys;

import java.util.Date;

import com.redsun.platf.entity.BaseEntity;

/**
 * <p>Title: com.walsin.platf.orm.entity.BatchJobDetail</p>
 * <p>Description: EP_SYS_BATCHJOB_DETAIL</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: FreeLance</p>
 * @author Jason Huang
 * @version 1.0
 */
public class BatchJobDetail extends BaseEntity{

    private static final long serialVersionUID = -4699474166276216897L;
    
//    /** 背景程式執行記錄ID */
//    private Integer id;

    /** 背景程式ID */
    private BatchJob batchJob;

    /** 開始執行時間 */
    private Date execStartTime;

    /** 結束執行時間 */
    private Date execEndTime;

    /** 執行結果訊息 */
    private String message;

  

    public BatchJob getBatchJob() {
        return batchJob;
    }

    public void setBatchJob(BatchJob batchJob) {
        this.batchJob = batchJob;
    }

    public Date getExecStartTime() {
        return execStartTime;
    }

    public void setExecStartTime(Date execStartTime) {
        this.execStartTime = execStartTime;
    }

    public Date getExecEndTime() {
        return execEndTime;
    }

    public void setExecEndTime(Date execEndTime) {
        this.execEndTime = execEndTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
