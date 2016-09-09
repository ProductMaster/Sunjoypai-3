package com.taoists.weixin.menu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taoists.CommonProjectController;
import com.taoists.Module;
import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.orm.PropertyFilter;
import com.taoists.weixin.menu.entity.MainMenu;
import com.taoists.weixin.menu.entity.SubMenu;


/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-11-20 上午11:38:22  
 *  
 */
@Controller
@RequestMapping("/weixinMenu")
public class SubMenuController extends CommonProjectController{
	/** ---------------------------- menu ------------------------------ **/
	/** 菜单：系统属性值列表 **/
	@RequestMapping("/listSubMenu/{mainMenuId}")
	public String list(@PathVariable Long mainMenuId, Page page, Model model) {
		logger.debug("list: mainMenu.id[{}], page[{}]", mainMenuId, page);

		MainMenu mainMenu = mainMenuService.get(mainMenuId);

		SubMenu subMenu = new SubMenu();
		subMenu.setMainMenu(mainMenu);
		subMenuService.findDatas("mainMenu.id", mainMenuId, page);
		model.addAttribute("mainMenu", mainMenu);
		return "weixin/menu/listSubMenu";
	}


	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(HttpServletRequest request, SubMenu subMenu, Page page) {
		logger.debug("list: subMenu[{}], page[{}]", subMenu, page);
		dataDictService.findPage(page, PropertyFilter
				.buildFromHttpRequest(request));
		return forward(ViewName.list);
	}

	/** ：新增二级菜单(Get) **/
	@RequestMapping("/newSubMenu/{mainMenuId}")
	public String newDataDict(@PathVariable Long mainMenuId, Model model) {
		logger.debug("editNew: mainMenuId[{}]", mainMenuId);
		model.addAttribute("mainMenu", mainMenuService.get(mainMenuId));
		return "weixin/menu/newSubMenu";
	}

	/** ：新增二级菜单(Post) **/
	@RequestMapping(value = "/addSubMenu", method = RequestMethod.POST)
	public String addSubMenu(SubMenu subMenu) {
		logger.debug("create: subMenu[{}]", subMenu);

		subMenuService.addSubMenu(subMenu);
		return redirect("/weixinMenu/listSubMenu/"+ subMenu.getMainMenu().getId());
	}

	/** ：编辑 二级菜单(Get) **/
	@RequestMapping("/editSubMenu/{id}")
	public String editSubMenu(@PathVariable Long id) {
		logger.debug("edit: subMenu.id[{}]", id);
		return "weixin/menu/editSubMenu";
	}

	/** ：编辑二级菜单(Post) **/
	@RequestMapping(value = "/updateSubMenu/{subMenu.id}", method = RequestMethod.POST)
	public String updateSubMenu(SubMenu subMenu) {
		logger.debug("dataDict[{}]", subMenu);
		subMenuService.clear();
		subMenuService.saveOrUpdate(subMenu);
		return redirect("/weixinMenu/listSubMenu/"+ subMenu.getMainMenu().getId());
	}

	/** ：删除二级菜单 **/
	@RequestMapping("/destroySubMenu/{mainMenuId}/{id}")
	public String destroySubMenu(@PathVariable Long mainMenuId,	@PathVariable Long id) {
		subMenuService.destroySubMenu(subMenuService.get(id));
		return redirect("/weixinMenu/listSubMenu/" + mainMenuId);
	}

	/** ---------------------------- helper ------------------------------ **/

	@ModelAttribute("subMenu")
	public SubMenu getDataDict(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		Long id = extractId(requestURI);
		logger.debug("getSubMenu: request.getRequestURI[{}], id[{}]",
				requestURI, id);

		if (id == null) {
			return new SubMenu();
		}
		return subMenuService.get(id);
	}
	
	
	@Override
	protected String getModule() {
		return Module.sys.getName();
	}
}
