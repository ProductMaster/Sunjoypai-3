package com.taoists.weixin.model;

import com.taoists.common.Const;
import com.taoists.common.util.DateUtils;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2015年6月6日 下午5:58:19 
 * 类说明 
 */
public class WeixinOpenId{

	// 获取到的凭证  
    private String token = Const.NULL_VALUE_STRING;  
    // 凭证有效时间，单位：秒  
    private int expiresIn = 0;  
    // 创建时间
	private long createDate = System.currentTimeMillis();
	// openid
	private String openId = Const.NULL_VALUE_STRING;
	// refresh token
	private String refreshToken = Const.NULL_VALUE_STRING;
        
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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}  
    public String getCreateDateStr() {
        if (createDate != Const.NULL_VALUE_LONG) {
            return DateUtils.long2ShortString(createDate);
        } else {
            return Const.NULL_VALUE_DISPLAY;
        }
    }	
}
