package com.taoists.common.paging;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 分页对象.
 * 包含当前页数据及分页信息.
 *
 * @author ajax
 * @author calvin
 */
@SuppressWarnings("serial")
public class Page implements Serializable {

	static private int DEFAULT_PAGE_SIZE = 20;

	/**
	 * 每页的记录数
	 */
	private int pageSize = DEFAULT_PAGE_SIZE;

	/**
	 * 当前页第�?条数据在List中的位置,�?0�?�?
	 */
	private long start;

	/**
	 * 当前页中存放的记�?,类型�?般为List
	 */
	private Object data;

	/**
	 * 总记录数
	 */
	private long totalCount;

	/**
	 * 构�?�方法，只构造空�?
	 */
	public Page() {
		this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList());
	}

	/**
	 * 默认构�?�方�?
	 *
	 * @param start	 本页数据在数据库中的起始位置
	 * @param totalSize 数据库中总记录条�?
	 * @param pageSize  本页容量
	 * @param data	  本页包含的数�?
	 */
	public Page(long start, long totalSize, int pageSize, Object data) {
		this.pageSize = pageSize;
		this.start = start;
		this.totalCount = totalSize;
		this.data = data;
	}

	/**
	 * 取数据库中包含的总记录数
	 */
	public long getTotalCount() {
		return this.totalCount;
	}

	/**
	 * 取�?�页�?
	 */
	public long getTotalPageCount() {
		if (totalCount % pageSize == 0)
			return totalCount / pageSize;
		else
			return totalCount / pageSize + 1;
	}

	/**
	 * 取每页数据容�?
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 当前页中的记�?
	 */
	public Object getResult() {
		return data;
	}

	/**
	 * 取当前页�?,页码�?1�?�?
	 */
	public long getCurrentPageNo() {
		return start / pageSize + 1;
	}

	/**
	 * 是否有下�?�?
	 */
	public boolean hasNextPage() {
		return this.getCurrentPageNo() < this.getTotalPageCount() - 1;
	}

	/**
	 * 是否有上�?�?
	 */
	public boolean hasPreviousPage() {
		return this.getCurrentPageNo() > 1;
	}

	/**
	 * 获取任一页第�?条数据的位置，每页条数使用默认�??
	 */
	protected static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}

	/**
	 * 获取任一页第�?条数据的位置,startIndex�?0�?�?
	 */
	public static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}
}
