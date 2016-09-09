/*****************************************************************************
 *
 *                      FORNOW PROPRIETARY INFORMATION
 *
 *          The information contained herein is proprietary to ForNow
 *           and shall not be reproduced or disclosed in whole or in part
 *                    or used for any design or manufacture
 *              without direct written authorization from ForNow.
 *
 *            Copyright (c) 2014 by ForNow.  All rights reserved.
 *
 *****************************************************************************/
package com.taoists.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.taoists.common.image.Image2Utils;


/**
 * @author Jiafa Lv
 * @date Apr 21, 2014 2:11:40 PM
 * @email simon-jiafa@126.com
 * 
 */
@Component
public class UploadHelper {
	public static final String REAL_PATH = "D:/work/xiangjupai/user";
	public static final String CASE_PATH = "D:/work/xiangjupai/case/";
	public static final String PRODUCT_PATH = "D:/work/xiangjupai/product/";
	public static final String USER_HEAD_PATH = "D:/work/xiangjupai/user/";
	public static final String STORE_HEAD_PATH = "D:/work/xiangjupai/store/";
	public static final String UPLOAD_PATH = "upload/";
	public static final String FILE_NAME = "fileName";
	public static final String FILE_EXT = "fileExt";
	public static final String UNDERLINE = "_";
	public static final String SEPARATOR = "/";

	private static final String CLASS_NAME = UploadHelper.class.getName();

