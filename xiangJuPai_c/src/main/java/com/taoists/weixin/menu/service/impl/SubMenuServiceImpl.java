package com.taoists.weixin.menu.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.weixin.menu.entity.MainMenu;
import com.taoists.weixin.menu.entity.SubMenu;
import com.taoists.weixin.menu.service.MainMenuService;
import com.taoists.weixin.menu.service.SubMenuService;


/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-11-20 上午11:16:53  
 *  
 */
@Service("subMenuService")
public class SubMenuServiceImpl extends HibernateDaoSupport<SubMenu> implements SubMenuService{
	
	@Override
	public List<SubMenu> findSubMenuByMainMenuId(long mainMenuId) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.createAlias("mainMenu", "dc");
		detachedCriteria.add(Restrictions.eq("dc.id", mainMenuId));
		detachedCriteria.addOrder(Order.asc("orderBy"));
		return findDatas(detachedCriteria);
	}
	
	@Transactional
	public void addSubMenu(SubMenu subMenu){
	    save(subMenu);
	    MainMenu mainMenu = mainMenuService.get(subMenu.getMainMenu().getId());
	    mainMenu.setSize(mainMenu.getSize()+1);
	    mainMenuService.update(mainMenu);
	}

	@Transactional
	public void destroySubMenu(SubMenu subMenu){
        MainMenu mainMenu =  mainMenuService.get(subMenu.getMainMenu().getId());
        mainMenu.setSize(mainMenu.getSize()-1);
        mainMenuService.update(mainMenu);
        delete(subMenu);
    }
	
	@Autowired
    private MainMenuService mainMenuService;
}
