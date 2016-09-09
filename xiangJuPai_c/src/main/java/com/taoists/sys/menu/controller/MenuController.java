package com.taoists.sys.menu.controller;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.taoists.common.Const;
import com.taoists.common.ViewName;
import com.taoists.common.controller.CommonFrameworkController;
import com.taoists.sys.common.Module;
import com.taoists.sys.common.controller.path.ResultPath;
import com.taoists.sys.menu.entity.Menu;
import com.taoists.sys.menu.service.MenuService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends CommonFrameworkController {

	@RequestMapping
	public String list(HttpSession session) {
		List<Menu> menus = menuService.getRootMenus();
		menuService.loopQueryMenusByParent(menus);
		session.setAttribute("menus", menuService.loopQueryMenusByParent(menus));
		return forward(ViewName.list);
	}

	@RequestMapping("/left")
	public String left(HttpSession session, Model model) {
		logger.debug("============");
		List<Menu> menus = menuService.getRootMenus();
		menuService.loopQueryMenusByParent(menus);
		session.setAttribute("menus", menuService.loopQueryMenusByParent(menus));
		return redirect("/main/left.jsp");
	}
	
	//@RequestMapping("/left")
	public String leftByEmployee(HttpSession session, Model model) {
		logger.debug("============: leftByEmployee()");
		//Employee employee = getUser(model);
		//session.setAttribute("menus", menuService.findByRoles(employee.getRoles()));
		session.setAttribute("menus", menuService.findAll());
		return redirect("/main/left.jsp");
	}
	

	@RequestMapping("/edit-new")
	public String editNew() {
		return forward(ViewName.insert);
	}

	@RequestMapping("/new/{parentId}")
	public String editNew(Menu menu, @PathVariable String parentId, Model model) {
		logger.debug("menu[{}], parentId[{}]", menu, parentId);
		if (StringUtils.isNotBlank(parentId)) {
			Menu parent = new Menu();
			parent.setId(Long.parseLong(parentId));
			menu.setParent(parent);
			model.addAttribute("menu", menu);
		}
		return forward(ViewName.insert);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(HttpSession session, Menu menu, String parentId) {
		logger.debug("create[{}]", menu);

		if (StringUtils.isNotBlank(parentId)) {
			Menu parent = menuService.get(Long.parseLong(parentId));
			menu.setWidth(parent.getWidth() + 1);
			menu.setParent(parent);
		}
		menuService.save(menu);
		refresh(session);
		return redirect(ResultPath.menu);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(HttpSession session, Menu menu, String parentId) {
		logger.debug("create[{}]", menu);

		if (StringUtils.isNotBlank(parentId)) {
			Menu parent = menuService.get(Long.parseLong(parentId));
			menu.setWidth(parent.getWidth() + 1);
			menu.setParent(parent);
		}
		menuService.merge(menu);
		refresh(session);
		return redirect(ResultPath.menu);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("{id}/expand")
	public String expand(@PathVariable long id, HttpSession session) {
		List<Menu> menus = (List<Menu>) session.getAttribute("menus");
		menuService.handle(id, menus);
		session.setAttribute("menus", menus);
		return forward(ViewName.list);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("{id}/menu")
	public String menu(@PathVariable long id, HttpSession session) {
		List<Menu> menus = (List<Menu>) session.getAttribute("menus");
		menuService.handle(id, menus);
		session.setAttribute("menus", menus);
		return redirect("/main/left.jsp");
	}

	@RequestMapping("{id}")
	public String show(@PathVariable long id, Model model) {
		logger.debug("show[{}]", id);

		model.addAttribute("menu", menuService.get(id));
		return forward(ViewName.edit);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/destroy/{id}")
	public String remove(@PathVariable long id, HttpSession session) {
		logger.debug("remove[{}]", id);
		menuService.delete(id);
		List<Menu> menus = (List<Menu>) session.getAttribute("menus");
		menuService.updateMenus(menus, new Menu(), id, Const.delete);
		
		refresh(session);
		return redirect(ResultPath.menu);
	}

	@RequestMapping(value = "/nodes", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String nodes() {
		List<Menu> list = new ArrayList<Menu>();
		List<Menu> rootMenus = menuService.getRootMenus();
		menuService.loopQueryMenusByParent(rootMenus);
		forEachMenus(list, rootMenus);
		return JSONArray.fromObject(list).toString();
	}

	@Override
	protected String getModule() {
		return Module.sys.getName();
	}

	@SuppressWarnings("unchecked")
	private void forEachMenus(List<Menu> list, List<Menu> menus) {
		if (CollectionUtils.isEmpty(menus)) {
			return;
		}

		for (Menu menu : menus) {
			if (menu.getLeaf()) {
				continue;
			}

			Menu m = new Menu();
			m.setId(menu.getId());
			m.setLabel(indent(menu.getWidth()) + menu.getLabel());
			m.setWidth(menu.getWidth());
			list.add(m);
			if (CollectionUtils.isNotEmpty(menu.getChild())) {
				forEachMenus(list, (List<Menu>) menu.getChild());
			} else {
				forEachMenus(list, menuService.findMenusByParent(m.getId(), false));
			}
		}
	}

	private String indent(Integer width) {
		if (width == null) {
			width = 0;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < width; i++) {
			sb.append("|--");
		}
		return sb.toString();
	}


	private void refresh(HttpSession session) {
		List<Menu> menus = menuService.findAll();

		Multimap<Long, Menu> menuGroup = HashMultimap.create();
		for (Menu menu : menus) {
			if (menu.getParent() == null) {
				menuGroup.put(-1L, menu);
			} else {
				menuGroup.put(menu.getParent().getId(), menu);
			}
		}
		session.getServletContext().setAttribute("menuGroup", menuGroup);
	}

	/** ----------------------------  Autowired ------------------------------ **/
	@Autowired
	protected MenuService menuService;
}
