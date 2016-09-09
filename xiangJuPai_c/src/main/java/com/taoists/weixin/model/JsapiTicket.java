package com.taoists.weixin.model;

import com.taoists.common.Const;
import com.taoists.common.util.DateUtils;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2015年6月6日 上午2:15:22 
 * 类说明 
 */
public class JsapiTicket {

	// 获取到的凭证  
    private String ticket = Const.NULL_VALUE_STRING;  
    // 凭证有效时间，单位：秒  
    private int expiresIn = 0;  
    // 创建时间
	private long createDate= System.currentTimeMillis();
	
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
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
