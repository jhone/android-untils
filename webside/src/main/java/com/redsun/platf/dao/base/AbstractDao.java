package com.redsun.platf.dao.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.hql.ParameterTranslations;
import org.hibernate.hql.classic.QueryTranslatorImpl;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.PropertyFilter.MatchType;

/**
 * 基本的DAO.
 * 
 * 可在Service层直接使用, 也可以扩展泛型DAO子类使用
 * 
 * 使用HibernateTemplate.HibernateDaoSupport
 * 
 * @param <T>
 *            DAO操作的对象类型
 * @param <PK>
 *            主键类型
 * 
 * @author 1.0
 * 
 * 
 */

@Transactional
@Repository
@SuppressWarnings("unchecked")
public class AbstractDao<T, PK extends Serializable> extends
		HibernateDaoSupport implements IBaseDao<T, PK> {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;

	protected Class<T> entityClass;

	/*
	 * 为父类HibernateDaoSupport注入sessionFactory的值 (non-Javadoc)
	 * 
	 * @see
	 * com.redsun.platf.dao.IDao#setSuperSessionFactory(org.hibernate.SessionFactory
	 * )
	 */
	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * eg. SimpleHibernateDao<User, Long> userDao = new SimpleHibernateDao<User,
	 * Long>(sessionFactory, User.class);
	 */
	public AbstractDao(final SessionFactory sessionFactory,
			final Class<T> entityClass) {
		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
	}
	public AbstractDao() {
		super();
	}

	

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 * @return 更新记录数.
	 */
	@Override
	public int batchExecute(final String hql, final Map<String, ?> values) {
		return createQuery(hql, values).executeUpdate();
	}

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 * @return 更新记录数.
	 */
	@Override
	public int batchExecute(final String hql, final Object... values) {
		return createQuery(hql, values).executeUpdate();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redsun.platf.dao.IDao#count(java.lang.String,
	 * java.lang.Object[])
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redsun.platf.dao.IDao#count(java.lang.String,
	 * java.lang.Object[], java.util.Map)
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

	/**
	 * 根据Criterion条件创建Criteria. 与find()函数可进行更加灵活的操作.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 */
	@Override
	public Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象. 与find()函数可进行更加灵活的操作.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	@Override
	public Query createQuery(final String queryString,
			final Map<String, ?> values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象. 与find()函数可进行更加灵活的操作.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	@Override
	public Query createQuery(final String queryString, final Object... values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	@Override
	public void delete(final PK id) {
		Assert.notNull(id, "id不能为空");
		delete(get(id));
		logger.debug("delete entity {},id is {}", entityClass.getSimpleName(),
				id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redsun.platf.dao.IDao#delete(T)
	 */
	@Override
	public void delete(T entity) {
		// if (entity != null)
		// getHibernateTemplate().delete(entity);
		Assert.notNull(entity, "entity不能为空");
		System.out.println("delete...");
		getSession().delete(entity);
//		getHibernateTemplate().delete(entity);
		logger.debug("delete entity: {}",  entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redsun.platf.dao.IDao#deleteAll(java.util.Collection)
	 */
	@Override
	public void deleteAll(Collection<T> entities) {
		if (entities != null)
			getHibernateTemplate().deleteAll(entities);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redsun.platf.dao.IDao#deleteAll(T[])
	 */
	@Override
	public void deleteAll(T[] entities) {
		if (entities != null)
			getHibernateTemplate().deleteAll(Arrays.asList(entities));
	}

	/**
	 * 2. 为Criteria添加distinct transformer. 预加载关联对象的HQL会引起主对象重复, 需要进行distinct处理.
	 */
	public Criteria distinct(Criteria criteria) {
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return criteria;
	}

	/**
	 * 2. 为Query添加distinct transformer. 预加载关联对象的HQL会引起主对象重复, 需要进行distinct处理.
	 */
	public Query distinct(Query query) {
		query.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return query;
	}

	/**
	 * 按Criteria查询对象列表.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 */

	@Override
	public List<T> find(final Criterion... criterions) {
		return createCriteria(criterions).list();
	}

	@Override
	public List<T> find(List<PropertyFilter> filters) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 基本共用查詢
	 * 
	 * @param queryString
	 *            查詢字串HQL
	 * @return 返回集合物件List<T>
	 */
	protected List<T> find(String queryString) {
		return (List<T>) getHibernateTemplate().find(queryString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redsun.platf.dao.springside.S#find(java.lang.String,
	 * java.util.Map)
	 */
	@Override
	public <X> List<X> find(final String hql, final Map<String, ?> values) {
		return createQuery(hql, values).list();
	}

	@Override
	public <X> List<X> find(String hql, Object... values) {

		return createQuery(hql, values).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redsun.platf.dao.IDao#find(java.lang.String, java.lang.Object[],
	 * java.util.Map, java.lang.Integer, java.lang.Integer)
	 */
	@Override
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
	protected List<T> find(String queryString, String[] names, Object[] values) {
		return (List<T>) getHibernateTemplate().findByNamedParam(queryString,
				names, values);
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
	protected List<T> find(StringBuffer queryString, List<Object> values) {
		return (List<T>) getHibernateTemplate().find(queryString.toString(),
				values.toArray());
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
	 * 按属性查找对象列表, 匹配方式为相等.
	 */
	public List<T> findBy(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return find(criterion);
	}

	@Override
	public List<T> findBy(String propertyName, Object value, MatchType matchType) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return find(criterion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redsun.platf.dao.IDao#findById(java.lang.Integer)
	 */
	@Override
	public T findById(PK id) {
		return getHibernateTemplate().get(getEntityClass(), id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redsun.platf.dao.IDao#findById(java.lang.String)
	 */
	@Override
	public T findById(String id) {
		return getHibernateTemplate().get(getEntityClass(), id);
	}

	/**
	 * 按Criteria查询唯一对象.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 */
	@Override
	public T findUnique(final Criterion... criterions) {
		return (T) createCriteria(criterions).uniqueResult();
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

	@Override
	public <X> X findUnique(final String hql, final Map<String, ?> values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	@Override
	public <X> X findUnique(final String hql, final Object... values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	/**
	 * 按属性查找唯一对象, 匹配方式为相等.
	 */
	public T findUniqueBy(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(criterion).uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redsun.platf.dao.IDao#flush()
	 */
	@Override
	public void flush() {
		getHibernateTemplate().flush();
	}

	/**
	 * 按id列表获取对象列表.
	 */
	@Override
	public List<T> get(final Collection<PK> ids) {
		return find(Restrictions.in(getIdName(), ids));
	}

	@Override
	public T get(final PK id) {
		Assert.notNull(id, "id不能为空");
		return (T) getSession().load(entityClass, id);
	}

	@Override
	public List<T> getAll() {
		return find();
	}

	@Override
	public List<T> getAll(String orderByProperty, boolean isAsc) {
		Criteria c = createCriteria();
		if (isAsc) {
			c.addOrder(Order.asc(orderByProperty));
		} else {
			c.addOrder(Order.desc(orderByProperty));
		}
		return c.list();
	}

	/**
	 * 2. 取得对象的主键名.
	 */
	public String getIdName() {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		return meta.getIdentifierPropertyName();
	}

	/**
	 * 初始化对象. 使用load()方法得到的仅是对象Proxy, 在传到View层前需要进行初始化. 如果传入entity,
	 * 则只初始化entity的直接属性,但不会初始化延迟加载的关联集合和属性. 如需初始化关联属性,需执行:
	 * Hibernate.initialize(user.getRoles())，初始化User的直接属性和关联集合.
	 * Hibernate.initialize
	 * (user.getDescription())，初始化User的直接属性和延迟加载的Description属性.
	 */
	@Override
	public void initProxyObject(Object proxy) {
		Hibernate.initialize(proxy);
	}

	/**
	 * 2 判断对象的属性值在数据库内是否唯一.
	 * 
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
	 */
	public boolean isPropertyUnique(final String propertyName,
			final Object newValue, final Object oldValue) {
		if (newValue == null || newValue.equals(oldValue)) {
			return true;
		}
		Object object = findUniqueBy(propertyName, newValue);
		return (object == null);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redsun.platf.dao.IDao#loadAll()
	 */
	@Override
	public List<T> loadAll() {
		return getHibernateTemplate().loadAll(getEntityClass());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redsun.platf.dao.IDao#refresh(T)
	 */
	@Override
	public void refresh(T entity) {
		if (entity != null)
			getHibernateTemplate().refresh(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redsun.platf.dao.IDao#save(T)
	 */
	@Override
	public void save(T entity) {
		Assert.notNull(entity, "entity不能为空");
		if (entity != null)
			getHibernateTemplate().save(entity);
		logger.debug("save entity: {}",  entity);
	}

	// @Override
	/*
	 * (non-Javadoc)
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redsun.platf.dao.IDao#saveOrUpdate(T)
	 */
	@Override
	public void saveOrUpdate(T entity) {
		if (entity != null)
			getHibernateTemplate().saveOrUpdate(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redsun.platf.dao.IDao#saveOrUpdateAll(java.util.Collection)
	 */
	@Override
	public void saveOrUpdateAll(Collection<T> entities) {
		if (entities != null)
			getHibernateTemplate().saveOrUpdateAll(entities);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redsun.platf.dao.IDao#saveOrUpdateAll(T[])
	 */
	@Override
	public void saveOrUpdateAll(T[] entities) {
		if (entities != null)
			getHibernateTemplate().saveOrUpdateAll(Arrays.asList(entities));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redsun.platf.dao.IDao#setEntityClass(java.lang.Class)
	 */
	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redsun.platf.dao.IDao#update(T)
	 */
	@Override
	public void update(T entity) {
		if (entity != null)
			getHibernateTemplate().update(entity);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.redsun.platf.dao.IDao#getEntityClass()
	 */
	public Class<T> getEntityClass() {
		return entityClass;
	}
}
