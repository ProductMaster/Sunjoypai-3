package com.taoists.store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.taoists.common.Const;
import com.taoists.common.orm.entity.BaseEntity;
import com.taoists.user.entity.User;
@Entity
@Table(name="store_case_imgs")
public class StoreCaseImgs extends BaseEntity{

	@Column(name="img_url")
	private String imgUrl = Const.NULL_VALUE_STRING;  //图片
	@Column(name="store_id")
	private long storeId = Const.NULL_VALUE_LONG;  //店铺Id
	
	
    @ManyToOne
    @JoinColumn(name = "case_id")
    private StoreCase storeCase;


	public String getImgUrl() {
		return imgUrl;
	}


	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}


	public long getStoreId() {
		return storeId;
	}


	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}


	public StoreCase getStoreCase() {
		return storeCase;
	}


	public void setStoreCase(StoreCase storeCase) {
		this.storeCase = storeCase;
	}
    
    
}
