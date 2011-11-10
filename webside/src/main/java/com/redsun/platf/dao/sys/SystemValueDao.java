package com.redsun.platf.dao.sys;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.redsun.platf.dao.base.AbstractPagedDao;
import com.redsun.platf.entity.sys.SystemValue;

/**
 * 用户对象的泛型DAO类.
 * 
 * @author calvin
 */
@Component
public class SystemValueDao extends AbstractPagedDao<SystemValue, Long> {

	public SystemValueDao() {
		super();
		this.entityClass=SystemValue.class;
	}


	
}
