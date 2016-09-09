package com.taoists.user.service;

import java.util.List;

import com.taoists.common.orm.dao.BaseDao;
import com.taoists.user.entity.UserInterest;

public interface UserInterestService extends BaseDao<UserInterest>{

	//根据userID和storeId得到用户关注信息
	public UserInterest getByUserIdAndStoreId(long userId, long storeId);
	//根据userID得到用户关注信息
	public List<UserInterest> getByUserId(long userId);
	public int countStoreInterest(long storeId);
}
