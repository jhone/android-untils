package com.redsun.platf.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.redsun.platf.dao.sys.SystemConfigurationDao;
import com.redsun.platf.entity.IdEntity;
import com.redsun.platf.entity.sys.SystemCompany;
import com.redsun.platf.service.sys.IBaseEntityManager;

/**
 * 安全相关实体的管理类, 包括用户,角色,资源与授权类.
 * 
 * @author pyc version:2.0
 * 
 *         使用entityClass注入entity至此公共Bean 1.不再用systemDao companyDao...
 *         2.注意sessionFactory要定义在最后的属性 3.将每一个Dao 写在application-managers.xml
 */
// Spring Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class BaseEntityManagerImpl implements IBaseEntityManager {

	private static Logger logger = LoggerFactory
			.getLogger(BaseEntityManagerImpl.class);

	private SessionFactory sessionFactory;

	@Resource
	SystemConfigurationDao systemConfigurationDao;

	private Class<? extends IdEntity> entityClass;

	private HibernateDao<? extends IdEntity, Long> dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.batchcard.service.main.impl.IBaseEntityManager#init()
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void init() {
		dao = new HibernateDao(sessionFactory, entityClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.batchcard.service.main.impl.IBaseEntityManager#save(com.batchcard
	 * .entity.IdEntity)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.batchcard.service.main.impl.IBaseEntityManager#save(com.batchcard
	 * .entity.IdEntity)
	 */
	public void save(IdEntity entity) {
		dao.getSession().saveOrUpdate(entity);

		// dao.save(entity);
		logger.info("manager saved ok!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.batchcard.service.main.impl.IBaseEntityManager#delete(java.lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.batchcard.service.main.impl.IBaseEntityManager#delete(java.lang.Long)
	 */
	public void delete(Long id) {
		dao.delete(id);
		logger.info("manager deleted ok!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.batchcard.service.main.impl.IBaseEntityManager#deleteSystemCompany
	 * (java.lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.batchcard.service.main.impl.IBaseEntityManager#deleteSystemCompany
	 * (java.lang.Long)
	 */
	@Deprecated
	public void deleteSystemCompany(Long id) {
		dao.delete(id);
		logger.info("manager deleted ok!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.batchcard.service.main.impl.IBaseEntityManager#find(org.springside
	 * .modules.orm.Page, java.util.List)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.batchcard.service.main.impl.IBaseEntityManager#find(org.springside
	 * .modules.orm.Page, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page find(final Page page, final List<PropertyFilter> filters) {
		return dao.findPage(page, filters);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.batchcard.service.main.impl.IBaseEntityManager#findUniqueBy(java.
	 * lang.String, java.lang.Object)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.batchcard.service.main.impl.IBaseEntityManager#findUniqueBy(java.
	 * lang.String, java.lang.Object)
	 */
	@Transactional(readOnly = true)
	public IdEntity findUniqueBy(final String propertyName, final Object value) {
		return dao.findUniqueBy(propertyName, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.batchcard.service.main.impl.IBaseEntityManager#
	 * findSystemCompanyByCompanyNo(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.batchcard.service.main.impl.IBaseEntityManager#
	 * findSystemCompanyByCompanyNo(java.lang.String)
	 */
	@Transactional(readOnly = true)
	@Deprecated
	public SystemCompany findSystemCompanyByCompanyNo(String s) {
		return (SystemCompany) dao.findUniqueBy("companyNo", s);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.batchcard.service.main.impl.IBaseEntityManager#isPropertyUnique(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.batchcard.service.main.impl.IBaseEntityManager#isPropertyUnique(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public boolean isPropertyUnique(String fieldName, String newValue,
			String oldValue) {
		return dao.isPropertyUnique(fieldName, newValue, oldValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.batchcard.service.main.impl.IBaseEntityManager#getAll()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.batchcard.service.main.impl.IBaseEntityManager#getAll()
	 */
	@Transactional(readOnly = true)
	public List<? extends IdEntity> getAll() {
		return dao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.batchcard.service.main.impl.IBaseEntityManager#getEntityClass()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.batchcard.service.main.impl.IBaseEntityManager#getEntityClass()
	 */
	public Class<? extends IdEntity> getEntityClass() {
		return entityClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.batchcard.service.main.impl.IBaseEntityManager#setEntityClass(java
	 * .lang.Class)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.batchcard.service.main.impl.IBaseEntityManager#setEntityClass(java
	 * .lang.Class)
	 */
	public void setEntityClass(Class<? extends IdEntity> entityClass) {
		this.entityClass = entityClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.batchcard.service.main.impl.IBaseEntityManager#getSessionFactory()
	 */
	public SessionFactory getSessionFactory() {

		return sessionFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.batchcard.service.main.impl.IBaseEntityManager#setSessionFactory(
	 * org.hibernate.SessionFactory)
	 */
	// @SuppressWarnings("unchecked")
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.batchcard.service.main.impl.IBaseEntityManager#setSessionFactory(
	 * org.hibernate.SessionFactory)
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.batchcard.service.main.impl.IBaseEntityManager#getDao()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.batchcard.service.main.impl.IBaseEntityManager#getDao()
	 */
	public HibernateDao<? extends IdEntity, Long> getDao() {
		return dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.batchcard.service.main.impl.IBaseEntityManager#setDao(org.springside
	 * .modules.orm.hibernate.HibernateDao)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.batchcard.service.main.impl.IBaseEntityManager#setDao(org.springside
	 * .modules.orm.hibernate.HibernateDao)
	 */
	public void setDao(HibernateDao<? extends IdEntity, Long> dao) {
		this.dao = dao;
	}

}
