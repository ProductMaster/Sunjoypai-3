package com.taoists.user.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.icu.math.BigDecimal;
import com.taoists.common.Const;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.user.entity.User;
import com.taoists.user.service.UserService;

/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-12-6 上午10:49:55  
 *  
 */
@Service("userService")
public class UserServiceImpl extends HibernateDaoSupport<User> implements	 UserService {
	
	
	//根据userName得到用户
	public User getUserByUserName(String userName){
	     
        DetachedCriteria detachedCriteria = createDetachedCriteria();
    	detachedCriteria.add(Restrictions.eq("userName", userName));
        List<User> userList = detachedCriteria.getExecutableCriteria(getSession()).list();
        logger.debug("listSize:"+userList.size());
        if(userList.size()>0) return userList.get(0);
        return null;
	}

	public User getUserByWeixinId(String weixinId){
        
        User user = null;
        DetachedCriteria detachedCriteria = createDetachedCriteria();
        detachedCriteria.add(Restrictions.eq("wxOpenId", weixinId));
        detachedCriteria.add(Restrictions.ne("wxOpenId", Const.NULL_VALUE_STRING));
        detachedCriteria.add(Restrictions.isNotNull("wxOpenId"));
        detachedCriteria.addOrder(org.hibernate.criterion.Order.desc("id"));
        List<User> users = detachedCriteria.getExecutableCriteria(getSession()).list();
        if(users.size()>0) return users.get(0);
        return user;
    }
	

}
