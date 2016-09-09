package com.taoists.sys.sysNoRule.entity; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.taoists.common.orm.entity.BaseEntity;

@Entity
@Table(name="sys_norule")
public class SysNoRule extends BaseEntity {
	private static final long serialVersionUID = 1L;

	public static final long NO_TYPE_ALIPAY_BATCH_NO = 44; // 批次号（用于支付宝转账）
	public static final long NO_TYPE_ALIPAY_SERIAL_NO = 44; // 流水号（用于支付宝转账）
	// fields
	@Column(name="norule_name")
	private java.lang.String noruleName;
	@Column(name="no_serial")
	private long noSerial;
	@Column(name="no_prefix")
	private java.lang.String noPrefix;
	@Column(name="serial_length")
	private long serialLength;
	@Column(name="coding_pattern")
	private int codingPattern;
	@Column(name="year_pattern")
	private int yearPattern;
	@Column(name="is_default")
	private int isDefault;
	@Column(name="no_version")
	private java.lang.String noVersion;
	@Column(name="no_type")
	private int noType;
	@Column(name="serial_pattern")
	private String serialPattern;
	
	// constVar
	public static final long NO_TYPE_COUPON_VOUCHER = -1;
	public static final long NO_TYPE_ORDER = 1;
	
	public final static long INITIAL_NO_SERIAL=0;	
	
	public java.lang.String getNoruleName() {
		return noruleName;
	}
	public void setNoruleName(java.lang.String noruleName) {
		this.noruleName = noruleName;
	}
	public long getNoSerial() {
		return noSerial;
	}
	public void setNoSerial(long noSerial) {
		this.noSerial = noSerial;
	}
	public java.lang.String getNoPrefix() {
		return noPrefix;
	}
	public void setNoPrefix(java.lang.String noPrefix) {
		this.noPrefix = noPrefix;
	}
	public long getSerialLength() {
		return serialLength;
	}
	public void setSerialLength(long serialLength) {
		this.serialLength = serialLength;
	}
	public int getCodingPattern() {
		return codingPattern;
	}
	public void setCodingPattern(int codingPattern) {
		this.codingPattern = codingPattern;
	}
	public int getYearPattern() {
		return yearPattern;
	}
	public void setYearPattern(int yearPattern) {
		this.yearPattern = yearPattern;
	}
	public int getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}
	public java.lang.String getNoVersion() {
		return noVersion;
	}
	public void setNoVersion(java.lang.String noVersion) {
		this.noVersion = noVersion;
	}
	public int getNoType() {
		return noType;
	}
	public void setNoType(int noType) {
		this.noType = noType;
	}
	public String getSerialPattern() {
		return serialPattern;
	}
	public void setSerialPattern(String serialPattern) {
		this.serialPattern = serialPattern;
	}


}