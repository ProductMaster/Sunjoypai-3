package com.taoists.weixin.menu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.taoists.common.orm.entity.BaseEntity;

/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-11-20 上午10:40:44  
 *  
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "weixin_sub_menu")
public class SubMenu extends BaseEntity{
	
	private String title;
	private String value;
	private String type;
	@Column(name="order_by")
	private String orderBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "weixin_menu_id")
	private MainMenu mainMenu;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public MainMenu getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
