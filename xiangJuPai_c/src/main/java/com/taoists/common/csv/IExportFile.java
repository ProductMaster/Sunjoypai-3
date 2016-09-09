package com.taoists.common.csv;

import java.util.List;

/**
 * <b>Summary: </b> 数据导出文件接口 <b>Remarks:</b> 要求支持分批写数据到文件
 * 
 * @author liFang
 * @date： 日期：2012-12-29
 * @version 1.0
 */
public interface IExportFile {
	/**
	 * 获取csv文件路径
	 * 
	 * @author liFang 日期：2012-12-31
	 * @return
	 */
	public String getCsvFilePath();

	/**
	 * 追加列数据，可多次调用，即分批
	 * 
	 * @author liFang
	 * @date： 日期：2012-12-29
	 * @param dataList
	 *            列数据
	 */
	public void appendDatas(List<? extends CsvColumn> dataList);

	/**
	 * 写入列标题
	 * 
	 * @author liFang
	 * @date： 日期：2012-12-29
	 * @param columnHeader
	 */
	public void writeColumnHeaders(String[] columnHeader);

	/**
	 * 压缩csv文件至zip格式
	 * 
	 * @author liFang 日期：2013-1-1
	 * @param zipPath
	 *            zip文件存放的完整路径及文件名
	 */
	public void toZip(String zipPath);
	
	/**
	 * 删除生成的csv文件
	 *@author liFang
	 *日期：2013-1-1
	 */
	public void removeCsv();
	
	/**
	 * 获取整个导出过程（从开始写入列标题到N次追加数据完成）花费的毫秒数
	 *@author liFang
	 *日期：2013-1-3 
	 *@return
	 */
	public long getCostMillisecond();

}
