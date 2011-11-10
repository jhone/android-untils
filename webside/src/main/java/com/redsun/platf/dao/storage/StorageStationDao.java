package com.redsun.platf.dao.storage;

import org.springframework.stereotype.Component;

import com.redsun.platf.dao.base.AbstractPagedDao;
import com.redsun.platf.entity.sys.StorageStation;

/**
 * 用户对象的泛型DAO类.
 * 
 * @author calvin
 */
@Component
public class StorageStationDao extends AbstractPagedDao<StorageStation, Long> {
	public StorageStationDao() {
		super();
		this.entityClass = StorageStation.class;
	}
}
