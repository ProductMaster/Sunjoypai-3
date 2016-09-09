package com.taoists.weixin.model; 
/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2015年5月18日 上午3:05:24 
 * 类说明 
 */
public class WeixinOpenUser {
	
	private String openid;
	private String state;
	private String nickname;
	private String sex;
	private String city;
	private String province;
	private String language;
	private String headimgurl;
	
	public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getHeadimgurl() {
        return headimgurl;
    }
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }
    public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
}
