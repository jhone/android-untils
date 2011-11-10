package com.redsun.platf.dao.sys;

import org.springframework.stereotype.Repository;

import com.redsun.platf.dao.base.AbstractDao;
import com.redsun.platf.entity.sys.SystemLanguage;

@Repository
public class SystemLanguageDao extends AbstractDao<SystemLanguage, Long> {

	public SystemLanguageDao() {
		super();
		this.entityClass = SystemLanguage.class;
	}

}
