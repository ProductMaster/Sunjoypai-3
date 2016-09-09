package com.taoists.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *<b>Summary: </b>
 *   产品模块- 文件下載工具
 *<b>Remarks:</b>
 *   
 *@author liFang
 *日期：2013-1-1
 *@version 1.0
 */
public class FileDownUtils {
	/**
	 * 下载文件
	 *@author liFang
	 *日期：2013-1-1 
	 *@param request
	 *@param response
	 *@param downloadPath 要下载文件的路径 
	 *@param contentType 
	 *@param realName 重命名
	 *@throws Exception 
	 */
	public static void download(HttpServletRequest request,  
            HttpServletResponse response, String downloadPath, String contentType,  
            String realName) throws Exception {  
        response.setContentType("text/html;charset=UTF-8");  
        request.setCharacterEncoding("UTF-8");  
        BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;  
  
        long fileLength = new File(downloadPath).length();  
  
        response.setContentType(contentType);  
        response.setHeader("Content-disposition", "attachment; filename="  
                + new String(realName.getBytes("utf-8"), "ISO8859-1"));  
        response.setHeader("Content-Length", String.valueOf(fileLength));  
  
        bis = new BufferedInputStream(new FileInputStream(downloadPath));  
        bos = new BufferedOutputStream(response.getOutputStream());  
        byte[] buff = new byte[2048];  
        int bytesRead;  
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
            bos.write(buff, 0, bytesRead);  
        }  
        bis.close();  
        bos.close();  
    }  
}
