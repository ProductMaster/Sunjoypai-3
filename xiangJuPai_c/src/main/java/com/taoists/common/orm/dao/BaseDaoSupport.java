package com.taoists.common.orm.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.transaction.annotation.Transactional;

import com.taoists.common.Const;
import com.taoists.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-28
 */
@SuppressWarnings("unchecked")
@Transactional
public abstract class BaseDaoSupport<T extends BaseEntity> implements BaseDao<T> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public T get(Serializable id) {
		return (T) getSession().get(getEntityClass(), id);
	}

	@Override
	@Transactional
	public void insert(Object entity) {
		getSession().save(entity);
	}

	@Override
	@Transactional
	public void save(T entity) {
		getSession().save(entity);
	}

	@Override
	@Transactional
	public void merge(T entity) {
		getSession().merge(entity);
	}

	@Override
	@Transactional
	public void update(T entity) {
		getSession().update(entity);
	}

	@Override
	@Transactional
	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	@Override
	@Transactional
	public void delete(T entity) {
		getSession().delete(entity);
	}

	@Override
	@Transactional
	public void delete(Serializable id) {
		T entity = get(id);
		if (entity == null) {
			return;
		}
		getSession().delete(entity);
	}

	@Override
	public void evict(Object... entities) {
		if (entities == null) {
			return;
		}
		for (Object entity : entities) {
			getSession().evict(entity);
		}
	}

	
	public List<T> findDatas(DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria
				.getExecutableCriteria(getSession());
		return criteria.list();
	}

	@Override
	public List<T> findDatas(String propertyName, Object value) {
		Criteria criteria = createDetachedCriteria().getExecutableCriteria(
				getSession());
		if (propertyName.indexOf(".") != -1) {
			String alias = propertyName.substring(0, propertyName.indexOf("."));
			criteria.createAlias(alias, alias);
		}
		criteria.add(Restrictions.eq(propertyName, value));
		return criteria.list();
	}
	
	public T getObjectIsEnable(Serializable id){
	    Criteria criteria = createDetachedCriteria().getExecutableCriteria(getSession());
	    criteria.add(Restrictions.eq("id", id));
	    criteria.add(Restrictions.eq("status", Const.ENABLE));
	    List<T> list = criteria.list();
	    if(list.size()>0) return list.get(0);
	    return null;
	}
	
	@Override
	public List<T> findAll() {
		logger.debug("findAll");
		List<T> l =  createDetachedCriteria().getExecutableCriteria(getSession()).list();
		logger.debug("list size:" +l.size());
		return l;
	}
	
	public List<T> findAllOrderBy(String orderBy) {
		Criteria criteria = createDetachedCriteria().getExecutableCriteria(
				getSession());
		criteria.addOrder(Order.asc(orderBy));
		return criteria.list();
	}
	
	public List<T> findAll(long companyId){
		Criteria criteria = createDetachedCriteria().getExecutableCriteria(
				getSession());
		criteria.add(Restrictions.eq("company.id", companyId));
		return criteria.list();
	}
	
	public List<T> findAllWithoutNull(){
		Criteria criteria = createDetachedCriteria().getExecutableCriteria(
				getSession());
		criteria.add(Restrictions.ne("id",Const.NULL_VALUE_LONG));
		return criteria.list();
	}
	
	public List<T> findAllWithoutNull(long companyId){
		Criteria criteria = createDetachedCriteria().getExecutableCriteria(
				getSession());
		criteria.add(Restrictions.eq("company.id", companyId));
		criteria.add(Restrictions.ne("id",Const.NULL_VALUE_LONG));
		return criteria.list();
	}
	
	public List<T> findAllIsEnable(){
		Criteria criteria = createDetachedCriteria().getExecutableCriteria(
				getSession());
		criteria.add(Restrictions.ne("id",Const.NULL_VALUE_LONG));
		criteria.add(Restrictions.eq("status",Const.ENABLE));
		return criteria.list();
	}
	
	public List<T> findAllIsEnable(long companyId){
		Criteria criteria = createDetachedCriteria().getExecutableCriteria(
				getSession());
		criteria.add(Restrictions.eq("company.id", companyId));	
		criteria.add(Restrictions.ne("id",Const.NULL_VALUE_LONG));
		criteria.add(Restrictions.eq("status",Const.ENABLE));
		return criteria.list();
	}

	/** ----------------------------------- JDBC complement ------------------------------  **/
	@Transactional
	public int save(String sql, final Object[] params) throws Exception {
		return this.jdbcTemplate.update(sql, new PreparedStatementSetter(){

			public void setValues(PreparedStatement ps) throws SQLException {
				for(int i = 1; i <= params.length; i++) {
					ps.setObject(i, params[i - 1]);
				}
			}

		});
	}
	
	@Transactional
	public int[] batchSave(String sql, final List<Object[]> list) throws Exception {
		return this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){

			public int getBatchSize() {
				return list.size();
			}

			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Object[] params = list.get(i);
				for(int j = 1; j <= params.length; j++) {
					ps.setObject(j, params[j - 1]);
				}
			}

		});
	}

	@Transactional
	public int[] batchSave(String sql, final Object[] params) throws Exception {
		return this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){

			public int getBatchSize() {
				return params.length;
			}

			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setObject(1, params[i]);
			}

		});
	}
	
	@Transactional
	public int delete(String sql) throws Exception {
		return this.jdbcTemplate.update(sql);
	}
	
	@Transactional
	public int delete(String sql, final Object... params) throws Exception {
		return this.jdbcTemplate.update(sql, params);
	}
	
	@Transactional
	public int deleteById(String sql, final String id) throws Exception {
		return this.jdbcTemplate.update(sql, new PreparedStatementSetter(){

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setObject(1, id);
			}

		});
	}
	
	@Transactional
	public int[] batchDeleteByIds(String sql, final String[] ids)
			throws Exception {
		return this.jdbcTemplate.batchUpdate(sql,
				new BatchPreparedStatementSetter() {

					public int getBatchSize() {
						return ids.length;
					}

					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setObject(1, ids[i]);
					}

				});
	}
	
	@Transactional
	public int[] batchDeleteByIds(String sql, final List<String> list) throws Exception {
		return this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){

			public int getBatchSize() {
				return list.size();
			}

			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setObject(1, list.get(i));
			}

		});
	}
	
	/** ------------------------------------- Helper -------------------------------------	 **/

	@Override
	public void clear() {
		getSession().clear();
	}

	
	@Override
	public DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(getEntityClass());
	}

	@Override
	public DetachedCriteria createDetachedCriteria(String alias) {
		return DetachedCriteria.forClass(getEntityClass(), alias);
	}

	@SuppressWarnings("unchecked")
	public Class<T> getEntityClass() {
		try {
			return (Class<T>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (Exception e) {
			return (Class<T>) getEntity(getClass().getSuperclass()
					.getGenericInterfaces());
		}
	}

	protected Class<?> getEntity(Type[] types) {
		Class<?> entityClass = null;
		for (Type type : types) {
			if (type instanceof ParameterizedType) {
				entityClass = (Class<?>) ((ParameterizedType) type)
						.getActualTypeArguments()[0];
			} else if (type instanceof Class) {
				entityClass = getEntity(((Class<?>) type)
						.getGenericInterfaces());
			}
		}
		logger.debug("ActualTypeArguments [entityClass {}]", entityClass);
		return entityClass;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/** ------------------------------------- IOC -------------------------------------	 **/

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	protected JdbcTemplate jdbcTemplate = null;

	public JdbcTemplate getJdbcTemplate() {
		  return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


}
