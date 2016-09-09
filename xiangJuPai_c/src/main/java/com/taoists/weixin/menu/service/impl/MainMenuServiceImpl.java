package com.taoists.weixin.menu.service.impl;

import org.springframework.stereotype.Service;

import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.weixin.menu.entity.MainMenu;
import com.taoists.weixin.menu.service.MainMenuService;


/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-11-20 上午11:15:50  
 *  
 */
@Service("mainMenuService")
public class MainMenuServiceImpl extends HibernateDaoSupport<MainMenu> implements MainMenuService{

}