	/**
	 * 
	 * @param request
	 * @param inputFileName
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> getFileNameByUser(
			HttpServletRequest request, String inputFileName, long userId) throws Exception {
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			LoggerUtils.debug(CLASS_NAME, "Spring3 MVC upload file with Multipart form");

			MultipartFile file = multipartRequest.getFiles(inputFileName)
					.get(0);
			long size = file.getSize();
			if(size>0){
				Map<String, String> map = new HashMap<String, String>();
				byte[] data = new byte[(int) size];
				InputStream input = file.getInputStream();
				input.read(data);

				// create file, if no app context path, will throws access denied.
				// seems like you could not create any file at tomcat/bin
				// directory!!!

				LoggerUtils.debug(CLASS_NAME, "file.getContentType() ==> " + file.getContentType());
				Long filePath1 = mmNum(userId,5000);
				String fileName = String.valueOf(userId);
				map.put(FILE_NAME, fileName);
				LoggerUtils.debug(CLASS_NAME, "fileName ==> " + fileName);
				String fileExt = "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
				map.put(FILE_EXT, fileExt);
				LoggerUtils.debug(CLASS_NAME, "fileExt ==> " + fileExt);
				String wholeFileName = USER_HEAD_PATH +filePath1+"/"+ fileName + fileExt;
				LoggerUtils.debug(CLASS_NAME, "file WholeName ==> " + wholeFileName);
				File outFile = new File(wholeFileName);

				if (!outFile.exists()) {
					makeDir(outFile.getParentFile());
					outFile.createNewFile();
					LoggerUtils.debug(CLASS_NAME, "full path = " + outFile.getAbsolutePath());
				} else {
					LoggerUtils.debug(CLASS_NAME, "full path = " + outFile.getAbsolutePath());
				}
				FileOutputStream outStream = new FileOutputStream(outFile);

				outStream.write(data);
				outStream.close();
				input.close();
				
				String fileAllName = saveScaleImage(USER_HEAD_PATH + filePath1 + "/", fileName + fileExt, ImageUtils.SMALL_SIZE, ImageUtils.SMALL_SIZE, ImageUtils.EXT_SMALL_SIZE);
				return map;
			}
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param inputFileName
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> getFileNameByProduct(
			HttpServletRequest request, String inputFileName, long productId) throws Exception {
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			LoggerUtils.debug(CLASS_NAME, "Spring3 MVC upload file with Multipart form");

			MultipartFile file = multipartRequest.getFiles(inputFileName)
					.get(0);
			long size = file.getSize();
			if(size>0){
				Map<String, String> map = new HashMap<String, String>();
				byte[] data = new byte[(int) size];
				InputStream input = file.getInputStream();
				input.read(data);

				// create file, if no app context path, will throws access denied.
				// seems like you could not create any file at tomcat/bin
				// directory!!!

				LoggerUtils.debug(CLASS_NAME, "file.getContentType() ==> " + file.getContentType());
				Long filePath1 = mmNum(productId,5000);
				String fileName = String.valueOf(productId);
				map.put(FILE_NAME, fileName);
				LoggerUtils.debug(CLASS_NAME, "fileName ==> " + fileName);
				String fileExt = "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
				map.put(FILE_EXT, fileExt);
				LoggerUtils.debug(CLASS_NAME, "fileExt ==> " + fileExt);
				String wholeFileName = PRODUCT_PATH +filePath1+"/"+ fileName + fileExt;
				LoggerUtils.debug(CLASS_NAME, "file WholeName ==> " + wholeFileName);
				File outFile = new File(wholeFileName);

				if (!outFile.exists()) {
					makeDir(outFile.getParentFile());
					outFile.createNewFile();
					LoggerUtils.debug(CLASS_NAME, "full path = " + outFile.getAbsolutePath());
				} else {
					LoggerUtils.debug(CLASS_NAME, "full path = " + outFile.getAbsolutePath());
				}
				FileOutputStream outStream = new FileOutputStream(outFile);

				outStream.write(data);
				outStream.close();
				input.close();
				
				String fileAllName = saveScaleImage(PRODUCT_PATH + filePath1 + "/", fileName + fileExt, ImageUtils.MEDIUM_WIDTH, ImageUtils.MEDIUM_HEIGHT, ImageUtils.EXT_MEDIUM_SIZE);
				return map;
			}
		}
		
		return null;
	}
	/**
	 * 
	 * @param request
	 * @param inputFileName
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> getFileNameByStoreId(
			HttpServletRequest request, String inputFileName, long storeId) throws Exception {
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			LoggerUtils.debug(CLASS_NAME, "Spring3 MVC upload file with Multipart form");

			MultipartFile file = multipartRequest.getFiles(inputFileName)
					.get(0);
			long size = file.getSize();
			if(size>0){
				Map<String, String> map = new HashMap<String, String>();
				byte[] data = new byte[(int) size];
				InputStream input = file.getInputStream();
				input.read(data);

				// create file, if no app context path, will throws access denied.
				// seems like you could not create any file at tomcat/bin
				// directory!!!

				LoggerUtils.debug(CLASS_NAME, "file.getContentType() ==> " + file.getContentType());
				Long filePath1 = mmNum(storeId,5000);
				String fileName = String.valueOf(storeId);
				map.put(FILE_NAME, fileName);
				LoggerUtils.debug(CLASS_NAME, "fileName ==> " + fileName);
				String fileExt = "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
				map.put(FILE_EXT, fileExt);
				LoggerUtils.debug(CLASS_NAME, "fileExt ==> " + fileExt);
				String wholeFileName = STORE_HEAD_PATH +filePath1+"/"+ fileName + fileExt;
				LoggerUtils.debug(CLASS_NAME, "file WholeName ==> " + wholeFileName);
				File outFile = new File(wholeFileName);

				if (!outFile.exists()) {
					makeDir(outFile.getParentFile());
					outFile.createNewFile();
					LoggerUtils.debug(CLASS_NAME, "full path = " + outFile.getAbsolutePath());
				} else {
					LoggerUtils.debug(CLASS_NAME, "full path = " + outFile.getAbsolutePath());
				}
				FileOutputStream outStream = new FileOutputStream(outFile);

				outStream.write(data);
				outStream.close();
				input.close();
				
				String fileAllName = saveScaleImage(STORE_HEAD_PATH + filePath1 + "/", fileName + fileExt, ImageUtils.SMALL_SIZE, ImageUtils.SMALL_SIZE, ImageUtils.EXT_SMALL_SIZE);
				return map;
			}
		}
		
		return null;
	}
	
	
	/**
	 * 
	 * @param request
	 * @param inputFileName
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	public static List<Map<String, String>> getCaseFileNamesById(
			HttpServletRequest request, String inputFileName, long caseId) throws Exception {
		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			LoggerUtils.debug(CLASS_NAME, "Spring3 MVC upload file with Multipart form");
			
			
			List<MultipartFile> fileList = multipartRequest.getFiles(inputFileName);
			for(MultipartFile file : fileList){
				long size = file.getSize();
				if(size>0){
					Map<String, String> map = new HashMap<String, String>();
					byte[] data = new byte[(int) size];
					InputStream input = file.getInputStream();
					input.read(data);
	
					// create file, if no app context path, will throws access denied.
					// seems like you could not create any file at tomcat/bin
					// directory!!!
					LoggerUtils.debug(CLASS_NAME, "file.getContentType() ==> " + file.getContentType());
					Long filePath1 = mmNum(caseId,5000);
					String filePath2 = String.valueOf(caseId);
					LoggerUtils.debug(CLASS_NAME, "filePath ==> " + filePath1+filePath2);
					String fileName = String.valueOf(System.currentTimeMillis())+UUID.randomUUID().toString().replace("-", "");;
					map.put(FILE_NAME, fileName);
					LoggerUtils.debug(CLASS_NAME, "fileName ==> " + fileName);
					String fileExt = "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
					map.put(FILE_EXT, fileExt);
					LoggerUtils.debug(CLASS_NAME, "fileExt ==> " + fileExt);
					String wholeFileName = CASE_PATH + filePath1 + "/" + filePath2 + "/" + fileName + fileExt;
					
					LoggerUtils.debug(CLASS_NAME, "file WholeName ==> " + wholeFileName);
					File outFile = new File(wholeFileName);
	
					if (!outFile.exists()) {
						makeDir(outFile.getParentFile());
						outFile.createNewFile();
						LoggerUtils.debug(CLASS_NAME, "full path = " + outFile.getAbsolutePath());
					} else {
						LoggerUtils.debug(CLASS_NAME, "full path = " + outFile.getAbsolutePath());
					}
					FileOutputStream outStream = new FileOutputStream(outFile);
	
					outStream.write(data);
					outStream.close();
					input.close();
					
					String fileAllName = saveScaleImage(CASE_PATH + filePath1 + "/"+ filePath2 + "/", fileName + fileExt, ImageUtils.SMALL_SIZE, ImageUtils.SMALL_SIZE, ImageUtils.EXT_SMALL_SIZE);
					maps.add(map);
				}
			}
			
		}
		return maps;
	}
	/** 两数相除，有余数取整加1 */
	public static long mmNum(long a,long b){   
	    return   (a%b==0)?a/b:(a/b+1); 
	}
	
