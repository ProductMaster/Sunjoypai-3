package com.taoists.weixin.model;

import com.taoists.common.Const;
import com.taoists.common.util.DateUtils;

/**
 *   @author George 
 *   E-mail:lendun@163.com
 *   @version 创建时间： 2013-11-15 上午10:21:342013-11-15
 *
 */

public class AccessToken {

	// 获取到的凭证  
    private String token = Const.NULL_VALUE_STRING;  
    // 凭证有效时间，单位：秒  
    private int expiresIn = 0;  
    // 创建时间
	private long createDate = System.currentTimeMillis();
        
	public String getToken() {  
        return token;  
    }  
        
    public void setToken(String token) {  
        this.token = token;  
    }  
        
    public int getExpiresIn() {  
        return expiresIn;  
    }  
        
    public void setExpiresIn(int expiresIn) {  
        this.expiresIn = expiresIn;  
    }

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}  
	
	   public String getCreateDateStr() {
	        if (createDate != Const.NULL_VALUE_LONG) {
	            return DateUtils.long2ShortString(createDate);
	        } else {
	            return Const.NULL_VALUE_DISPLAY;
	        }
	    }
}
