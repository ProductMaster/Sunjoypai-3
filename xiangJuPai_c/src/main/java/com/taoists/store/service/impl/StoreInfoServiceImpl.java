package com.taoists.store.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.taoists.common.Const;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.store.entity.StoreInfo;
import com.taoists.store.service.StoreInfoService;

@Service("storeInfoService")
public class StoreInfoServiceImpl extends HibernateDaoSupport<StoreInfo> implements StoreInfoService{
	
	//根据分类id或的商铺列表
    public List<StoreInfo> listStoreInfoByCateId(long cateId){
        
        DetachedCriteria detachedCriteria = createDetachedCriteria();

    	detachedCriteria.add(Restrictions.eq("cateId", cateId));
    	detachedCriteria.add(Restrictions.eq("status", Const.APPLICATION_PASS));
        return detachedCriteria.getExecutableCriteria(getSession()).list();        
     }
    
	//根据用户id或的商铺列表
    public StoreInfo getStoreInfoByUserId(long userId){
        
        DetachedCriteria detachedCriteria = createDetachedCriteria();

    	detachedCriteria.add(Restrictions.eq("userId", userId));
    	//detachedCriteria.add(Restrictions.eq("status", Const.USER_ACTIVATION));
        List<StoreInfo> list = detachedCriteria.getExecutableCriteria(getSession()).list();  
        if(list.size()>0) return list.get(0);
        return null;
     }
}
