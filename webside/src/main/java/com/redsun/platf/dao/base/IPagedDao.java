package com.redsun.platf.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

/**
 * 封装Page的DAO泛型接口.
 * 
 * 可在Service层直接使用, 也可以扩展泛型DAO子类使用
 * 
 * 参考Spring2.5自带的Petlinc例子, 使用HibernateTemplate,取消使用Hibernate原生API.
 * 
 * @param <T> DAO操作的对象类型
 * @param <PK> 主键类型
 * 
 * @author 1.1 
 *         增加extends idao ,將有關page的操作全整到次處
 * 
 * 
 */
public interface IPagedDao<T, PK extends Serializable> extends  IBaseDao<T, PK> {

   
    /**
	 * 分页获取全部对象.
	 */
	public abstract Page<T> getAll(final Page<T> page);

	/**
	 * 按HQL分页查询.
	 * 
	 * @param page 分页参数. 注意不支持其中的orderBy参数.
	 * @param hql hql语句.
	 * @param values 数量可变的查询参数,按顺序绑定.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询输入参数.
	 */
	public abstract Page<T> findPage(final Page<T> page, final String hql,
			final Object... values);

	/**
	 * 按HQL分页查询.
	 * 
	 * @param page 分页参数. 注意不支持其中的orderBy参数.
	 * @param hql hql语句.
	 * @param values 命名参数,按名称绑定.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询输入参数.
	 */
	public abstract Page<T> findPage(final Page<T> page, final String hql,
			final Map<String, ?> values);

	/**
	 * 按Criteria分页查询.
	 * 
	 * @param page 分页参数.
	 * @param criterions 数量可变的Criterion.
	 * 
	 * @return 分页查询结果.附带结果列表及所有查询输入参数.
	 */
	public abstract Page<T> findPage(final Page<T> page,
			final Criterion... criterions);


	/**
	 * 按属性过滤条件列表分页查找对象.
	 */
	public abstract Page<T> findPage(final Page<T> page,
			final List<PropertyFilter> filters);


	
}
