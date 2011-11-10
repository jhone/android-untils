package com.redsun.platf.dao.sys;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.redsun.platf.sys.SystemConfiguration;

/**
 * 用户对象的泛型DAO类.
 * 
 * @author calvin
 */
@Component
public class SystemConfigurationDao extends
		HibernateDao<SystemConfiguration, Long> {

	public SystemConfigurationDao() {
		super();
		this.entityClass = SystemConfiguration.class;
	}

}
