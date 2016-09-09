package com.taoists.sys.passport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.util.HtmlUtils;

import com.taoists.common.Const;
import com.taoists.common.orm.entity.BaseEntity;


@Entity
@Table(name="passport")
public class Passport extends BaseEntity {
	private static final long serialVersionUID = 1L;
	public static final long PASSPORT_ID_ADMIN = 1l;
	public static final String PASSPORT_NICK_ADMIN = "管理员";
	public static final int PASSTYPE_NORMAL = 0;
	public static final int PASSTYPE_ADMIN = 1;

	@Transient
	private int hashCode = Integer.MIN_VALUE;

	// fields
	@Column(name = "pass_ip")
	private String passIp = Const.NULL_VALUE_STRING;
	@Column(name="email")
	private java.lang.String email = Const.NULL_VALUE_STRING;
	@Column(name="password")
	private java.lang.String password;
	@Column(name="pass_type")
	private int passType = 0;
	@Column(name="login_name")
	private java.lang.String loginName;
	@Column(name="sex")
	private int sex = Const.SEX_UNKNOW;
	@Column(name="phone")
	private java.lang.String phone = Const.NULL_VALUE_STRING;
	@Column(name="mobile")
	private java.lang.String mobile = Const.NULL_VALUE_STRING;
	@Column(name="memo")
	private java.lang.String memo = Const.NULL_VALUE_STRING;
	@Column(name="nickname")
	private java.lang.String nickname = Const.NULL_VALUE_STRING;
	@Column(name="pass_no")
	private String passNo= Const.NULL_VALUE_STRING;
	@Column(name = "status")
	private int status = Const.ENABLE;


	/**
	 * Return the value associated with the column: email
	 */
	public java.lang.String getEmail () {
		return email;
	}

	/**
	 * Set the value related to the column: email
	 * @param email the email value
	 */
	public void setEmail (java.lang.String email) {
		this.email = email;
	}



	/**
	 * Return the value associated with the column: password
	 */
	public java.lang.String getPassword () {
		return password;
	}

	/**
	 * Set the value related to the column: password
	 * @param password the password value
	 */
	public void setPassword (java.lang.String password) {
		this.password = password;
	}

	/**
	 * Return the value associated with the column: passType
	 */
	public int getPassType () {
		return passType;
	}

	/**
	 * Set the value related to the column: passType
	 * @param passType the passType value
	 */
	public void setPassType (int passType) {
		this.passType = passType;
	}



	/**
	 * Return the value associated with the column: login_name
	 */
	public java.lang.String getLoginName () {
		return loginName;
	}

	/**
	 * Set the value related to the column: login_name
	 * @param loginName the login_name value
	 */
	public void setLoginName (java.lang.String loginName) {
		this.loginName = loginName;
	}



	/**
	 * Return the value associated with the column: sex
	 */
	public int getSex () {
		return sex;
	}

	/**
	 * Set the value related to the column: sex
	 * @param sex the sex value
	 */
	public void setSex (int sex) {
		this.sex = sex;
	}



	/**
	 * Return the value associated with the column: phone
	 */
	public java.lang.String getPhone () {
		return phone;
	}

	/**
	 * Set the value related to the column: phone
	 * @param phone the phone value
	 */
	public void setPhone (java.lang.String phone) {
		this.phone = phone;
	}



	/**
	 * Return the value associated with the column: mobile
	 */
	public java.lang.String getMobile () {
		return mobile;
	}

	/**
	 * Set the value related to the column: mobile
	 * @param mobile the mobile value
	 */
	public void setMobile (java.lang.String mobile) {
		this.mobile = mobile;
	}



	/**
	 * Return the value associated with the column: memo
	 */
	public java.lang.String getMemo () {
		return memo;
	}

	/**
	 * Set the value related to the column: memo
	 * @param memo the memo value
	 */
	public void setMemo (java.lang.String memo) {
		this.memo = memo;
	}



	/**
	 * Return the value associated with the column: nickname
	 */
	public java.lang.String getNickname () {
		return nickname;
	}

	/**
	 * Set the value related to the column: nickname
	 * @param nickname the nickname value
	 */
	public void setNickname (java.lang.String nickname) {
		this.nickname = HtmlUtils.htmlEscape(nickname);//转换字符 防止HTML被破坏	
	}

	public String getPassNo() {
		return passNo;
	}

	public void setPassNo(String passNo) {
		this.passNo = passNo;
	}

	public String getPassIp() {
		return passIp;
	}

	public void setPassIp(String passIp) {
		this.passIp = passIp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}