package com.taoists.weixin.menu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.taoists.common.Const;
import com.taoists.common.orm.entity.BaseEntity;

/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-11-20 上午10:05:15  
 *  
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "weixin_menu")
public class MainMenu extends BaseEntity{
	
	private String title;
	private int size = 0;
	private String value = Const.NULL_VALUE_STRING;
	private String type;
	@Column(name="order_by")
	private String orderBy;
	private Long companyId = Const.NULL_VALUE_LONG;
		
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	
}
