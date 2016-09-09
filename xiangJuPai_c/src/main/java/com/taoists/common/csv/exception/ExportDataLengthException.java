package com.taoists.common.csv.exception;
/**
 *<b>Summary: </b>
 *    导出文件时，数据长度不一致的运行时异常。
 *@author liFang
 *@date： 日期：2012-12-27
 *@version 1.0
 */
public class ExportDataLengthException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3728960486908991073L;
	private static  String exceptionInfo="data length no accordance error!";
	public ExportDataLengthException()
	{
		super(exceptionInfo);
	}
}
