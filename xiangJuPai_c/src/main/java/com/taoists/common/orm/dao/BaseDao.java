package com.taoists.common.orm.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import com.taoists.common.bean.Page;
import com.taoists.common.orm.PropertyFilter;
import com.taoists.common.orm.entity.BaseEntity;

/**
 * @author rubys
 * @since 2012-5-29
 */
public interface BaseDao<T extends BaseEntity> {

	T get(Serializable id);
	
	void insert(Object entity);

	void save(T entity);

	void merge(T entity);

	void update(T entity);

	void saveOrUpdate(T entity);

	void delete(T entity);

	void delete(Serializable id);
	
	void evict(Object... entities);

	void clear();

	List<T> findDatas(String propertyName, Object value);
	
	public T getObjectIsEnable(Serializable id);
	
	List<T> findAll();
	List<T> findAllOrderBy(String orderBy);
	List<T> findAll(long companyId);
	
	List<T> findAllWithoutNull();
	List<T> findAllWithoutNull(long companyId);
	
	List<T> findAllIsEnable();
	List<T> findAllIsEnable(long companyId);

	List<T> findPage(Page page);

	List<T> findPage(Object object, Page page);

	List<T> findDatas(String propertyName, Object value, Page page);

	List<T> findPage(DetachedCriteria detachedCriteria, Page page);

	List<T> findPage(final Page page, final List<PropertyFilter> filters);
	
	Long count(String propertyName, Object value);
	
	Long count();

	long countCriteriaResult(final Criteria criteria);

	DetachedCriteria createDetachedCriteria();

	DetachedCriteria createDetachedCriteria(String alias);

}