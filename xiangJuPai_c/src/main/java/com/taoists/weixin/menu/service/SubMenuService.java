package com.taoists.weixin.menu.service;

import java.util.List;

import com.taoists.common.orm.dao.BaseDao;
import com.taoists.weixin.menu.entity.SubMenu;


/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-11-20 上午11:10:13  
 *  
 */
public interface SubMenuService extends BaseDao<SubMenu>{
	public List<SubMenu> findSubMenuByMainMenuId(long mainMenuId);
	public void addSubMenu(SubMenu subMenu);
	public void destroySubMenu(SubMenu subMenu);
}
