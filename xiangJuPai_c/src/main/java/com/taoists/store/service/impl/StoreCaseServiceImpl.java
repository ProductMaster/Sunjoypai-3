package com.taoists.store.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.taoists.common.Const;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.store.entity.Catalog;
import com.taoists.store.entity.StoreCase;
import com.taoists.store.service.StoreCaseService;

@Service("storeCaseService")
public class StoreCaseServiceImpl  extends HibernateDaoSupport<StoreCase> implements StoreCaseService{

	//根据店铺Id获得实例列表
    public List<StoreCase> listStoreCaseByStoreId(long storeId){
        
        DetachedCriteria detachedCriteria = createDetachedCriteria();
        detachedCriteria.add(Restrictions.eq("storeInfo.id", storeId));
        detachedCriteria.add(Restrictions.eq("isShow", Const.YES));
        return detachedCriteria.getExecutableCriteria(getSession()).list();        
     }
    
	//根据多个店铺Ids获得案例列表
    public List<StoreCase> listStoreCaseByStoreIds(Long[] storeIds){
        
        DetachedCriteria detachedCriteria = createDetachedCriteria();
        detachedCriteria.add(Restrictions.in("storeInfo.id", storeIds));
        detachedCriteria.addOrder(Order.asc("createDate"));
        return detachedCriteria.getExecutableCriteria(getSession()).list();        
     }
	
}
