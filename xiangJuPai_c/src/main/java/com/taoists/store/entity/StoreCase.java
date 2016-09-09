package com.taoists.store.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.taoists.common.Const;
import com.taoists.common.orm.entity.BaseEntity;
import com.taoists.common.util.UploadHelper;

@Entity
@Table(name="store_case")
public class StoreCase extends BaseEntity{
	
	// fields
	@Column(name="title")
	private java.lang.String title = Const.NULL_VALUE_STRING;
	@Column(name="descr")
	private String describe = Const.NULL_VALUE_STRING;
	@Column(name="is_show")
	private int isShow = Const.YES;
	
    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreInfo storeInfo;
    @OneToMany(mappedBy="storeCase")
    private List<StoreCaseImgs> imgs;
    
	@Transient
	private String path = Const.NULL_VALUE_STRING;   //图片路径
	
	
	public int getIsShow() {
		return isShow;
	}
	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}
	public String getPath() {
		return String.valueOf(UploadHelper.mmNum(this.getId(),5000))+"/";
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<StoreCaseImgs> getImgs() {
		return imgs;
	}
	public void setImgs(List<StoreCaseImgs> imgs) {
		this.imgs = imgs;
	}
	public java.lang.String getTitle() {
		return title;
	}
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public StoreInfo getStoreInfo() {
		return storeInfo;
	}
	public void setStoreInfo(StoreInfo storeInfo) {
		this.storeInfo = storeInfo;
	}

}
