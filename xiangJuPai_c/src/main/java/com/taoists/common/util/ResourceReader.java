package com.taoists.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ResourceReader {
    
    public static String getValue(String key){
    	return getValue("config.properties",key);
    }
    
    public static String getValue(String fileName , String key){
    	Properties props = new Properties();
    	try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props.getProperty(key);
    }
}
