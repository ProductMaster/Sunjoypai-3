package com.taoists.common.csv.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import com.taoists.common.csv.CsvColumn;
import com.taoists.common.csv.IExportFile;
import com.taoists.common.csv.exception.ExportSuffixException;



/**
 * <b>Summary: </b> 导出文件抽像类 <b>Remarks:</b> 实现com.taoist.pd.export.IExportFile
 * 接口
 * 
 * @author liFang
 * @date： 日期：2012-12-29 时间：下午01:24:13
 * @version 1.0
 */
public abstract class AbsExportFile implements IExportFile {

	protected String csvFilePath;
	protected String suffix = ".csv";
	/** 是否存在写入标题* */
	protected boolean isWriteColumnHeaders = false;
	protected FileWriter fileWriter = null;
	/** 列标题数组* */
	protected String[] columnHeaders;

	/**
	 * 压缩文件所用的属性
	 */
	private int bufSize = 512; // size of bytes
	private byte[] buf = new byte[this.bufSize];;
	private int readedBytes;

	/**
	 * 是否已经写入列标题
	 * 
	 * @author liFang
	 * @date： 日期：2012-12-29
	 * @return
	 */
	public boolean isWriteColumnHeaders() {
		return isWriteColumnHeaders;
	}

	public String getCsvFilePath() {
		return csvFilePath;
	}

	/**
	 * 设置文件目录及文件名
	 * 
	 * @author liFang
	 * @date： 日期：2012-12-29
	 * @param fileDirectory
	 *            文件目录及文件名
	 */
	
	public void setCsvFilePath(String csvFilePath) {
		if (!isCSVSuffix(csvFilePath))
			throw new ExportSuffixException();
		this.csvFilePath = csvFilePath;

	}

	/**
	 * 获取每列的标题
	 * 
	 * @author liFang
	 * @date： 日期：2012-12-27
	 * @param dataList
	 * @return
	 */
	public String[] getColumnHeaders(List<? extends CsvColumn> dataList) {
		String[] headers = new String[dataList.size()];
		int i = 0;
		for (CsvColumn csvColumn : dataList) {
			headers[i] = csvColumn.getName();
			i++;
		}
		return headers;
	}

	/**
	 * 每列数据的长度是否一至。
	 * 
	 * @author liFang
	 * @date： 日期：2012-12-27
	 * @param dataList
	 * @return true 长度一至，false为长度不一至
	 */

	protected boolean isEqualLength(List<? extends CsvColumn> dataList) {
		Integer count = null;
		if (dataList.size() > 0 && dataList.get(0).getDatas() != null) {
			count = dataList.get(0).getDatas().length;
			for (CsvColumn csvColumn : dataList) {
				if (count != csvColumn.getDatas().length) {
					return false;
				}
			}
		} else
			return false;
		return true;
	}

	/**
	 * 或者某列数据
	 * 
	 * @author liFang
	 * @date： 日期：2012-12-27
	 * @param header
	 *            标题头名称
	 * @param dataList
	 * @return
	 */
	protected Object[] getColumnDatas(String header,
			List<? extends CsvColumn> dataList) {
		for (CsvColumn csvColumn : dataList) {
			if (csvColumn.getName().equals(header))
				return csvColumn.getDatas();
		}
		return null;
	}

	/**
	 * 检查文件后缀名是否为.csv
	 * 
	 * @author liFang
	 * @date： 日期：2012-12-27
	 * @return true 为 正确，false为错误
	 */
	protected boolean isCSVSuffix(String file) {
		return file.toUpperCase().endsWith(suffix.toUpperCase());
	}

	/**
	 * 关闭文件流
	 * 
	 * @author liFang
	 * @date： 日期：2012-12-27
	 */
	protected void close() {
		try {
			if (fileWriter != null) {
				fileWriter.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/**
	 * 删除生成的csv文件
	 *@author liFang
	 *日期：2013-1-1
	 */
	public void removeCsv()
	{
		File file =new File(this.csvFilePath);
		file.delete();
		
	}
	/**
	 * 压缩csv文件至zip格式
	 * 
	 * @author liFang 日期：2013-1-1
	 * @param zipPath
	 *            zip文件存放的完整路径及文件名
	 */
	public void toZip(String zipPath) {

		// 添加".zip"后缀
		if (!zipPath.endsWith(".zip"))
			zipPath += ".zip";

		try {
			ZipOutputStream zipOut = new ZipOutputStream(
					new BufferedOutputStream(new FileOutputStream(zipPath)));
			File file = new File(this.csvFilePath);
			String fileName = file.getName();
			zipOut.putNextEntry(new ZipEntry(fileName));

			if (file.isFile()) {
				FileInputStream fileIn = new FileInputStream(file);
				while ((this.readedBytes = fileIn.read(this.buf)) > 0) {
					zipOut.write(this.buf, 0, this.readedBytes);
				}
				fileIn.close();
			}

			zipOut.closeEntry();
			zipOut.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new RuntimeException(ioe);
		}

	}

}
