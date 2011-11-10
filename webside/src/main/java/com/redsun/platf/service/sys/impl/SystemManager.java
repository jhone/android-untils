package com.redsun.platf.service.sys.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.redsun.platf.dao.sys.SystemCompanyDao;
import com.redsun.platf.dao.sys.SystemConfigurationDao;
import com.redsun.platf.entity.sys.SystemCompany;

/**
 * 安全相关实体的管理类, 包括用户,角色,资源与授权类.
 * 
 * @author calvin
 */
// Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class SystemManager {

    private static Logger logger = LoggerFactory.getLogger(SystemManager.class);
   
    @Autowired
    SystemConfigurationDao systemConfigurationDao;

    @Autowired
    SystemCompanyDao systemCompanyDao;

 
    
    /***** system Company ********/
    @Transactional(readOnly = true)
    public SystemCompany getSystemCompany(Long id) {
	return systemCompanyDao.get(id);
    }

    public void saveSystemCompany(SystemCompany entity) {
	systemCompanyDao.save(entity);
	logger.info("saved ok!");
    }

    /*** 删除. ******/
    public void deleteSystemCompany(Long id) {
	systemCompanyDao.delete(id);
    }

    /*** 使用属性过滤条件查询. ***/
    @Transactional(readOnly = true)
    public Page<SystemCompany> searchSystemCompany(
	    final Page<SystemCompany> page, final List<PropertyFilter> filters) {
	return systemCompanyDao.findPage(page, filters);
    }

    /**** 按编号查找 ***********/
    @Transactional(readOnly = true)
    public SystemCompany findSystemCompanyByCompanyNo(String s) {
	return systemCompanyDao.findUniqueBy("companyNo", s);
    }

    /**
     * 检查是否唯一.
     * 
     * @return No在数据库中唯一或等于oldValue时返回true.
     */
    @Transactional(readOnly = true)
    public boolean isPropertyUnique(String fieldName, String newValue,
	    String oldValue) {
	return systemCompanyDao.isPropertyUnique(fieldName, newValue, oldValue);
    }

    /**
     * @return list all
     */
    public List<SystemCompany> getAll() {
	return systemCompanyDao.getAll();
    }

   

    /****************** end of Company *******************/
   

    
}
