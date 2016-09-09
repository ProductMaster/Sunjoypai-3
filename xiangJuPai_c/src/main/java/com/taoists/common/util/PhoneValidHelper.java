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

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Jiafa Lv
 * @email simon-jiafa@126.com
 * @date 2014-6-13 下午5:57:55
 */
public class PhoneValidHelper {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String CLASS_NAME = PhoneValidHelper.class.getName();
	
	/**
	 * 获得六位数字
	 * 
	 * @param session
	 * @return
	 */
	public static String getValidCode(String phone, HttpSession session) {
		String code = "";
		
		if(StringUtils.isEmpty(phone)){
			return code;
		}
		
		while (code.length() < 6)
			code += (int) (Math.random() * 10);

		session.setAttribute(phone, code);
		
		return code;
	}
	
	/**
	 * 输入的数字进行比较
	 * 
	 * @param code
	 * @param session
	 * @return
	 */
	public static boolean isSameCode(String code, String phone, HttpSession session){
		if(StringUtils.isEmpty(phone)){
			return false;
		}
		String oldCode = (String)session.getAttribute(phone);
		if(!StringUtils.isEmpty(oldCode) && !StringUtils.isEmpty(code)){
			if(oldCode.equals(code)){
				return true;
			}
		}
		
		return false;
	}
	
}
