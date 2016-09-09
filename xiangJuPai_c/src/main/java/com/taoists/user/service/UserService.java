package com.taoists.user.service;

import java.io.IOException;
import java.util.List;

import com.taoists.common.orm.dao.BaseDao;
import com.taoists.user.entity.User;

/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-12-6 上午10:44:34  
 *  
 */
public interface UserService extends BaseDao<User>{

	//根据userName得到用户
	public User getUserByUserName(String userName);
    public User getUserByWeixinId(String weixinId);

}
    
