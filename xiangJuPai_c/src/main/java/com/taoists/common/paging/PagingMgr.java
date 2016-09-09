package com.taoists.common.paging;

import java.util.List;


public interface PagingMgr {

	public List pagingExec(Paging paging);
	
	public List getPaging(Paging paging);
	
	public List getJDBCPaging(Paging paging);
	
	public List getList(Paging paging);
	
	/**
	 * 得到单条记录
	 * @param hql
	 * @return
	 */
	public Object[] getUniqueResult(String hql);
	
	public Object[] jdbcUniqueResult(String sql);
	
	//检索全部数据
	public List getJDBCList(Paging paging);
	
	
	public int pagingCount(String hql, Object... values);
}
