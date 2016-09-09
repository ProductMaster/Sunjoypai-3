package com.taoists.sys.passport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.taoists.common.orm.entity.BaseEntity;

@Entity
@Table(name="passport_login_ip")
public class PassportLoginIp extends BaseEntity {
	private static final long serialVersionUID = 1L;

	public static final int IP_STATUS_OPEN = 0;
	public static final int IP_STATUS_CLOSE = 1;
	
	private static final int IP_FAILURE_COUNT = 10;
	
	// fields
	@Column(name = "login_ip") 
	private String loginIp="";
	@Column(name = "status")
	private int status=IP_STATUS_OPEN;
	@Column(name = "failure_count")
	private int failureCount = IP_FAILURE_COUNT;
	public int getFailureCount() {
		return failureCount;
	}
	public void setFailureCount(int failureCount) {
		this.failureCount = failureCount;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	/*************************************************************/
	public String getStrStatus(){
		String res = "";
		switch(status){
		case IP_STATUS_OPEN:
			res="正常";
			break;
		case IP_STATUS_CLOSE:
			res="封闭";
			break;
		}
		return res;
	}
	/*************************************************************/

}