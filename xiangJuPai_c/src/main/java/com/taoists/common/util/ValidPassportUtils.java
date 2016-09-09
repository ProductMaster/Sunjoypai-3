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


/**
 * @author Simon Lv
 * @email simon-jiafa@126.com
 * @date 2015-1-14 下午2:44:12
 */
public class ValidPassportUtils {
	private static final String PASSPORT = "fornowSuper";
	private static String TODAY = null;
	private static String PASSCODE = null;
	
	/**
	 * 验证传参的验证码是否一致
	 * 
	 * @param validCode
	 * @return
	 */
	public static boolean comparePassport(String validCode) {
		if(StringUtils.isNoEmpty(validCode)){
			getPossportNow();
			if(PASSCODE.equals(validCode)){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 获得加密后的字符串
	 * 
	 * @return
	 */
	private static void getPossportNow() {
		String date = DateUtils.todayToString();
		if(TODAY == null || !TODAY.equals(date)){
			StringBuffer possportNow = new StringBuffer();
			possportNow.append(PASSPORT).append(date);
			
			PASSCODE = CipherUtil.generatePossPort(possportNow.toString());
		}
	}
}
