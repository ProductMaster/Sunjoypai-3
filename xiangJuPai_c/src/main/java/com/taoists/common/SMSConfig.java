package com.taoists.common;

import java.util.Map;

/**
 * @author Enjoy E-mail:wangyi2200@yahoo.com.cn
 * @version 创建时间：Jan 18, 2011 3:29:52 PM
 * 类说明
 */
public class SMSConfig {
public static Map<String,String> configMap;//配置map
	
	public static int getInt(String key){
		return Integer.parseInt(getString(key));
	}
	
	public static long getLong(String key){
		return Long.parseLong(getString(key));
	}
	
	public static String getString(String key){
		return configMap.get(key).trim();
	}
	public static synchronized void setConfigMap(Map configMap2) {
		configMap = configMap2;
	}
	
	public static String getStringByParameters(String key , String[] parameters, String[] values){
		String content = getString(key);//原来的内容		for(int i = 0 ; i <parameters.length ; i++){
			content = content.replaceAll(parameters[i], values[i]); 
		}
		return content;
	}

}
