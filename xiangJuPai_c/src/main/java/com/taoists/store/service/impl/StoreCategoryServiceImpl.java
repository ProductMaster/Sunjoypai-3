package com.taoists.store.service.impl;

import org.springframework.stereotype.Service;

import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.store.entity.StoreCategory;
import com.taoists.store.service.StoreCategoryService;
@Service("storeCategoryService")
public class StoreCategoryServiceImpl extends HibernateDaoSupport<StoreCategory> implements StoreCategoryService{

}
