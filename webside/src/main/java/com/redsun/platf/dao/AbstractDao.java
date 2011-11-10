package com.redsun.platf.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.ParameterTranslations;
import org.hibernate.hql.classic.QueryTranslatorImpl;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class AbstractDao<T, PK extends Serializable> extends
		HibernateDaoSupport implements IBaseDao<T, PK> {
	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;

	protected Class<T> entityClass;

	// 为父类HibernateDaoSupport注入sessionFactory的值
	/* (non-Javadoc)
	 * @see com.redsun.platf.dao.IDao#setSuperSessionFactory(org.hibernate.SessionFactory)
	 */
	@Override
	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	/* (non-Javadoc)
	 * @see com.redsun.platf.dao.IDao#findById(java.lang.Integer)
	 */
	@Override
	public T findById(Integer id) {
		return getHibernateTemplate().get(getEntityClass(), id);
	}

	/* (non-Javadoc)
	 * @see com.redsun.platf.dao.IDao#findById(java.lang.String)
	 */
	@Override
	public T findById(String id) {
		return getHibernateTemplate().get(getEntityClass(), id);
	}

	/* (non-Javadoc)
	 * @see com.redsun.platf.dao.IDao#getAll()
	 */
	@Override
	public List<T> getAll() {
		return loadAll();
	}

	/* (non-Javadoc)
	 * @see com.redsun.platf.dao.IDao#loadAll()
	 */
	@Override
	public List<T> loadAll() {
		return getHibernateTemplate().loadAll(getEntityClass());
	}

	/* (non-Javadoc)
	 * @see com.redsun.platf.dao.IDao#save(T)
	 */
	@Override
	public void save(T entity) {
		if (entity != null)
			getHibernateTemplate().save(entity);
	}

	// @Override
	/* (non-Javadoc)
	 * @see com.redsun.platf.dao.IDao#saveAll(java.util.Collection)
	 */
	@Override
	public void saveAll(Collection<T> entitys) {
		if (entitys != null) {
			for (Iterator<T> it = entitys.iterator(); it.hasNext();) {
				getHibernateTemplate().save(it.next());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.redsun.platf.dao.IDao#update(T)
	 */
	@Override
	public void update(T entity) {
		if (entity != null)
			getHibernateTemplate().update(entity);
	}

	/* (non-Javadoc)
	 * @see com.redsun.platf.dao.IDao#saveOrUpdate(T)
	 */
	@Override
	public void saveOrUpdate(T entity) {
		if (entity != null)
			getHibernateTemplate().saveOrUpdate(entity);
	}

	/* (non-Javadoc)
	 * @see com.redsun.platf.dao.IDao#saveOrUpdateAll(T[])
	 */
	@Override
	public void saveOrUpdateAll(T[] entities) {
		if (entities != null)
			getHibernateTemplate().saveOrUpdateAll(Arrays.asList(entities));
	}

	/* (non-Javadoc)
	 * @see com.redsun.platf.dao.IDao#saveOrUpdateAll(java.util.Collection)
	 */
	@Override
	public void saveOrUpdateAll(Collection<T> entities) {
		if (entities != null)
			getHibernateTemplate().saveOrUpdateAll(entities);
	}

	/* (non-Javadoc)
	 * @see com.redsun.platf.dao.IDao#delete(T)
	 */
	@Override
	public void delete(T entity) {
		if (entity != null)
			getHibernateTemplate().delete(entity);
	}

	/* (non-Javadoc)
	 * @see com.redsun.platf.dao.IDao#deleteAll(T[])
	 */
	@Override
	public void deleteAll(T[] entities) {
		if (entities != null)
			getHibernateTemplate().deleteAll(Arrays.asList(entities));
	}

	/* (non-Javadoc)
	 * @see com.redsun.platf.dao.IDao#deleteAll(java.util.Collection)
	 */
	@Override
	public void deleteAll(Collection<T> entities) {
		if (entities != null)
			getHibernateTemplate().deleteAll(entities);
	}

	/* (non-Javadoc)
	 * @see com.redsun.platf.dao.IDao#refresh(T)
	 */
	@Override
	public void refresh(T entity) {
		if (entity != null)
			getHibernateTemplate().refresh(entity);
	}

	/* (non-Javadoc)
	 * @see com.redsun.platf.dao.IDao#flush()
	 */
	@Override
	public void flush() {
		getHibernateTemplate().flush();
	}

	/* (non-Javadoc)
	 * @see com.redsun.platf.dao.IDao#count(java.lang.String, java.lang.Object[], java.util.Map)
	 */
	@Override
	public int count(final String queryString, final Object[] params,
			final Map<String, Object> namedParams) {
		if (params != null)
			return count(queryString, params);
		if (namedParams != null)
			return count(queryString, namedParams);
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.redsun.platf.dao.IDao#find(java.lang.String, java.lang.Object[], java.util.Map, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> find(final String queryString, final Object[] params,
			final Map<String, Object> namedParams, final Integer firstResult,
			final Integer maxResults) {
		return (List<T>) this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = wrapQuery(session, queryString, params,
								namedParams);
						if (firstResult != null)
							query.setFirstResult(firstResult.intValue());
						if (maxResults != null)
							query.setMaxResults(maxResults.intValue());
						return query.list();
					}
				});
	}

	/**
	 * 基本共用查詢(for單一回傳值)
	 * 
	 * @param hql
	 *            查詢字串HQL
	 * @param nameParams
	 *            名字與值對應參數Map
	 * @return 返回一個單一值
	 */
	protected Object unique(final String hql,
			final Map<String, Object> nameParams) {
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = (Query) session.createQuery(hql);
				for (Iterator<String> it = nameParams.keySet().iterator(); it
						.hasNext();) {
					String name = it.next();
					Object value = nameParams.get(name);
					if (value.getClass().isArray()) {
						query.setParameterList(name, (Object[]) value);
					} else {
						query.setParameter(name, value);
					}
				}
				return query.uniqueResult();
			}
		});
	}

	protected Object Iterate(final String hql,
			final Map<String, Object> nameParams) {
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				for (Iterator<String> it = nameParams.keySet().iterator(); it
						.hasNext();) {
					String name = it.next();
					Object value = nameParams.get(name);
					if (value.getClass().isArray()) {
						query.setParameterList(name, (Object[]) value);
					} else {
						query.setParameter(name, value);
					}
				}
				return query.iterate();
			}
		});
	}

	/**
	 * 基本共用查詢(for單一回傳值)
	 * 
	 * @param hql
	 *            查詢字串HQL
	 * @param params
	 *            值陣列
	 * @return 返回一個單一值
	 */
	protected Object unique(final String hql, final Object[] params) {
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				for (int i = 0, size = params.length; i < size; i++) {
					query.setParameter(i, params[i]);
				}
				return query.uniqueResult();
			}
		});
	}

	/**
	 * 基本共用查詢
	 * 
	 * @param queryString
	 *            查詢字串HQL
	 * @return 返回集合物件List<T>
	 */
	@SuppressWarnings("unchecked")
	protected List<T> find(String queryString) {
		return (List<T>) getHibernateTemplate().find(queryString);
	}

	/**
	 * 基本共用查詢
	 * 
	 * @param queryString
	 *            查詢字串HQL
	 * @param values
	 *            值陣列
	 * @return 返回集合物件List<T>
	 */
	@SuppressWarnings("unchecked")
	protected List<T> find(StringBuffer queryString, List<Object> values) {
		return (List<T>) getHibernateTemplate().find(queryString.toString(),
				values.toArray());
	}

	/**
	 * 基本共用查詢
	 * 
	 * @param queryString
	 *            查詢字串HQL
	 * @param values
	 *            值陣列
	 * @return 返回集合物件List<T>
	 */
	@SuppressWarnings("unchecked")
	protected List<T> find(String queryString, Object[] values) {
		return (List<T>) getHibernateTemplate().find(queryString, values);
	}

	/**
	 * 基本共用查詢
	 * 
	 * @param queryString
	 *            查詢字串HQL
	 * @param names
	 *            名稱陣列
	 * @param values
	 *            值陣列
	 * @return 返回集合物件List<T>
	 */
	@SuppressWarnings("unchecked")
	protected List<T> find(String queryString, String[] names, Object[] values) {
		return (List<T>) getHibernateTemplate().findByNamedParam(queryString,
				names, values);
	}

	/**
	 * 基本共用查詢
	 * 
	 * @param queryString
	 *            查詢字串HQL
	 * @param nameParams
	 *            名字與值對應參數Map
	 * @return 返回集合物件List<T>
	 */
	protected List<T> find(StringBuffer queryString,
			Map<String, Object> nameParams) {
		return find(queryString.toString(), nameParams);
	}

	/**
	 * 基本共用查詢
	 * 
	 * @param queryString
	 *            查詢字串HQL
	 * @param nameParams
	 *            名字與值對應參數Map
	 * @return 返回集合物件List<T>
	 */
	protected List<T> find(String queryString, Map<String, Object> nameParams) {
		Object[] keys = (Object[]) nameParams.keySet().toArray();
		List<String> names = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		for (Object name : keys) {
			Object value = nameParams.get(name);
			if (value != null) {
				names.add(name.toString());
				values.add(value);
			}
		}
		String[] nameArray = new String[names.size()];
		for (int i = 0; i < names.size(); i++)
			nameArray[i] = names.get(i);
		return find(queryString, nameArray, values.toArray());
	}

	/**
	 * 建立查詢參數
	 * 
	 * @param session
	 *            Session物件
	 * @param queryString
	 *            查詢字串HQL
	 * @param params
	 *            陣列參數
	 * @param namedParams
	 *            名字與值對應參數Map
	 * @return 返回Query物件
	 */
	private Query wrapQuery(Session session, String queryString,
			Object[] params, Map<String, Object> namedParams) {
		Query query = session.createQuery(queryString);

		if (params != null) {
			for (int i = 0, size = params.length; i < size; i++)
				query.setParameter(i, params[i]);
		}

		if (namedParams != null) {
			for (Iterator<String> it = namedParams.keySet().iterator(); it
					.hasNext();) {
				String name = it.next();
				Object value = namedParams.get(name);
				if (value != null) {
					if (value.getClass().isArray()) {
						query.setParameterList(name, (Object[]) value);
					} else if (value instanceof Collection<?>) {
						query.setParameterList(name, (Collection<?>) value);
					} else {
						query.setParameter(name, value);
					}
				}
			}
		}
		return query;
	}

	/**
	 * 查詢總筆數
	 * 
	 * @param queryString
	 *            查詢字串HQL
	 * @param namedParams
	 *            名字與值對應參數Map
	 * @return 返回總筆數
	 */
	private int count(final String queryString,
			final Map<String, Object> namedParams) {
		// 取得HQL=>SQL
		QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(
				queryString,
				queryString,
				Collections.EMPTY_MAP,
				(org.hibernate.engine.SessionFactoryImplementor) getSessionFactory());
		queryTranslator.compile(Collections.EMPTY_MAP, false);
		String sql = "select count(*) from (" + queryTranslator.getSQLString()
				+ ") tmp_count_t";

		ParameterTranslations parameterTranslations = queryTranslator
				.getParameterTranslations();
		final Object[] paramList = new Object[parameterTranslations
				.getNamedParameterNames().size()];
		for (Object obj : parameterTranslations.getNamedParameterNames()) {
			int[] index = parameterTranslations
					.getNamedParameterSqlLocations(obj.toString());
			if (index.length > 0)
				paramList[index[0]] = obj.toString();
		}

		// 重組SQL
		int index = 0;
		final StringBuffer adujSQL = new StringBuffer();
		for (int i = 0, size = sql.length(); i < size; i++) {
			adujSQL.append(sql.charAt(i) == '?' ? (":" + paramList[index++])
					: sql.charAt(i));
		}

		// 進行SQL查詢
		BigDecimal count = (BigDecimal) this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(adujSQL.toString());
						for (Iterator<String> it = namedParams.keySet()
								.iterator(); it.hasNext();) {
							String key = it.next();
							Object value = namedParams.get(key);
							if (value != null) {
								if (value.getClass().isArray()) {
									query.setParameterList(key,
											(Object[]) value);
								} else {
									query.setParameter(key, value);
								}
							}
						}
						return query.uniqueResult();
					}
				});

		return (count == null) ? 0 : count.intValue();
	}

	/* (non-Javadoc)
	 * @see com.redsun.platf.dao.IDao#count(java.lang.String, java.lang.Object[])
	 */
	@Override
	public int count(final String queryString, final Object[] params) {

		// 取得HQL=>SQL
		QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(
				queryString,
				queryString,
				Collections.EMPTY_MAP,
				(org.hibernate.engine.SessionFactoryImplementor) getSessionFactory());
		queryTranslator.compile(Collections.EMPTY_MAP, false);
		final String sql = "select count(*) from ("
				+ queryTranslator.getSQLString() + ") tmp_count_t";

		// 進行SQL查詢
		BigDecimal count = (BigDecimal) this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql);
						for (int i = 0, size = params.length; i < size; i++) {
							query.setParameter(i, params[i]);
						}
						return query.uniqueResult();
					}
				});

		return (count == null) ? 0 : count.intValue();
	}

	/* (non-Javadoc)
	 * @see com.redsun.platf.dao.IDao#getEntityClass()
	 */
	@Override
	public Class<T> getEntityClass() {
		return entityClass;
	}

	/* (non-Javadoc)
	 * @see com.redsun.platf.dao.IDao#setEntityClass(java.lang.Class)
	 */
	@Override
	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	
}
