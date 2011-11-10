package com.redsun.platf.service.sys.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.redsun.platf.dao.DataAccessObjectFactory;

/**
 * 安全相关实体的管理类, 包括用户,角色,资源与授权类.
 * 
 * @author calvin
 */
// Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class MainManager_old {

//    private static Logger logger = LoggerFactory.getLogger(MainManager.class);

    @Resource(name = "dataAccessObjectFactory")
    DataAccessObjectFactory daoFactory;

    // -- SystemUser Manager --//
//    @Transactional(readOnly = true)
//    public SystemUser getSystemUser(Long id) {
//	return daoFactory.getSystemUserDao().get(id);
//    }
//
//    public void saveSystemUser(SystemUser entity) {
//	daoFactory.getSystemUserDao().save(entity);
//    }
//    
//    public void saveSystemCompany(SystemCompany entity) {
//	daoFactory.getSystemCompanyDao().save(entity);
//    }
//
//    /**
//     * 删除.
//     */
//    public void deleteSystemUser(Long id) {
//	daoFactory.getSystemUserDao().delete(id);
//    }
//
//    /**
//     * 使用属性过滤条件查询.
//     */
//    @Transactional(readOnly = true)
//    public Page<SystemUser> searchSystemUser(final Page<SystemUser> page,
//	    final List<PropertyFilter> filters) {
//	return daoFactory.getSystemUserDao().findPage(page, filters);
//    }
//
//    @Transactional(readOnly = true)
//    public SystemUser findSystemUserByLoginName(String loginName) {
//	return daoFactory.getSystemUserDao().findUniqueBy("loginName",
//		loginName);
//    }
//
//    /**
//     * 检查用户名是否唯一.
//     * 
//     * @return loginName在数据库中唯一或等于oldLoginName时返回true.
//     */
//    @Transactional(readOnly = true)
//    public boolean isLoginNameUnique(String newLoginName, String oldLoginName) {
//	return daoFactory.getSystemUserDao().isPropertyUnique("loginName",
//		newLoginName, oldLoginName);
//    }

    public DataAccessObjectFactory getDaoFactory() {
        return daoFactory;
    }

    public void setDaoFactory(DataAccessObjectFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

}
