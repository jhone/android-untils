package com.redsun.platf.dao.sys;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.redsun.platf.entity.sys.SystemUser;

/**
 * 用户对象的泛型DAO类.
 * 
 * @author calvin
 */
@Component
public class SystemUserDao extends HibernateDao<SystemUser, Long> {
}
