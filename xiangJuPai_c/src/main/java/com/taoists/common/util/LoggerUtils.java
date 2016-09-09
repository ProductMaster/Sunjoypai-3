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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jiafa Lv
 * @email simon-jiafa@126.com
 * @date 2014-6-13 下午4:46:00
 */
public class LoggerUtils {
	private static Logger logger = LoggerFactory.getLogger(LoggerUtils.class);
	private static final boolean IS_LOG = true;
	
	/**
	 * DEBUG信息
	 * 
	 * @param className
	 * @param content
	 */
	public static void debug(String className, String content){
		if(IS_LOG) {
			logger.debug("["+className+"] "+content);
		}
	}
	
	/**
	 * DEBUG信息
	 * 
	 * @param className
	 * @param content
	 */
	public static void debug(String className, String content, Object obj){
		if(IS_LOG) {
			logger.debug("["+className+"] "+content, obj);
		}
	}
	
	/**
	 * INFO信息
	 * 
	 * @param className
	 * @param content
	 */
	public static void info(String className, String content){
		if(IS_LOG) {
			logger.info("["+className+"] "+content);
		}
	}
	
	/**
	 * ERROR信息
	 * 
	 * @param className
	 * @param content
	 */
	public static void error(String className, String content){
		if(IS_LOG) {
			logger.error("["+className+"] "+content);
		}
	}
	
	/**
	 * ERROR信息
	 * 
	 * @param className
	 * @param content
	 */
	public static void error(String className, String content, Exception e){
		if(IS_LOG) {
			logger.error("["+className+"] "+content, e);
		}
	}
	
	/**
	 * ERROR信息
	 * 
	 * @param className
	 * @param content
	 */
	public static void error(String className, String content, String message){
		if(IS_LOG) {
			logger.error("["+className+"] "+content, message);
		}
	}
	
	/**
	 * WARN信息
	 * 
	 * @param className
	 * @param content
	 */
	public static void warn(String className, String content){
		if(IS_LOG) {
			logger.warn("["+className+"] "+content);
		}
	}
}
