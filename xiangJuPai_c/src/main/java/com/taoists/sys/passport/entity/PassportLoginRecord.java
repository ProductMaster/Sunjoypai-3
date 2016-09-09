package com.taoists.sys.passport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;




import com.taoists.common.orm.entity.BaseEntity;
import com.taoists.common.util.DateUtils;

 

@Entity
@Table(name="passport_login_record")
public class PassportLoginRecord extends BaseEntity {
	private static final long serialVersionUID = 1L;

	public static final int LOGIN_STATUS_FAILURE = 0;
	public static final int LOGIN_STATUS_SUCCESS = 1;
	
	// fields
	@Column(name = "login_date")
	private long loginDate=-99;
	@Column(name = "pass_id")
	private long passId=-99; 
	@Column(name = "nickname")
	private String nickname="";
	@Column(name = "login_name")
	private String loginName="";
	@Column(name = "status")
	private int status=LOGIN_STATUS_SUCCESS;
	@ManyToOne
	@JoinColumn(name = "login_ip_id")
	private PassportLoginIp passportLoginIp;

	public long getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(long loginDate) {
		this.loginDate = loginDate;
	}
	public long getPassId() {
		return passId;
	}
	public void setPassId(long passId) {
		this.passId = passId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public PassportLoginIp getPassportLoginIp() {
		return passportLoginIp;
	}
	public void setPassportLoginIp(PassportLoginIp passportLoginIp) {
		this.passportLoginIp = passportLoginIp;
	}
	
	/*************************************************************/
	public String getStrStatus(){
		String res = "";
		switch(status){
		case LOGIN_STATUS_FAILURE:
			res="登录失败";
			break;
		case LOGIN_STATUS_SUCCESS:
			res="登录成功";
			break;
		}
		return res;
	}
	public String getStrLoginDate(){
		return DateUtils.long2ShortString(loginDate);
	}
	/*************************************************************/

}