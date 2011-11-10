package com.redsun.platf.dao.sys;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.redsun.platf.entity.sys.SystemAuthority;

/**
 * 授权对象的泛型DAO.
 * 
 * @author calvin
 */
@Component
public class SystemAuthorityDao extends HibernateDao<SystemAuthority, Long> {

	public SystemAuthorityDao() {
		super();
		this.entityClass=SystemAuthority.class;
	}
}
