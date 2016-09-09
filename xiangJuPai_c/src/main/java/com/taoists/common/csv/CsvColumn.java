package com.taoists.common.csv;
/**
 *<b>Summary: </b>
 *   csv文件列的实体类
 *<b>Remarks:</b>
 *   存放csv文件每列数据的实体对象
 *@author liFang
 *@date： 日期：2012-12-27 时间：下午09:46:39
 *@version 1.0
 */
public class CsvColumn {

	/**csv文件中列的名称**/
	private String name;
	/**存放当前列每行的数据，最好为基本数值型*/
	private  Object[] datas;
 
	public CsvColumn(String name,Object[] datas)
	{
	    this.name=name;
	    this.datas=datas;
	}
	public  Object[] getDatas() {
		return datas;
	}
	public void setDatas( Object[] datas) {
		this.datas = datas;
		 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
 
}
