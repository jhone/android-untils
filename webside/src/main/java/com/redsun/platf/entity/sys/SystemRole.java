package com.redsun.platf.entity.sys;

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

/**
 * 角色.
 * 
 * 注释见{@link SystemUser}.
 * 
 * @author
 */
@Entity
@Table(name = "SYS_ROLE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SystemRole extends BaseEntity {

    private static final long serialVersionUID = -8793039837587333429L;
    private String name;
    private List<SystemAuthority> authorityList = Lists.newArrayList();

    public SystemRole() {

    }

    public SystemRole(Long id, String name) {
	this.id = id;
	this.name = name;
    }

    @Column(nullable = false, unique = true)
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @ManyToMany
    @JoinTable(name = "SYS_ROLE_AUTHORITY", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public List<SystemAuthority> getAuthorityList() {
	return authorityList;
    }

    public void setAuthorityList(List<SystemAuthority> authorityList) {
	this.authorityList = authorityList;
    }

    @Transient
    public String getAuthNames() {
	return ConvertUtils.convertElementPropertyToString(authorityList,
		"name", ", ");
    }

    @Transient
    @SuppressWarnings("unchecked")
    public List<Long> getAuthIds() {
	return ConvertUtils.convertElementPropertyToList(authorityList, "id");
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
}
