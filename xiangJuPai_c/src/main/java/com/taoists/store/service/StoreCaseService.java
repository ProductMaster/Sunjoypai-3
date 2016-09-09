package com.taoists.store.service;

import java.util.List;

import com.taoists.common.orm.dao.BaseDao;
import com.taoists.store.entity.StoreCase;

public interface StoreCaseService extends BaseDao<StoreCase>{
	
	//根据店铺Id获得实例列表
    public List<StoreCase> listStoreCaseByStoreId(long storeId);
	//根据多个店铺Ids获得案例列表
    public List<StoreCase> listStoreCaseByStoreIds(Long[] storeIds);
}
