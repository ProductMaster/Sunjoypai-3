package com.taoists.common.controller;

import java.util.Comparator;


/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-6-20 下午01:48:35  
 *  
 */
public class SimpleStatisObjectComparator  implements Comparator<SimpleStatisObject>{

	 public int compare(SimpleStatisObject o1, SimpleStatisObject o2) {  
		 int flag=o1.getaStr().compareTo(o2.getaStr());
		 return flag;		   
	 }
}
