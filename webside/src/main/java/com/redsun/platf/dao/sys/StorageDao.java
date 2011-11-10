package com.redsun.platf.dao.sys;

import org.springframework.stereotype.Component;

import com.redsun.platf.dao.springside.HibernateDao;
import com.redsun.platf.entity.stock.MbStorage;

/**
 * 用户对象的泛型DAO类.
 * 
 * @author calvin
 */
@Component(value="storageDao")
public class StorageDao extends HibernateDao<MbStorage, Long> {
}
