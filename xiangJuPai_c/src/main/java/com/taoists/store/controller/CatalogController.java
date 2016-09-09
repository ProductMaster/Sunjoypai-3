package com.taoists.store.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taoists.CommonProjectController;
import com.taoists.common.Const;
import com.taoists.common.bean.Page;
import com.taoists.common.util.CalendarUtils;
import com.taoists.common.util.UploadHelper;
import com.taoists.store.entity.Catalog;
import com.taoists.store.entity.StoreCase;
import com.taoists.store.entity.StoreCaseImgs;
import com.taoists.store.entity.StoreInfo;
import com.taoists.user.entity.User;

@Controller
@RequestMapping("/store")
public class CatalogController extends CommonProjectController{

	/** 商品列表*/
	@RequestMapping("/listCatalog/{channel}")
	public String listCatalog(HttpSession session, HttpServletRequest request, Model model, @PathVariable("channel")int channel){
		
		User user = (User) session.getAttribute("currentUser");
		//User user = userService.get(1L);
		StoreInfo store = storeInfoService.getStoreInfoByUserId(user.getId());
		List<Catalog> catalogs = null;
		if(channel ==1){
			catalogs = catalogService.listCatalogByStoreId(store.getId(),0);
		}else{
			catalogs = catalogService.listCatalogByStoreId(store.getId(),1);
		}
		request.setAttribute("catalogs", catalogs);//getPath(catalogs));
		model.addAttribute("channel", channel);
		return "/store/listCatalog"+channel;
	}
	
	private List<Catalog> getPath(List<Catalog> list){
		
		List<Catalog> newList = new ArrayList<Catalog>();
		
		for(Catalog c : list){
			c.setPath(UploadHelper.mmNum(c.getId(),5000)+"/");
			newList.add(c);
		}
		
		return newList;
	}
	
	/** 新增商品*/
	@RequestMapping("/newCatalog")
	public String newCatalog(HttpSession session, HttpServletRequest request, Model model){
		
		User user = (User) session.getAttribute("currentUser");
		//User user = userService.get(1L);
		return "/store/newCatalog";
	}
	
	@RequestMapping(value="/addCatalog",method=RequestMethod.POST)
	public String addCatalog(HttpSession session, HttpServletRequest request,Catalog catalog) throws Exception{
		
		User user = (User) session.getAttribute("currentUser");
		//User user = userService.get(1L);
		StoreInfo store = storeInfoService.getStoreInfoByUserId(user.getId());
		catalog.setStoreId(store.getId());
		catalogService.save(catalog);
		// 上传图片
		Map<String, String> fileMap = UploadHelper.getFileNameByProduct(
				request, "uploadFile", catalog.getId());
		if (fileMap != null) {
			String filePath = null;
			String fileExt = null;
			filePath = fileMap.get(UploadHelper.FILE_NAME);
			fileExt = fileMap.get(UploadHelper.FILE_EXT);
			catalog.setPicUrl(filePath+fileExt);
		}
		catalogService.update(catalog);
		return "redirect:/store/listCatalog/0";
			
	}
	
	/**商品*/
	@RequestMapping("/deleteCatalog/{id}")
	public String deleteProduct(HttpSession session, HttpServletRequest request,@PathVariable long id){
		
		Catalog c = catalogService.get(id);
		c.setSaleStatus(Const.USER_DISABLE);  //冻结
		catalogService.update(c);
		int i=0;
		if(c.getSaleStatus() != Const.USER_ACTIVATION){
			 i=1;
		}
		
		return "redirect:/store/listCatalog"
				+ i;
	}
	
	/** 商品上架/下架 **/
	@RequestMapping("/updateCatalogStatus/{id}")
	public String updateCatalogStatus(@PathVariable long id, Model model){
		
		Catalog catalog = catalogService.get(id);
		int i=0;
		if(catalog.getSaleStatus() == Const.USER_ACTIVATION){
			catalog.setSaleStatus(Const.USER_FREEZE);
			i=1;
		}else if(catalog.getSaleStatus() == Const.USER_FREEZE){
			catalog.setSaleStatus(Const.USER_ACTIVATION);
		}
		catalogService.saveOrUpdate(catalog);
		
		return redirect("/store/listCatalog/"+i);
	}
	
	@Override
	protected String getModule() {
		// TODO Auto-generated method stub
		return null;
	}

}
