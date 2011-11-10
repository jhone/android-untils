package com.redsun.platf.dao.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.PropertyFilter.MatchType;

public interface IBaseDao<T, PK extends Serializable > {


	/**
	 * 新增指定之 Entity 物件
	 * 
	 * @param entity
	 *            指定之 Entity 物件
	 */
	public void save(T entity);

	/**
	 * 新增指定之 Entity 物件
	 * 
	 * @param entitys
	 *            entity 指定之 Entity 物件
	 */
	public void saveAll(Collection<T> entitys);

	/**
	 * 更新指定之 Entity 物件
	 * 
	 * @param entity
	 *            指定之 Entity 物件
	 */
	public void update(T entity);

	/**
	 * 儲存或更新指定之 Entity 物件
	 * 
	 * @param entity
	 *            指定之 Entity 物件
	 */
	public void saveOrUpdate(T entity);

	/**
	 * 儲存或更新指定之 Entity 物件 Array
	 * 
	 * @param entities
	 *            指定之 Entity 物件 Array
	 */
	public void saveOrUpdateAll(T[] entities);

	/**
	 * 儲存或更新指定之 Entity 物件 Collection
	 * 
	 * @param entities
	 *            指定之 Entity 物件 Collection
	 */
	public void saveOrUpdateAll(Collection<T> entities);

	/** delete *************************************************************/

	/**
	 * 刪除指定之 Entity 物件
	 * 
	 * @param entity
	 *            指定之 Entity 物件
	 */
	public void delete(T entity);

	/**
	 * 2按id删除对象
	 * 
	 * @param entity
	 *            指定之 Entity 物件id
	 */
	public void delete(final PK id);

	/**
	 * 刪除指定之 Entity 物件 Array 所有資料
	 * 
	 * @param entity
	 *            指定之 Entity 物件 Array
	 */
	public void deleteAll(T[] entities);

	/**
	 * 刪除指定之 Entity 物件 Collection 所有資料
	 * 
	 * @param entity
	 *            指定之 Entity 物件 Collection
	 */
	public void deleteAll(Collection<T> entities);

	/**
	 * 更新目前取出的資料，與資料庫同步
	 * 
	 * @param entity
	 *            指定之 Entity 物件
	 */
	public void refresh(T entity);

	/**
	 * 將目前 DAO 的更新，同步至資料庫，類似 Commit
	 */
	public void flush();

	/** find *************************************************************/

	/**
	 * 計算query string所回傳的行數.
	 * 
	 * @param queryString
	 *            SQL字串
	 * @param params
	 *            參數陣列
	 * @param namedParams
	 *            參數Map
	 * @return
	 */
	public abstract int count(final String queryString, final Object[] params,
			final Map<String, Object> namedParams);

	/**
	 * 計算query string所回傳的行數.
	 * 
	 * @param queryString
	 *            SQL字串
	 * @param params
	 *            參數陣列
	 * @return
	 */
	public abstract int count(String queryString, Object[] params);

	/**
	 * 查詢指定Entity 物件
	 * 
	 * @param id
	 * @return
	 */
	public T findById(PK id);

	/**
	 * 查詢指定Entity 物件
	 * 
	 * @param id
	 * @return
	 */
	public T findById(String id);

	/**
	 * 查詢所有Entity 物件
	 * 
	 * @return
	 */
	public List<T> loadAll();

	/**
	 * 執行query string, 代入parameter. 回傳自firstResult開始最多maxResults的清單.
	 * 
	 * @param queryString
	 *            SQL字串
	 * @param params
	 *            參數陣列
	 * @param namedParams
	 *            參數Map
	 * @param firstResult
	 *            最小筆數
	 * @param maxResults
	 *            最大筆數
	 * @return a List of objects.
	 */
	List<T> find(final String queryString, final Object[] params,
			final Map<String, Object> namedParams, final Integer firstResult,
			final Integer maxResults);

