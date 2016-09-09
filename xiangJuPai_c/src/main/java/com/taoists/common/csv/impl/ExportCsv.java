package com.taoists.common.csv.impl;

import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;

import com.taoists.common.csv.CsvColumn;
import com.taoists.common.csv.exception.ExportDataLengthException;
import com.taoists.common.csv.exception.ExportSuffixException;
import com.taoists.common.util.ResourceReader;


/**
 * 
 * <b>Summary: </b> 导出CSV格式文件的实现类 <b>Remarks:</b> 继承
 * com.taoist.pd.export.impl.AbsExportFile 调用说明： 方式1：
 * 1）实例化之后，可以先调用writeColumnHeaders方法写入每列标题 2）然后调用
 * appendDatas方法，追加数据到csv文件中。可多次调用 注意：
 * 1.列标题从左到右的顺序是按传入writeColumnHeaders方法中的数组顺序为准 2.appendDatas方法中参数List<?
 * extends CsvColumn>中CsvColumn的name对应列标题。 方式2:
 * 直接调用appendDatas方法写入数据。本方法中会自动写入列标题 注意：列标题从左到右的顺序是按第一次调用appendDatas方法传入参数List<?
 * extends CsvColumn>的CsvColumn顺序存放为准
 * 
 * @author liFang
 * @date： 日期：2012-12-27
 * @version 1.0
 */

public class ExportCsv extends AbsExportFile {
	private String path = ResourceReader.getValue("export_path");
	private int dataLength = 0;
	private long startMs;//开始运行的毫秒数
	private long endMs;//结束运行的毫秒数
	public ExportCsv() {
		this.suffix = ".csv";
		this.csvFilePath = path + System.currentTimeMillis() + suffix;
	}

	/**
	 * @param 文件路径及文件名
	 */
	public ExportCsv(String csvFilePath) {

		if (!isCSVSuffix(csvFilePath))
			throw new ExportSuffixException();
		this.csvFilePath = csvFilePath;

	}

	/**
	 * 获取所有标题头名称 列之间用,间隔,写完一行需要回车换行"\r\n"
	 * 
	 * @author liFang
	 * @date： 日期：2012-12-27
	 * @param dataList
	 * @return
	 */
	protected StringBuffer getColumnHeaders(String[] headers) {
		StringBuffer header = new StringBuffer();
		for (String h : headers)
			header.append(h + ",");
		header.delete(header.length() - 1, header.length());
		header.append("\r\n");
		return header;
	}

	/**
	 * 添加标题头名称
	 * 
	 * @author liFang
	 * @date： 日期：2012-12-28
	 * @param headers
	 * @return
	 */
	public void writeColumnHeaders(String[] headers) {
		startMs=System.currentTimeMillis();
		try {
			if (!isWriteColumnHeaders) {
				fileWriter = new FileWriter(csvFilePath);
				StringBuffer header = getColumnHeaders(headers);
				fileWriter.write(header.toString());
				isWriteColumnHeaders = true;
				this.columnHeaders = headers;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close();
		}

	}

	/**
	 * 追加数据到csv文件，可多次调用，即分批
	 * 
	 * @author liFang
	 * @date： 日期：2012-12-27
	 * @param dataList
	 *            要追加的数据集合 注意：每个 CsvColumn 对象中的datas长度必须一至。
	 * @return
	 */
	public void appendDatas(List<? extends CsvColumn> dataList) {
		if (dataList == null)
			throw new NullPointerException();
		if (!isEqualLength(dataList))
			throw new ExportDataLengthException();
		dataLength = dataList.get(0).getDatas().length;
		try {

			if (!isWriteColumnHeaders) {
				String[] hs = getColumnHeaders(dataList);
				writeColumnHeaders(hs);
				isWriteColumnHeaders = true;
			}
			StringBuffer dataStr = new StringBuffer();
			for (int i = 0; i < dataLength; i++) {
				for (String title : columnHeaders) {
					Object[] datas = getColumnDatas(title, dataList);
					Object value = datas[i];
					if (value == null)
						dataStr.append(",");
					else {
						// 字符串与数字加\t
						//if (value != null
								// && (NumberUtils.isNumber(value.toString()) || value instanceof String)) {
							// dataStr.append("\t");
						// }
						if (value instanceof BigDecimal) {
							
						} else if ((NumberUtils.isNumber(value.toString()) || value instanceof String)) {
							dataStr.append("\t");
						}
						dataStr.append(value + ",");
					}
				}
				dataStr.delete(dataStr.length() - 1, dataStr.length());
				dataStr.append("\r\n");
			}
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			fileWriter = new FileWriter(csvFilePath, true);
			fileWriter.write(dataStr.toString());

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close();
		}
		endMs=System.currentTimeMillis();
	}

	public static void delete(String filePath) {
		File file = new File(filePath);
		file.delete();
	}

	public long getCostMillisecond() {
		 
		return endMs-startMs;
	}
}
