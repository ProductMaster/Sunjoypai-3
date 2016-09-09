package com.taoists.common.csv.exception;
/**
 *<b>Summary: </b>
 *   TODO 请在此处简要描述此类所实现的功能。因为这项注释主要是为了在IDE环境中生成tip帮助，务必简明扼要
 *<b>Remarks:</b>
 *   TODO 请在此处详细描述类的功能、调用方法、注意事项、以及与其它类的关系
 *@author liFang
 *@date： 日期：2012-12-27 时间：下午11:13:27
 *@version 1.0
 */
public class ExportSuffixException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3728960486908991073L;
	private static  String exceptionInfo="file suffix error!";
	public ExportSuffixException()
	{
		super(exceptionInfo);
	}
}
