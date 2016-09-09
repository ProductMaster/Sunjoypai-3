package com.taoists.sys.zoneCode.entity;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.taoists.common.orm.entity.BaseEntity;

/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2012-7-25 下午05:07:52  
 *  
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "zone_code")
public class ZoneCode extends BaseEntity {
	
	@Column(name = "zone_no")
	private String zoneNo;
	@Column(name = "zone_name")
	private String zoneName;	
	private Integer status;
	@Column(name = "father_no")
	private String fatherNo;	
	private Integer level;
	public String getZoneNo() {
		return zoneNo;
	}
	public void setZoneNo(String zoneNo) {
		this.zoneNo = zoneNo;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getFatherNo() {
		return fatherNo;
	}
	public void setFatherNo(String fatherNo) {
		this.fatherNo = fatherNo;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
}
