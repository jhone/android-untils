package com.redsun.platf.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.hibernate.proxy.HibernateProxyHelper;


/**
 * <p>Title: com.mxic.platf.orm.BaseEntity</p>
 * <p>Description: 基礎 ORM 資料元件</p>
 * <p>Copyright: Copyright (c) IBM Corp. 2008. All Rights Reserved.</p>
 * <p>Company: FreeLance</p>
 * @author Jason Huang
 * @version 1.0
 */
@MappedSuperclass
public abstract class BaseEntity  extends IdEntity implements Serializable {
   
    private static final long serialVersionUID = 1L;

    /**
     * 建立日期.
     */
    private Date creationDate;

    /**
     * 建立者代碼 
     */
    private String createdBy;

    /**
     * 最後更新日期.
     */
    private Date lastUpdateDate;

    /**
     * 最後更新者代碼
     */
    private String lastUpdatedBy;
    
    @Column(columnDefinition="timestamp")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    @Column(columnDefinition="timestamp")
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public boolean equals(Object o) {
        try {
            return new EqualsBuilder().append(HibernateProxyHelper.getClassWithoutInitializingProxy(this), HibernateProxyHelper.getClassWithoutInitializingProxy(o)).append(PropertyUtils.getSimpleProperty(this, "id"), PropertyUtils.getSimpleProperty(o, "id")).isEquals();
        } catch (Exception e) {}
        return false;
    }
}

