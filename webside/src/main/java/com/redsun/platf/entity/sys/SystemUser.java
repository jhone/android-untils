package com.redsun.platf.entity.sys;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springside.modules.utils.reflection.ConvertUtils;

import com.google.common.collect.Lists;
import com.redsun.platf.entity.BaseEntity;
import com.redsun.platf.entity.account.Role;

/**
 * <p>
 * Title: com.walsin.platf.orm.entity.SystemUser
 * </p>
 * <p>
 * Description: 使用者主檔EP_SYS_USER
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: FreeLance
 * </p>
 * 
 * @author
 * @version 1.0
 */

@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "SYS_USER")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SystemUser extends BaseEntity {

    private static final long serialVersionUID = -5398638918496099191L;

    /** 使用者帳號 */
    private String username;

    /** 使用者密碼 */
    private String password;

    /** Y:必須變更密碼 */
    private String resetPasswordFlag;

    /** 密碼錯誤次數 */
    private Integer pwdErrorcount;

    /** 上次變更密碼時間 */
    private Date lasttspChgpwd;

    /** 保留: 可以使用ep欄位 */
    private String epEnabled;
    /** email **/
    private String email;

    /** role **/
    private List<Role> roleList = Lists.newArrayList();// 有序的关联对象集合

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getResetPasswordFlag() {
	return resetPasswordFlag;
    }

    public void setResetPasswordFlag(String resetPasswordFlag) {
	this.resetPasswordFlag = resetPasswordFlag;
    }

    public Integer getPwdErrorcount() {
	return pwdErrorcount;
    }

    public void setPwdErrorcount(Integer pwdErrorcount) {
	this.pwdErrorcount = pwdErrorcount;
    }

    public Date getLasttspChgpwd() {
	return lasttspChgpwd;
    }

    public void setLasttspChgpwd(Date lasttspChgpwd) {
	this.lasttspChgpwd = lasttspChgpwd;
    }

    public String getEpEnabled() {
	return epEnabled;
    }

    public void setEpEnabled(String epEnabled) {
	this.epEnabled = epEnabled;
    }

    // 字段非空且唯一, 用于提醒Entity使用者及生成DDL.
    @Column(nullable = false, unique = true)
    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    // 多对多定义
    @ManyToMany
    // 中间表定义,表名采用默认命名规则
    @JoinTable(name = "SYS_USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
    // Fecth策略定义
    @Fetch(FetchMode.SUBSELECT)
    // 集合按id排序.
    @OrderBy("id")
    // 集合中对象id的缓存.
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public List<Role> getRoleList() {
	return roleList;
    }

    public void setRoleList(List<Role> roleList) {
	this.roleList = roleList;
    }

    /**
     * 用户拥有的角色名称字符串, 多个角色名称用','分隔.
     */
    // 非持久化属性.
   @Transient
    public String getRoleNames() {
	return ConvertUtils.convertElementPropertyToString(roleList, "name",
		", ");
    }

    /**
     * 用户拥有的角色id字符串, 多个角色id用','分隔.
     */
//    // 非持久化属性.
    @Transient
    @SuppressWarnings("unchecked")
    public List<Long> getRoleIds() {
	return ConvertUtils.convertElementPropertyToList(roleList, "id");
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }
}
