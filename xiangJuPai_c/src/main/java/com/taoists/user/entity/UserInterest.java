package com.taoists.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.taoists.common.Const;
import com.taoists.common.orm.entity.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name="user_interest")
public class UserInterest extends BaseEntity{
	// fields
	@Column(name="store_id")
	private long storeId = Const.NULL_VALUE_LONG;
	@Column(name = "user_id")
	private long userId = Const.NULL_VALUE_LONG;
	public long getStoreId() {
		return storeId;
	}
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
}