/**
 * <?xml version="1.0"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">

<hibernate-mapping package="com.walsin.platf.orm.entity">

<!-- 資料庫主機登入登出紀錄  -->
 	<class name="Sys103m" table="SYS103M">
        <composite-id name="id" class="Sys103mId">
            <key-property name="osHost" type="string">
                <column name="OS_HOST" length="30" />
            </key-property>
            <key-property name="osUser" type="string">
                <column name="OS_USER" />
            </key-property>
            <key-property name="logonDay" type="timestamp">
                <column name="LOGON_DAY" length="7" />
            </key-property>
        </composite-id>
        <property name="terminal" type="string">
            <column name="TERMINAL" length="10" />
        </property>
        <property name="ipAddress" type="string">
            <column name="IP_ADDRESS" length="15" />
        </property>
        <property name="logoffDay" type="timestamp">
            <column name="LOGOFF_DAY" length="7" />
        </property>
        <property name="elapsedMinutes" type="string">
            <column name="ELAPSED_MINUTES" length="20" />
        </property>
    </class>

	<!-- 資料庫主機重要帳號執行記錄  -->
    <class name="Sys104m" table="SYS104M">
        <id name="seq" type="string">
            <column name="SEQ"/>
            <generator class="uuid" />
        </id>
        <property name="osHost" type="string">
            <column name="OS_HOST" length="30" not-null="true" />
        </property>
        <property name="osUser" type="string">
            <column name="OS_USER" not-null="true" />
        </property>
        <property name="logoffDay" type="date">
            <column name="LOGOFF_DAY" length="7" />
        </property>
        <property name="cmdText" type="string">
            <column name="CMD_TEXT" length="2000" />
        </property>
        <property name="creDate" type="timestamp">
            <column name="CRE_DATE" not-null="true" />
        </property>
    </class>
    
	<!-- 資料庫最高權限使用記錄  -->
    <class name="Sys105m" table="SYS105M">
        <composite-id name="id" class="Sys105mId">
            <key-property name="sid" type="string">
                <column name="SID" length="30" />
            </key-property>
            <key-property name="dbUser" type="string">
                <column name="DB_USER" length="30" />
            </key-property>
            <key-property name="executeTime" type="timestamp">
                <column name="EXECUTE_TIME" length="7" />
            </key-property>
            <key-property name="pid" type="string">
                <column name="PID" length="10" />
            </key-property>
        </composite-id>
        <property name="oshost" type="string">
            <column name="OS_HOST" length="128" />
        </property>
        <property name="osUser" type="string">
            <column name="OS_USER" />
        </property>
        <property name="privilege" type="string">
            <column name="PRIVILEGE" length="20" />
        </property>
        <property name="terminal" type="string">
            <column name="TERMINAL" length="30" />
        </property>
        <property name="status" type="string">
            <column name="STATUS" length="10" />
        </property>
        <property name="cmdText" type="string">
            <column name="CMD_TEXT" length="4000" />
        </property>
    </class>
        
	<!-- 主機清單維表格  -->
	<class name="Sys108m" table="SYS108M" schema="DSA">
        <id name="osHost" type="string">
            <column name="OS_HOST" length="30" />
            <generator class="assigned" />
        </id>
        <property name="osLoginPath" type="string">
            <column name="OS_LOGIN_PATH" length="100" not-null="true" />
        </property>
        <property name="osAuditPath" type="string">
            <column name="OS_AUDIT_PATH" length="100" not-null="true" />
        </property>
        <property name="dbAuditPath" type="string">
            <column name="DB_AUDIT_PATH" length="100" not-null="true" />
        </property>
        <property name="ftpId" type="string">
            <column name="FTP_ID" length="20" />
        </property>
        <property name="ftpPasswd" type="string">
            <column name="FTP_PASSWD" length="20" />
        </property>
        <property name="localDest" type="string">
            <column name="LOCAL_DEST" length="100"/>
        </property>
        <property name="creationDate" column="CRE_DATE"/>
		<property name="createdBy" column="CRE_USER"/>
		<property name="lastUpdateDate" column="MAI_DATE"/>
		<property name="lastUpdatedBy" column="MAI_USER"/>
    </class>
    
    <!-- OS-HOST與SID與對照表  -->
    <class name="Sys109m" table="SYS109M">
        <composite-id name="id" class="Sys109mId">
            <key-property name="sid" type="string">
                <column name="SID" length="30" />
            </key-property>
            <key-property name="osHost" type="string">
                <column name="OS_HOST" length="30" />
            </key-property>
        </composite-id>
        <property name="sidDes" type="string">
            <column name="SID_DES" length="40" />
        </property>
        <property name="creUser" type="string">
            <column name="CRE_USER" length="10" />
        </property>
        <property name="creDate" type="timestamp">
            <column name="CRE_DATE" length="7" />
        </property>
        <property name="maiUser" type="string">
            <column name="MAI_USER" length="10" />
        </property>
        <property name="maiDate" type="timestamp">
            <column name="MAI_DATE" length="7" />
        </property>
    </class>
    
    <!-- 重要資料表維護作業  -->
   <class name="Sys116m" table="SYS116M">
        <composite-id name="id" class="Sys116mId">
            <key-property name="sid" type="string">
                <column name="SID" length="30" />
            </key-property>
            <key-property name="tableOwner" type="string">
                <column name="TABLE_OWNER" length="30" />
            </key-property>
            <key-property name="tableName" type="string">
                <column name="TABLE_NAME" length="30" />
            </key-property>
        </composite-id>
        <property name="creDate" type="timestamp">
            <column name="CRE_DATE" length="7" />
        </property>
        <property name="isAuth" type="string">
            <column name="IS_AUTH" length="2" />
        </property>
    </class>
    
	<!-- 特殊程式維表格  -->
	<class name="Sys117m" table="SYS117M" schema="DSA">
        <id name="progid" type="string">
            <column name="PROGID" length="30" />
            <generator class="assigned" />
        </id>
        <property name="progname" type="string">
            <column name="PROGNAME" length="40" />
        </property>
        <property name="sysid" type="string">
            <column name="SYSID" length="20" />
        </property>
        <property name="progState" type="string">
            <column name="PROG_STATE" length="1" />
        </property>
        <property name="creationDate" column="CRE_DAT"/>
		<property name="createdBy" column="CRE_USER"/>
		<property name="lastUpdateDate" column="MAI_DATE"/>
		<property name="lastUpdatedBy" column="MAI_USER"/>
    </class>

	<!-- 特定使用者表格  --> 
 	<class name="Sys118m" table="SYS118M" schema="DSA">
        <id name="userId" type="string">
            <column name="USER_ID" length="30" />
            <generator class="assigned" />
        </id>        
        <property name="userName" type="string">
            <column name="USER_NAME" length="12" />
        </property>
        <property name="userDes" type="string">
            <column name="USER_DES" length="40" />
        </property>        
        <property name="creationDate" column="CRE_DAT"/>
		<property name="createdBy" column="CRE_USER"/>
		<property name="lastUpdateDate" column="MAI_DATE"/>
		<property name="lastUpdatedBy" column="MAI_USER"/>
    </class> 
    
    <!-- 異常時間定義表格  --> 
    <class name="Sys119m" table="SYS119M">
        <id name="seq" type="string">
            <column name="SEQ" length="64" />
            <generator class="uuid" />
        </id>
        <property name="sid" type="string">
            <column name="SID" length="30" not-null="true" />
        </property>
        <property name="isAuth" type="string">
            <column name="IS_AUTH" length="1" not-null="true" />
        </property>
        <property name="logonDay" type="timestamp">
            <column name="LOGON_DAY" length="7" not-null="true" />
        </property>
        <property name="logoffDay" type="timestamp">
            <column name="LOGOFF_DAY" length="7" not-null="true" />
        </property>
        <property name="osHost" type="string">
            <column name="OS_HOST" length="30" />
        </property>
        <property name="sidDes" type="string">
            <column name="SID_DES" length="40" />
        </property>
        <property name="creUser" type="string">
            <column name="CRE_USER" length="10" />
        </property>
        <property name="creDate" type="timestamp">
            <column name="CRE_DATE" length="7" />
        </property>
        <property name="maiUser" type="string">
            <column name="MAI_USER" length="10" />
        </property>
        <property name="maiDate" type="timestamp">
            <column name="MAI_DATE" length="7" />
        </property>
    </class>
    
    <!-- 轉檔記錄表格 -->
    <class name="Sys120m" table="SYS120M">
        <composite-id name="id" class="Sys120mId">
            <key-property name="transStart" type="timestamp">
                <column name="TRANS_START" length="7" />
            </key-property>
            <key-property name="fileName" type="string">
                <column name="FILE_NAME" length="60" />
            </key-property>
        </composite-id>
        <property name="transRecs" type="java.lang.Long">
            <column name="TRANS_RECS" precision="10" scale="0" />
        </property>
        <property name="transFailRecs" type="java.lang.Long">
            <column name="TRANS_FAILRECS" precision="10" scale="0" />
        </property>        
        <property name="transState" type="string">
            <column name="TRANS_STATE" length="1" />
        </property>
        <property name="transDes" type="string">
            <column name="TRANS_DES" length="200" />
        </property>
        <property name="creUser" type="string">
            <column name="CRE_USER" length="10" />
        </property>
        <property name="creDat" type="timestamp">
            <column name="CRE_DAT" length="7" />
        </property>
        <property name="maiUser" type="string">
            <column name="MAI_USER" length="10" />
        </property>
        <property name="maiDate" type="timestamp">
            <column name="MAI_DATE" length="7" />
        </property>
        <property name="transEnd" type="timestamp">
            <column name="TRANS_END" length="7" />
        </property>
        <property name="osHost" type="string">
            <column name="OS_HOST" length="30" />
        </property>
    </class>
    
	<!-- 授權主機表格  -->
    <class name="Sys122m" table="SYS122M" schema="DSA">
        <composite-id name="id" class="Sys122mId">
            <key-property name="userHost" type="string">
                <column name="USER_HOST" length="30" />
            </key-property>
            <key-property name="isAuth" type="string">
                <column name="IS_AUTH" length="1" />
            </key-property>
        </composite-id>
        <property name="ipAddress" type="string">
            <column name="IP_ADDRESS" length="15" />
        </property>
        <property name="hostDes" type="string">
            <column name="HOST_DES" length="100" />
        </property>
        <property name="type" column="TYPE"/>  
		<property name="creationDate" column="CRE_DAT"/>
		<property name="createdBy" column="CRE_USER"/>
		<property name="lastUpdateDate" column="MAI_DATE"/>
		<property name="lastUpdatedBy" column="MAI_USER"/>
    </class>  
    
</hibernate-mapping>

 */

