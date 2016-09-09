package com.taoists.sys.menu.entity;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.taoists.common.entity.NodeModel;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sys_menu")
public class Menu extends NodeModel {

	private String name;
	private String icon;
	private String label;
	private String action;
	private Integer orderValue;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parentId")
	private Menu parent;

	@Transient
	public String getIndent() {
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i < getWidth(); i++) {
			sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		}
		return sb.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}
}
