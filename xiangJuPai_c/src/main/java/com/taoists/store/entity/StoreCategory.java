package com.taoists.store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.taoists.common.Const;
import com.taoists.common.orm.entity.BaseEntity;

@Entity
@Table(name="store_category")
public class StoreCategory extends BaseEntity{

	// fields
	@Column(name="cate_name")
	private java.lang.String cateName;
	@Column(name="parent_id")
	private long parentId;
	@Column(name="level")
	private int level;
	@Column(name="sub_count")
	private int subCount;
	@Column(name="cate_no")
	private java.lang.String cateNo;
	@Column(name="sub_cateno")
	private java.lang.String subCateno;
	@Column(name = "is_home")
	private int isHome = Const.YES;
	
	public java.lang.String getCateName() {
		return cateName;
	}
	public void setCateName(java.lang.String cateName) {
		this.cateName = cateName;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getSubCount() {
		return subCount;
	}
	public void setSubCount(int subCount) {
		this.subCount = subCount;
	}
	public java.lang.String getCateNo() {
		return cateNo;
	}
	public void setCateNo(java.lang.String cateNo) {
		this.cateNo = cateNo;
	}
	public java.lang.String getSubCateno() {
		return subCateno;
	}
	public void setSubCateno(java.lang.String subCateno) {
		this.subCateno = subCateno;
	}
	public int getIsHome() {
		return isHome;
	}
	public void setIsHome(int isHome) {
		this.isHome = isHome;
	}
}
