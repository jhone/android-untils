package com.redsun.platf.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;

public interface IBaseDao<T, PK > {

	// 为父类HibernateDaoSupport注入sessionFactory的值
//	@Resource(name = "sessionFactory")
	public abstract void setSuperSessionFactory(SessionFactory sessionFactory);

	public abstract T findById(Integer id);

	public abstract T findById(String id);

	/**
	 * 获取全部对象.
	 */
	public abstract List<T> getAll();

	public abstract List<T> loadAll();

	public abstract void save(T entity);

	// @Override
	public abstract void saveAll(Collection<T> entitys);

	public abstract void update(T entity);

	public abstract void saveOrUpdate(T entity);

	public abstract void saveOrUpdateAll(T[] entities);

	public abstract void saveOrUpdateAll(Collection<T> entities);

	public abstract void delete(T entity);

	public abstract void deleteAll(T[] entities);

	public abstract void deleteAll(Collection<T> entities);

	public abstract void refresh(T entity);

	public abstract void flush();

	public abstract int count(final String queryString, final Object[] params,
			final Map<String, Object> namedParams);

	public abstract List<T> find(final String queryString,
			final Object[] params, final Map<String, Object> namedParams,
			final Integer firstResult, final Integer maxResults);

	/**
	 * 查詢總筆數
	 * 
	 * @param queryString
	 *            查詢字串HQL
	 * @param params
	 *            陣列參數
	 * @return 返回總筆數
	 */
	public abstract int count(final String queryString, final Object[] params);

	public abstract Class<T> getEntityClass();

	public abstract void setEntityClass(Class<T> entityClass);

}