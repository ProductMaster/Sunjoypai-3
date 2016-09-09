package com.taoists.store.service;

import java.util.List;

import com.taoists.common.orm.dao.BaseDao;
import com.taoists.store.entity.StoreInfo;

public interface StoreInfoService extends BaseDao<StoreInfo>{
	
	//根据分类id或的商铺列表
    public List<StoreInfo> listStoreInfoByCateId(long cateId);
	//根据用户id或的商铺列表
    public StoreInfo getStoreInfoByUserId(long userId);
}