	   /**
     * 
     * @param request
     * @param inputFileName
     * @return
     * @throws IOException
     */
    public static Map<String, String> getFileNameByUpload(HttpServletRequest request, String inputFileName, String uploadPath) throws Exception {
        
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            LoggerUtils.debug(CLASS_NAME, "Spring3 MVC upload file with Multipart form");

            MultipartFile file = multipartRequest.getFiles(inputFileName).get(0);
            long size = file.getSize();
            if(size>0){
                Map<String, String> map = new HashMap<String, String>();
                byte[] data = new byte[(int) size];
                InputStream input = file.getInputStream();
                input.read(data);

                // create file, if no app context path, will throws access denied.
                // seems like you could not create any file at tomcat/bin
                // directory!!!

                LoggerUtils.debug(CLASS_NAME, "file.getContentType() ==> " + file.getContentType());
                String fileName = getFileNameByUploadPath(uploadPath);
                map.put(FILE_NAME, fileName);
                LoggerUtils.debug(CLASS_NAME, "fileName ==> " + fileName);
                String fileExt = "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
                map.put(FILE_EXT, fileExt);
                LoggerUtils.debug(CLASS_NAME, "fileExt ==> " + fileExt);
                String wholeFileName = REAL_PATH + fileName + fileExt;
                LoggerUtils.debug(CLASS_NAME, "file WholeName ==> " + wholeFileName);
                File outFile = new File(wholeFileName);

                if (!outFile.exists()) {
                    makeDir(outFile.getParentFile());
                    outFile.createNewFile();
                    LoggerUtils.debug(CLASS_NAME, "full path = " + outFile.getAbsolutePath());
                } else {
                    LoggerUtils.debug(CLASS_NAME, "full path = " + outFile.getAbsolutePath());
                }
                FileOutputStream outStream = new FileOutputStream(outFile);

                outStream.write(data);
                outStream.close();
                input.close();
                
                return map;
            }
        }
        
