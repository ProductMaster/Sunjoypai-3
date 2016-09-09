package com.taoists.common.controller;

import com.taoists.common.Const;

/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-6-15 下午08:11:10  
 *  
 */
public class CommonCond {

	protected int orderBy = Const.NULL_VALUE_INT;
	protected int limit = Const.NULL_VALUE_INT;
	
	private String fromDateStr; 			// 映射时间控件
	private String toDateStr;   			// 映射时间控件
	
	private String fromDate2Str; 			// 映射时间控件
	private String toDate2Str;   			// 映射时间控件
	
	public int getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getFromDateStr() {
		return fromDateStr;
	}
	public void setFromDateStr(String fromDateStr) {
		this.fromDateStr = fromDateStr;
	}
	public String getToDateStr() {
		return toDateStr;
	}
	public void setToDateStr(String toDateStr) {
		this.toDateStr = toDateStr;
	}
	public String getFromDate2Str() {
		return fromDate2Str;
	}
	public void setFromDate2Str(String fromDate2Str) {
		this.fromDate2Str = fromDate2Str;
	}
	public String getToDate2Str() {
		return toDate2Str;
	}
	public void setToDate2Str(String toDate2Str) {
		this.toDate2Str = toDate2Str;
	}
	
	
}
