package com.taoists.common.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.taoists.common.Const;
import com.taoists.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
@SuppressWarnings("serial")
@MappedSuperclass
public class NodeModel extends BaseEntity {

	public static final String CHECKED = "checked";

	private Boolean expanded = true;
	private Boolean leaf = false;
	private Integer width = 1;
	private Integer status = Const.ENABLE;

	@Transient
	private boolean last;
	@Transient
	private boolean first;
	@Transient
	private String[] rows;
	@Transient
	private String checked;
	@Transient
	private int level;
	@Transient
	private List<? extends NodeModel> child = new ArrayList<NodeModel>();

	@Transient
	public String getIndent() {
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i < getWidth(); i++) {
			sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		}
		return sb.toString();
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public Integer getWidth() {
		if (width == null) {
			return 0;
		}
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public String[] getRows() {
		return rows;
	}

	public void setRows(String[] rows) {
		this.rows = rows;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<? extends NodeModel> getChild() {
		return child;
	}

	public void setChild(List<? extends NodeModel> child) {
		this.child = child;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