	/**
	 * 按属性查找对象列表,支持多种匹配方式.
	 * 
	 * @param matchType
	 *            匹配方式,目前支持的取值见PropertyFilter的MatcheType enum.
	 */
	public abstract List<T> findBy(final String propertyName,
			final Object value, final MatchType matchType);

	/**
	 * 按属性过滤条件列表查找对象列表.
	 */
	public abstract List<T> find(List<PropertyFilter> filters);
	/*******************************************************************/
	


	/**
	 * 按id获取对象.
	 */
	public abstract T get(final PK id);

	/**
	 * 按id列表获取对象列表.
	 */
	public abstract List<T> get(final Collection<PK> ids);

	/**
	 *	获取全部对象.
	 */
	public abstract List<T> getAll();

	/**
	 *	获取全部对象, 支持按属性行序.
	 */
	public abstract List<T> getAll(String orderByProperty, boolean isAsc);

	/**
	 * 按属性查找对象列表, 匹配方式为相等.
	 */
	public abstract List<T> findBy(final String propertyName, final Object value);

	/**
	 * 按属性查找唯一对象, 匹配方式为相等.
	 */
	public abstract T findUniqueBy(final String propertyName, final Object value);

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	public abstract <X> List<X> find(final String hql, final Object... values);

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param values 命名参数,按名称绑定.
	 */
	public abstract <X> List<X> find(final String hql,
			final Map<String, ?> values);

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	public abstract <X> X findUnique(final String hql, final Object... values);

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param values 命名参数,按名称绑定.
	 */
	public abstract <X> X findUnique(final String hql,
			final Map<String, ?> values);

	
	
	/**
	 * 按Criteria查询对象列表.
	 * 
	 * @param criterions 数量可变的Criterion.
	 */
	public abstract List<T> find(final Criterion... criterions);

	/**
	 * 按Criteria查询唯一对象.
	 * 
	 * @param criterions 数量可变的Criterion.
	 */
	public abstract T findUnique(final Criterion... criterions);

	/**
	 * 根据Criterion条件创建Criteria.
	 * 与find()函数可进行更加灵活的操作.
	 * 
	 * @param criterions 数量可变的Criterion.
	 */
	public abstract Criteria createCriteria(final Criterion... criterions);

	/**
	 * 初始化对象.
	 * 使用load()方法得到的仅是对象Proxy, 在传到View层前需要进行初始化.
	 * 如果传入entity, 则只初始化entity的直接属性,但不会初始化延迟加载的关联集合和属性.
	 * 如需初始化关联属性,需执行:
	 * Hibernate.initialize(user.getRoles())，初始化User的直接属性和关联集合.
	 * Hibernate.initialize(user.getDescription())，初始化User的直接属性和延迟加载的Description属性.
	 */
	public abstract void initProxyObject(Object proxy);

	

	/**
	 * 为Query添加distinct transformer.
	 * 预加载关联对象的HQL会引起主对象重复, 需要进行distinct处理.
	 */
	public abstract Query distinct(Query query);

	/**
	 * 为Criteria添加distinct transformer.
	 * 预加载关联对象的HQL会引起主对象重复, 需要进行distinct处理.
	 */
	public abstract Criteria distinct(Criteria criteria);

	/**
	 * 取得对象的主键名.
	 */
	public abstract String getIdName();

	
	

	
	
	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 * 
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
	 */
	public abstract boolean isPropertyUnique(final String propertyName,
			final Object newValue, final Object oldValue);
	
	/**
	 * 执行HQL进行批量修改/删除操作.
	 * 
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return 更新记录数.
	 */
	public abstract int batchExecute(final String hql, final Object... values);

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * 
	 * @param values 命名参数,按名称绑定.
	 * @return 更新记录数.
	 */
	public abstract int batchExecute(final String hql,
			final Map<String, ?> values);

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 与find()函数可进行更加灵活的操作.
	 * 
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	public abstract Query createQuery(final String queryString,
			final Object... values);

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 与find()函数可进行更加灵活的操作.
	 * 
	 * @param values 命名参数,按名称绑定.
	 */
	public abstract Query createQuery(final String queryString,
			final Map<String, ?> values);


	



}