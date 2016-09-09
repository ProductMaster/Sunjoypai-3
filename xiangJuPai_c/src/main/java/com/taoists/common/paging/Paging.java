package com.taoists.common.paging;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;


public abstract class Paging {

	/** 分页缺省显示行数 **/
	private static int DEFAULT_PAGE_SIZE = 10;

	/** 缺省页 **/
	private static int DEFAULT_PAGE_NO = 1;

	/** 缺省排序条件类型 **/
	private static int DEFAULT_ORDER_TYPE = 1;

	/** 缺省查询条件类型 **/
	private static int DEFAULT_WHERE_TYPE = 1;

	/** 分页显示行数 **/
	private int pageSize = DEFAULT_PAGE_SIZE;

	/** 当前页号 **/
	private int pageNo = DEFAULT_PAGE_NO;

	/** 排序条件 **/
	protected int orderType = DEFAULT_ORDER_TYPE;

	/** 搜索类型 **/
	protected int whereType = DEFAULT_WHERE_TYPE;

	/** 总记录数 **/
	private int totalCount;

	/** 总页数 **/
	private int totalPage;

	/** 搜索条件输入 **/
	protected String whereValue;

	/** 当前页第一条数据在List中的位置,从0开始 **/
	private int start;

	/** 起始时间 **/
	protected String fromDate;

	/** 结束时间 **/
	protected String toDate;

	/** 权限判断SQL子句  **/
	protected String purviewCond;


	/** 结果集 **/
	private List list;
	

	private boolean hasNextPage;
	private boolean hasPrePage;
	
	private String ObjIds;

	public String getObjIds() {
		return ObjIds;
	}
	public void setObjIds(String objIds) {
		ObjIds = objIds;
	}
	/************************************
	 *
	 *       abstract methods
	 *
	 ************************************/

	public abstract String getSqlClause();
	public abstract String getCountSqlClause();
	public abstract String getOrderByClause();
	public abstract String getWhereClause();
	public abstract RowMapper getRowMapper();
	
	
	/**
	 * 得到单条记录
	 * @return
	 */
	public Object[] getUniqueSqlClause(){
		return null;
	}
	/************************************
	 *
	 *       paging methods
	 *
	 ************************************/

	/** 是否有下一页	**/
	public boolean hasNextPage() {
		return this.getPageNo() < this.getTotalPage() ;
	}

	/** 是否有上一页	**/
	public boolean hasPreviousPage() {
		return this.getPageNo() > 1;
	}

	/**	 获取任一页第一条数据的位置，每页条数使用默认值	 **/
	protected static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}

	/** 获取任一页第一条数据的位置,startIndex从0开始   **/
	public static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}

	/** 无结果集 **/
	public void setNullResult(){

	}
	

	/************************************
	 *
	 *       Getters and Setters
	 *
	 ************************************/
	public int getPageNo() {
		
		return pageNo > 0 ? pageNo : 1;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		if (totalCount % pageSize == 0)
			return totalCount / pageSize;
		else
			return totalCount / pageSize + 1;
	}

	public int getOrderType() {
		return orderType;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	public int getWhereType() {
		return whereType;
	}
	public void setWhereType(int whereType) {
		this.whereType = whereType;
	}
	public String getWhereValue() {
		return whereValue;
	}
	public void setWhereValue(String whereValue) {
		this.whereValue = whereValue;
	}
	public String getFromDate() {
		return fromDate;
	}

	public String getToDate() {
		return toDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public boolean isHasNextPage() {
		return this.getPageNo() < this.getTotalPage() ;
	}
	public boolean isHasPrePage() {
		return this.getPageNo() > 1;
	}

	public String getPostgreSQLLimit(){
		return this.getSqlClause()+ " limit "+this.pageSize+" OFFSET "+(this.pageNo-1)*this.pageSize;
	}
	public String getMySQLLimit(){
		return this.getSqlClause()+ " limit "+(this.getPageNo()-1)*this.pageSize+" , "+this.pageSize;
	}

}
