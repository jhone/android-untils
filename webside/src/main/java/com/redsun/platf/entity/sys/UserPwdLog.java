package com.redsun.platf.entity.sys;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.redsun.platf.entity.BaseEntity;

/**
 * <p>Title: com.walsin.platf.orm.entity.UserPwdLog</p>
 * <p>Description: 使用者密碼歷史紀錄主檔EP_SYS_USER_PWD_LOG</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: FreeLance</p>
 * @author 
 * @version 1.0
 */

@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "SYS_USERPWDLOG")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserPwdLog extends BaseEntity{

    private static final long serialVersionUID = -6599219397888530045L;
    

    /** 使用者帳號 */
    private String userId;

    /** 密碼 */
    private String password;

    /** 密碼建立日期 */
    private Date pwdCreationDate;

//    // 多对多定义
//    @ManyToMany
//    // 中间表定义,表名采用默认命名规则
//    @JoinTable(name = "SYS_USER_LOG", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "LOG_ID") })
//    // Fecth策略定义
//    @Fetch(FetchMode.SUBSELECT)
//    // 集合按id排序.
//    @OrderBy("id")
//    // 集合中对象id的缓存.
//    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getPwdCreationDate() {
        return pwdCreationDate;
    }

    public void setPwdCreationDate(Date pwdCreationDate) {
        this.pwdCreationDate = pwdCreationDate;
    }
}