        return null;
    }
	
	/**
	 * 
	 * @param request
	 * @param inputFileName
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> getFileNameByUpload(HttpServletRequest request, String inputFileName) throws Exception {
	    
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			LoggerUtils.debug(CLASS_NAME, "Spring3 MVC upload file with Multipart form");

			MultipartFile file = multipartRequest.getFiles(inputFileName).get(0);
			long size = file.getSize();
			if(size>0){
				Map<String, String> map = new HashMap<String, String>();
				byte[] data = new byte[(int) size];
				InputStream input = file.getInputStream();
				input.read(data);

				// create file, if no app context path, will throws access denied.
				// seems like you could not create any file at tomcat/bin
				// directory!!!

				LoggerUtils.debug(CLASS_NAME, "file.getContentType() ==> " + file.getContentType());
				String fileName = getWholeFileName();
				map.put(FILE_NAME, fileName);
				LoggerUtils.debug(CLASS_NAME, "fileName ==> " + fileName);
				String fileExt = "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
				map.put(FILE_EXT, fileExt);
				LoggerUtils.debug(CLASS_NAME, "fileExt ==> " + fileExt);
				String wholeFileName = REAL_PATH + fileName + fileExt;
				LoggerUtils.debug(CLASS_NAME, "file WholeName ==> " + wholeFileName);
				File outFile = new File(wholeFileName);

				if (!outFile.exists()) {
					makeDir(outFile.getParentFile());
					outFile.createNewFile();
					LoggerUtils.debug(CLASS_NAME, "full path = " + outFile.getAbsolutePath());
				} else {
					LoggerUtils.debug(CLASS_NAME, "full path = " + outFile.getAbsolutePath());
				}
				FileOutputStream outStream = new FileOutputStream(outFile);

				outStream.write(data);
				outStream.close();
				input.close();
				
				return map;
			}
		}
		
		return null;
	}

	/**
	 * Save image as 640X320 size
	 * 
	 * @param realDirPath
	 * @param fileExt
	 * @throws Exception
	 */
	public static void save640X320Image(String realDirPath, String fileExt)
			throws Exception {
		String outFilePath = realDirPath + UNDERLINE
				+ ImageUtils.EXT_MEDIUM_SIZE + fileExt;
		ImageUtils.saveImageAsJpg(realDirPath + fileExt, outFilePath,
				ImageUtils.MEDIUM_WIDTH, ImageUtils.MEDIUM_HEIGHT);
	}
	
	/**
	 * Save image as 200X200 size
	 * 
	 * @param realDirPath
	 * @param fileExt
	 * @throws Exception
	 */
	public static void save200X200Image(String realDirPath, String fileExt)
			throws Exception {
		String outFilePath = realDirPath + UNDERLINE
				+ ImageUtils.EXT_LANGE_SIZE + fileExt;
		ImageUtils.saveImageAsJpg(realDirPath + fileExt, outFilePath,
				ImageUtils.LANGE_SIZE, ImageUtils.LANGE_SIZE);
	}

	/**
	 * Save image as 80X80 size
	 * 
	 * @param realDirPath
	 * @param fileExt
	 * @throws Exception
	 */
	public static void save80X80Image(String realDirPath, String fileExt)
			throws Exception {
		String outFilePath = realDirPath + UNDERLINE
				+ ImageUtils.EXT_SMALL_SIZE + fileExt;
		ImageUtils.saveImageAsJpg(realDirPath + fileExt, outFilePath,
				ImageUtils.SMALL_SIZE, ImageUtils.SMALL_SIZE);
	}
	
	/**
	 * Save image as 300X300 size
	 * 
	 * @param realDirPath
	 * @param fileExt
	 * @throws Exception
	 */
	public static String saveScaleImage(String realDirPath, String fileExt, int width, int height, String imageFilePref)
			throws Exception {

		String outFilePath = realDirPath + imageFilePref  + UNDERLINE + fileExt;

		Image2Utils.scale2(realDirPath + fileExt, outFilePath, width, height, true);
		return imageFilePref + UNDERLINE + fileExt;
	}	

	/**
     * Get the whole file name
     * 
     * @param fileName
     * @return
     */
    public static String getFileNameByUploadPath(String uploadPath) {
        StringBuffer sb = new StringBuffer();
        sb.append(uploadPath).append(UUID.randomUUID());
        return sb.toString();
    }
	
	/**
	 * Get the whole file name
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getWholeFileName() {
		StringBuffer sb = new StringBuffer(UPLOAD_PATH);
		sb.append(todayToString()).append(SEPARATOR)
				.append(UUID.randomUUID());

		return sb.toString();
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean isSucc4DelFile(String sPath) {
		boolean flag = false;
		LoggerUtils.debug(CLASS_NAME, sPath);
		if(isNoEmpty(sPath)){
			File file = new File(sPath);
			// 路径为文件且不为空则进行删除
			if (file.isFile() && file.exists()) {
				LoggerUtils.debug(CLASS_NAME, "File is delete successfully!");
				file.delete();
				flag = true;
			}
		}
		
		return flag;
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(SEPARATOR)) {
			sPath = sPath + SEPARATOR;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = isSucc4DelFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Create package
	 * 
	 * @param dir
	 */
	public static void makeDir(File dir) {
		if (!dir.getParentFile().exists()) {
			makeDir(dir.getParentFile());
		}
		dir.mkdir();
	}
	
	public static String todayToString() {
		return new LocalDate().toString("yyMMdd");
	}
	
	public static boolean isNoEmpty(String str) {
		if (str != null && !"".equals(str.trim())) {
			return true;
		}

		return false;
	}
}
