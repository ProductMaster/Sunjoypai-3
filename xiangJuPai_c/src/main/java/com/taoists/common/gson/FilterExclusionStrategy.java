/**
 * 
 */
package com.taoists.common.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/** 
 * 类说明 
 * @author George 
 * @date 2015-1-24  
 */
public class FilterExclusionStrategy implements ExclusionStrategy {
	
    private String[] excludeFields;  
    private Class<?>[] excludeClasses;  
	
    public FilterExclusionStrategy(){}
    
    public FilterExclusionStrategy(String[] excludeFields,  Class<?>[] excludeClasses) {  
        this.excludeFields = excludeFields;  
        this.excludeClasses = excludeClasses;  
    }  
  
    public void setFields(String[] excludeFields){
    	this.excludeFields = excludeFields;
    }
    
    public void setClasses(Class<?>[] excludeClasses){
    	this.excludeClasses = excludeClasses;
    }
    
    public boolean shouldSkipClass(Class<?> clazz) {  
        if (this.excludeClasses == null) {  
            return false;  
        }  
  
        for (Class<?> excludeClass : excludeClasses) {  
            if (excludeClass.getName().equals(clazz.getName())) {  
                return true;  
            }  
        }  
  
        return false;  
    }  
  
    public boolean shouldSkipField(FieldAttributes f) {  
        if (this.excludeFields == null) {  
            return false;  
        }  
  
        for (String field : this.excludeFields) {  
            if (field.equals(f.getName())) {  
                return true;  
            }  
        }  
  
        return false;  
    }  
  
    public final String[] getExcludeFields() {  
        return excludeFields;  
    }  
  
    public final Class<?>[] getExcludeClasses() {  
        return excludeClasses;  
    }  
}  