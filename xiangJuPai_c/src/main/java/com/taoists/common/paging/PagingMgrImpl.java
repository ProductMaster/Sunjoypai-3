package com.taoists.common.paging;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("pagingMgr")
public class PagingMgrImpl extends HibernateDaoSupport  implements PagingMgr{

	public List pagingExec(Paging paging){
		int totalCount = pagingDao.countPaging(paging);
		List list = null;
		paging.setTotalCount(totalCount);
		if(totalCount!=0){
			list = pagingDao.listPaging(paging);
		}
		return list;
	}

	public List getPaging(Paging paging){
		int totalCount = pagingCount(paging.getSqlClause());
		List list =null ;
		if(totalCount >0){
			list = pagingQuery(paging.getSqlClause(), paging.getPageNo(), paging.getPageSize());
			paging.setTotalCount(totalCount);
		}else{
			list = new ArrayList();
			paging.setTotalCount(0);
			paging.setPageNo(0);
		}
		return list;
	}
	
	public List getList(Paging paging){
		return getHibernateTemplate().find(paging.getSqlClause());
	}
	/**
	 * 得到单条记录
	 * @param hql
	 * @return
	 */
	public Object[] getUniqueResult(String hql){
		return getUniqueResult(hql);
	}
	
	public Object[] jdbcUniqueResult(String sql){
		return pagingDao.jdbcUniqueResult(sql);
	}
	public List getJDBCPaging(Paging paging){
		int totalCount = pagingDao.countPaging(paging);
		List list = null;
		paging.setTotalCount(totalCount);
		if(totalCount>0){
			list = pagingDao.jdbcPaging(paging);
		}else{
			list = new ArrayList();
			paging.setTotalCount(0);
			paging.setPageNo(1);
		}
		return list;
	}
	
	public List getJDBCList(Paging paging){
		return pagingDao.jdbcList(paging);
	}
	
	/**
	 * 分页查询函数，使用hql.
	 *
	 * @param pageNo 页号,�?0�?�?.
	 */
	public List pagingQuery(String hql, int pageNo, int pageSize, Object... values) {
				
		//实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		Query query = getQuery(hql, values);
		return query.setFirstResult(startIndex).setMaxResults(pageSize).list();
	}
	
	public Query getQuery(String hql, Object... values) {
		Assert.hasText(hql);
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query;
	}
	
	
	public int pagingCount(String hql, Object... values) {
		
		String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
		List countlist = getHibernateTemplate().find(countQueryString, values);
		int count = Integer.parseInt(countlist.get(0).toString()); 
		return count;
	}
	
	
	/**
	 * 去除hql的select 子句，未考虑union的情�?,，用于pagedQuery.
	 */
	private static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
		return hql.substring(beginPos);
	}

	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 */
	private static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	
	
	@Autowired
	private PagingDao pagingDao;
	public void setPagingDao(PagingDao pagingDao) {
		this.pagingDao = pagingDao;
	} 
	
}

