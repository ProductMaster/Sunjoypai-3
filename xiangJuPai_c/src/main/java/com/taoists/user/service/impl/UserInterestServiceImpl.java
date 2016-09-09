package com.taoists.user.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.user.entity.User;
import com.taoists.user.entity.UserInterest;
import com.taoists.user.service.UserInterestService;

@Service("userInterestService")
public class UserInterestServiceImpl  extends HibernateDaoSupport<UserInterest> implements UserInterestService{

	//根据userID和storeId得到用户关注信息
	public UserInterest getByUserIdAndStoreId(long userId, long storeId){
	     
        DetachedCriteria detachedCriteria = createDetachedCriteria();
    	detachedCriteria.add(Restrictions.eq("userId", userId));
    	detachedCriteria.add(Restrictions.eq("storeId", storeId));
        List<UserInterest> list = detachedCriteria.getExecutableCriteria(getSession()).list();
        logger.debug("list:"+list.size());
        if(list.size()>0) return list.get(0);
        return null;
	}
	
	//根据userID得到用户关注信息
	public List<UserInterest> getByUserId(long userId){
	     
        DetachedCriteria detachedCriteria = createDetachedCriteria();
    	detachedCriteria.add(Restrictions.eq("userId", userId));
        List<UserInterest> list = detachedCriteria.getExecutableCriteria(getSession()).list();
        logger.debug("list:"+list.size());
        if(list.size()>0) return list;
        return null;
	}
	
	// 店铺被关注数
	public int countStoreInterest(long storeId){
		
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT IFNULL(COUNT(id),0) AS interestCount FROM user_interest where store_id ="+storeId);	
						
		int count = 0;
		List<Map<String, Object>> list = findPageByNative(sb.toString());
		if(list.iterator().hasNext()){
			Map<String ,Object>map = list.iterator().next();
			count = Integer.valueOf(map.get("interestCount").toString());			
		}
		return count;
	}
}
