package com.taoists.weixin.menu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taoists.CommonProjectController;
import com.taoists.Module;
import com.taoists.common.bean.Page;
import com.taoists.common.orm.PropertyFilter;
import com.taoists.weixin.context.AccessTokenThread;
import com.taoists.weixin.menu.entity.MainMenu;
import com.taoists.weixin.menu.entity.SubMenu;
import com.taoists.weixin.menu.model.Button;
import com.taoists.weixin.menu.model.CommonButton;
import com.taoists.weixin.menu.model.ComplexButton;
import com.taoists.weixin.menu.model.Menu;
import com.taoists.weixin.menu.model.ViewButton;
import com.taoists.weixin.menu.service.MainMenuService;
import com.taoists.weixin.menu.service.SubMenuService;
import com.taoists.weixin.model.AccessToken;
import com.taoists.weixin.util.WeixinUtils;

/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-11-20 上午11:18:40  
 *  
 */
@Controller
@RequestMapping("/weixinMenu")
public class MainMenuController extends CommonProjectController{
	
	@RequestMapping("/createMenu")
	public String createMenu(HttpServletRequest request, Page page, RedirectAttributes redirectAttributes){
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		mainMenuService.findPage(page, filters);
		extractParams(request);

		// 生成菜单对象
		Menu menu = getMenu(request);
		if(menu == null){
			redirectAttributes.addFlashAttribute("msg", "未发现菜单数据，生成菜单终止！");
			return redirect("/weixinMenu/listMenu");
		}
		
		// 调用接口创建菜单  
		int result = WeixinUtils.createMenu(menu);
		// 判断菜单创建结果  
		if (0 == result){
		   redirectAttributes.addFlashAttribute("msg", "菜单创建成功！");
		}
		else
			redirectAttributes.addFlashAttribute("msg", "菜单创建失败，错误码：" + result);
		return redirect("/weixinMenu/listMenu");
	}
	
	/** ---------------------------- menu ------------------------------ **/
	/** 菜单：菜单列表 **/
	@RequestMapping("/listMenu")
	public String listMenu(HttpServletRequest request, Page page) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		mainMenuService.findPage(page, filters);
		extractParams(request);
		return "weixin/menu/listMenu";
	}

	/** ：新增菜单(Get) **/
	@RequestMapping("/newMenu")
	public String newDictCategory() {
		return "weixin/menu/newMenu";
	}

	/** ：新增菜单(Post) **/
	@RequestMapping(value = "/addMenu", method = RequestMethod.POST)
	public String addMenu(MainMenu mainMenu) {
		logger.debug("create: Menu[{}]", mainMenu);

		mainMenuService.save(mainMenu);
		return redirect("/weixinMenu/listMenu");
	}

	/** ：编辑菜单(Get) **/
	@RequestMapping("/editMenu/{id}")
	public String editMenu(@PathVariable long id) {
		logger.debug("edit: id[{}]", id);
		return "weixin/menu/editMenu";
	}

	/** ：编辑菜单(Post) **/
	@RequestMapping(value = "/updateMenu/{mainMenu.id}", method = RequestMethod.POST)
	public String updateMenu(MainMenu mainMenu) {
		logger.debug("update: mainMenu[{}]", mainMenu);
		mainMenuService.clear();
		mainMenuService.saveOrUpdate(mainMenu);
		return redirect("/weixinMenu/listMenu");
	}

	/** ：删除菜单 **/
	@RequestMapping("/destroyMenu/{id}")
	public String destroyDictCategory(@PathVariable long id) {
		logger.debug("remove: id[{}]", id);
		List<SubMenu> subMenus = subMenuService.findSubMenuByMainMenuId(id);
		for(SubMenu subMenu:subMenus){
			subMenuService.delete(subMenu);
		}
		mainMenuService.delete(id);
		return redirect("/weixinMenu/listMenu");
	}

	/** ---------------------------- helper ------------------------------ **/

	@ModelAttribute("mainMenu")
	public MainMenu getMainMenu(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		Long id = extractId(requestURI);
		logger.debug("getMainMenu: request.getRequestURI[{}], id[{}]",
				requestURI, id);

		if (id == null) {
			return new MainMenu();
		}
		return mainMenuService.get(id);
	}
	
	/** 
     * 组装菜单数据 
     *  
     */
	private Menu getMenu(HttpServletRequest request) {
		
		// 从数据库读取一级菜单数据
		List<MainMenu> mainMenuList= mainMenuService.findAllOrderBy("orderBy");
		if(mainMenuList.size() == 0) return null;  		// 未发现主菜单数据
		
	    Menu menu = new Menu();  
	    Button[] menuBtn = new Button[mainMenuList.size()];
			    
	    int index=0;		
		// 对一级菜单数据进行遍历
		for(MainMenu wx:mainMenuList){
			
			if(wx.getType().equals("click")){
				CommonButton commonButton=new CommonButton();
				commonButton.setName(wx.getTitle());
				commonButton.setKey(wx.getValue());
				commonButton.setType("click");
				menuBtn[index] = commonButton;
			}
			
			if(wx.getType().equals("view")){
				ViewButton viewBtn=new ViewButton();
				viewBtn.setName(wx.getTitle());
				viewBtn.setUrl(wx.getValue());
				viewBtn.setType("view");
				menuBtn[index] = viewBtn;				
			}
			
			// 如果一级菜单包含二级菜单，则从数据库读取二级菜单数据
			if(wx.getType().equals("group") && wx.getSize()>0){
				logger.debug("wx.getSize():"+wx.getSize());
				ComplexButton mainBtn=new ComplexButton();		// 创建一级菜单的button对象组
				mainBtn.setName(wx.getTitle());  
				
				Button[] btns=new Button[wx.getSize()];
				List<SubMenu> subMenus=subMenuService.findSubMenuByMainMenuId(wx.getId());
				
				//对二级菜单数据进行遍历
				for(int i=0;i<wx.getSize();i++){
					SubMenu subMenu=subMenus.get(i);
					// click类型菜单
					if("click".equals(subMenu.getType())){
						CommonButton commonButton=new CommonButton();
						commonButton.setName(subMenu.getTitle());
						commonButton.setKey(subMenu.getValue());
						commonButton.setType("click");
						btns[i]=commonButton;
					// view类型菜单
					}else if("view".equals(subMenu.getType())){
						ViewButton viewBtn=new ViewButton();
						viewBtn.setName(subMenu.getTitle());
						viewBtn.setUrl(subMenu.getValue());
						viewBtn.setType("view");
						btns[i]=viewBtn;
					}
				}
				mainBtn.setSub_button(btns);
				menuBtn[index] = mainBtn;
			}
			
			// 如果一级菜单为组菜单，但子菜单为空
			if(wx.getType().equals("group") && wx.getSize()==0){
				Button nullButton = new Button();
				nullButton.setName(wx.getTitle()); 
				menuBtn[index] = nullButton;
			}
		    index++;
		}

	    menu.setButton(menuBtn);  
	    return menu;  
	}  
	
	@Override
	protected String getModule() {
		return Module.sys.getName();
	}	
}
