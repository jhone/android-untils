package com.redsun.platf.service.sys;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.redsun.platf.entity.IdEntity;
import com.redsun.platf.entity.sys.SystemCompany;

/**
 * <p>Title        : com.webapp        </p>
 * <p>Description  :                   </p>
 * <p>Copyright    : Copyright (c) 2011</p>
 * <p>Company      : FreedomSoft       </p>
 * 
 */

/**
 * @author dick pan 
 * @version 1.0
 * @since   1.0
 * <p><H3>Change history</H3></p>
 * <p>2011/3/18   : Created </p>
 *
 */
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional

public interface IBaseEntityManager {

    public abstract void init();

    /* (non-Javadoc)
     * @see com.batchcard.service.main.impl.IBaseEntityManager#save(com.batchcard.entity.IdEntity)
     */
    public abstract void save(IdEntity entity);

    /* (non-Javadoc)
     * @see com.batchcard.service.main.impl.IBaseEntityManager#delete(java.lang.Long)
     */
    public abstract void delete(Long id);

    /* (non-Javadoc)
     * @see com.batchcard.service.main.impl.IBaseEntityManager#deleteSystemCompany(java.lang.Long)
     */
    @Deprecated
    public abstract void deleteSystemCompany(Long id);

    /* (non-Javadoc)
     * @see com.batchcard.service.main.impl.IBaseEntityManager#find(org.springside.modules.orm.Page, java.util.List)
     */
    
    public abstract Page find(final Page page,
	    final List<PropertyFilter> filters);

    /* (non-Javadoc)
     * @see com.batchcard.service.main.impl.IBaseEntityManager#findUniqueBy(java.lang.String, java.lang.Object)
     */
    public abstract IdEntity findUniqueBy(final String propertyName,
	    final Object value);

    /* (non-Javadoc)
     * @see com.batchcard.service.main.impl.IBaseEntityManager#findSystemCompanyByCompanyNo(java.lang.String)
     */
    @Deprecated
    public abstract SystemCompany findSystemCompanyByCompanyNo(String s);

    /* (non-Javadoc)
     * @see com.batchcard.service.main.impl.IBaseEntityManager#isPropertyUnique(java.lang.String, java.lang.String, java.lang.String)
     */
    public abstract boolean isPropertyUnique(String fieldName, String newValue,
	    String oldValue);

    /* (non-Javadoc)
     * @see com.batchcard.service.main.impl.IBaseEntityManager#getAll()
     */
    public abstract List<? extends IdEntity> getAll();

    /* (non-Javadoc)
     * @see com.batchcard.service.main.impl.IBaseEntityManager#getEntityClass()
     */
    public abstract Class<? extends IdEntity> getEntityClass();

    /* (non-Javadoc)
     * @see com.batchcard.service.main.impl.IBaseEntityManager#setEntityClass(java.lang.Class)
     */
    public abstract void setEntityClass(Class<? extends IdEntity> entityClass);

    public abstract SessionFactory getSessionFactory();

    /* (non-Javadoc)
     * @see com.batchcard.service.main.impl.IBaseEntityManager#setSessionFactory(org.hibernate.SessionFactory)
     */
    //    @SuppressWarnings("unchecked")
    public abstract void setSessionFactory(SessionFactory sessionFactory);

    /* (non-Javadoc)
     * @see com.batchcard.service.main.impl.IBaseEntityManager#getDao()
     */
    public abstract HibernateDao<? extends IdEntity, Long> getDao();

    /* (non-Javadoc)
     * @see com.batchcard.service.main.impl.IBaseEntityManager#setDao(org.springside.modules.orm.hibernate.HibernateDao)
     */
    public abstract void setDao(HibernateDao<? extends IdEntity, Long> dao);

}