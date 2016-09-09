package com.taoists.store.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.icu.math.BigDecimal;
import com.taoists.CommonProjectController;
import com.taoists.common.Const;
import com.taoists.common.bean.CommonJsonBean;
import com.taoists.common.gson.FilterExclusionStrategy;
import com.taoists.common.util.AmapUtils;
import com.taoists.common.util.DistanceUtil;
import com.taoists.common.util.UploadHelper;
import com.taoists.store.entity.Catalog;
import com.taoists.store.entity.StoreCase;
import com.taoists.store.entity.StoreCaseImgs;
import com.taoists.store.entity.StoreCategory;
import com.taoists.store.entity.StoreInfo;
import com.taoists.user.entity.User;
import com.taoists.user.entity.UserInterest;
import com.taoists.weixin.event.controller.TokenController;

@Controller
@RequestMapping("/store")
public class StoreController extends CommonProjectController{

	/** 主页 */
    @RequestMapping("/index")
    public String index(HttpSession session, HttpServletRequest request)throws IOException{
        
    	int sessionStatus = initialWeixinUser(session, request);
        if (sessionStatus == 1) return "redirect:/oAuth2/"+TokenController.RETURN_URL_DEFAULT;
    	
    	List<StoreCategory> cates = storeCategoryService.findAll();
    	
    	request.setAttribute("cates", cates);
        return "/store/index";
    }
    
    @RequestMapping(value = "/listByJingWeidu", method = RequestMethod.POST)  
    @ResponseBody  
    public void listByJingWeidu(HttpServletRequest request, HttpServletResponse response) throws Exception {  
        
      Map<String, String> map = new HashMap<String, String>(1);
      String cateId = request.getParameter("cateId");
      String jingdu = request.getParameter("jingdu");
      String weidu = request.getParameter("weidu");
      List<StoreInfo> list = storeInfoService.listStoreInfoByCateId(Long.parseLong(cateId));
      List<StoreInfo> infos = new ArrayList<StoreInfo>();
      
      for(StoreInfo info : list){
			double distance;
			distance = DistanceUtil.GetDistance(Double.parseDouble(jingdu), Double.parseDouble(weidu), info.getX(), info.getY());
			distance=distance/1000;
			BigDecimal b = new BigDecimal(distance);  
			distance = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
			info.setDistance(distance);
			info.setPath(UploadHelper.mmNum(info.getId(),5000)+"/");
			infos.add(info);			
		}
      ComparatorChain chain = new ComparatorChain();
      chain.addComparator(new BeanComparator("distance"),false);//true,fase正序反序
      Collections.sort(infos,chain);
      
      CommonJsonBean jbean = new CommonJsonBean();
      jbean.setDatas(infos);
      
      FilterExclusionStrategy ts = new FilterExclusionStrategy();
      //ts.setClasses(new Class<?>[]{User.class});
      Gson gson = new GsonBuilder().setExclusionStrategies(ts).create();
      String json = gson.toJson(jbean).replace("\\", Const.NULL_VALUE_STRING);
      response.setCharacterEncoding("UTF-8");
      PrintWriter writer = response.getWriter();
      writer.write(json.toString());
      writer.flush(); 
      writer.close(); 
      
    }
    
    
	/** 店铺详情*/
	@RequestMapping("/storeDetail/{id}")
	public String storeDetail(HttpSession session, HttpServletRequest request, Model model, @PathVariable long id)throws IOException{
		
		int sessionStatus = initialWeixinUser(session, request);
        if (sessionStatus == 1) return "redirect:/oAuth2/"+TokenController.RETURN_URL_USECENTER;
		User user = (User) session.getAttribute("currentUser");
		//User user = userService.get(1L);
		UserInterest interest = userInterestService.getByUserIdAndStoreId(user.getId(), id);
		
		int isInterest = 0;
		if(interest != null){
			isInterest = 1;
		}
		
		StoreInfo info = storeInfoService.get(id);
		info.setPageCount(info.getPageCount()+1);
		storeInfoService.update(info);
		info.setPath(UploadHelper.mmNum(info.getId(),5000)+"/");
		List<StoreCase> cases = storeCaseService.listStoreCaseByStoreId(info.getId());
		List<Catalog> catalogs = catalogService.listCatalogByStoreId(info.getId(),1);
		model.addAttribute("isInterest", isInterest);
		
		request.setAttribute("interestNum", userInterestService.countStoreInterest(id));
		request.setAttribute("catalogs", catalogs);//getPathOfCatalog(catalogs));
		request.setAttribute("cases", cases);//getPath(cases));
		request.setAttribute("info", info);
		return "/store/storeDetail";
	}
	private List<Catalog> getPathOfCatalog(List<Catalog> list){
		
		List<Catalog> newList = new ArrayList<Catalog>();
		
		for(Catalog c : list){
			c.setPath(UploadHelper.mmNum(c.getId(),5000)+"/");
			newList.add(c);
		}
		
		return newList;
	}

	
	/** 店铺设置*/
	@RequestMapping("/storeInfo")
	public String storeInfo(HttpSession session, HttpServletRequest request, Model model)throws IOException{
		
		int sessionStatus = initialWeixinUser(session, request);
        if (sessionStatus == 1) return "redirect:/oAuth2/"+TokenController.RETURN_URL_USERINFO;
		User user = (User) session.getAttribute("currentUser");
		//User user = userService.get(1L);
		
		StoreInfo info = storeInfoService.getStoreInfoByUserId(user.getId());
		request.setAttribute("info", info);
		return "/store/storeInfo";
	}
	
	/** 修改店铺头像 */
	@RequestMapping("/settingStorePic")
	public String settingStorePic(HttpSession session, HttpServletRequest request)throws IOException{
		
		int sessionStatus = initialWeixinUser(session, request);
        if (sessionStatus == 1) return "redirect:/oAuth2/"+TokenController.RETURN_URL_USERINFO;

        User user = (User) session.getAttribute("currentUser");
        //User user = userService.get(1L);
        StoreInfo info = storeInfoService.getStoreInfoByUserId(user.getId());
        request.setAttribute("info", info);
        request.setAttribute("user", user);
    	//request.setAttribute("photoPath", UploadHelper.mmNum(info.getId(),5000)+"/");
		return "/store/settingStorePic";
	}
	
	@RequestMapping(value="/settingStorePic",method=RequestMethod.POST)
	public String addJianFeiLogPost(HttpSession session, HttpServletRequest request) throws Exception{
		
		User user = (User)session.getAttribute("currentUser");
        //User user = userService.get(1L);
        StoreInfo info = storeInfoService.getStoreInfoByUserId(user.getId());
        // 上传头像
        Map<String, String> fileMap = new HashMap<String, String>();
        fileMap = UploadHelper.getFileNameByStoreId(
 				request, "uploadFile", info.getId());
        if(fileMap != null){
	        
 			String fileName = null;
 			String fileExt = null;
 			fileName = fileMap.get("fileName").toString();
 			fileExt = fileMap.get("fileExt").toString();
 			info.setImgUrl(fileName+fileExt);
		 		
        }
        storeInfoService.update(info);
        return redirect("/store/storeInfo");
		
	}
	
	/** 修改店铺信息 */
	@RequestMapping("/settingStoreInfo/{type}")
	public String settingStoreInfo(HttpSession session, HttpServletRequest request, @PathVariable long type)throws IOException{
		
		int sessionStatus = initialWeixinUser(session, request);
        if (sessionStatus == 1) return "redirect:/oAuth2/"+TokenController.RETURN_URL_USERINFO;

        User user = (User) session.getAttribute("currentUser");
        //User user = userService.get(1L);
        StoreInfo info = storeInfoService.getStoreInfoByUserId(user.getId());
        request.setAttribute("sInfo", info);
        request.setAttribute("type", type);
		return "/store/settingStoreInfo";
	}
	@RequestMapping(value="/updateStoreInfo/{id}",method=RequestMethod.POST)
	public String updateStoreInfo(HttpServletRequest request,@ModelAttribute("storeInfo")StoreInfo storeInfo){
		
		if(storeInfo.getAddr().trim().length() != 0){
			double[] gps = new double[2];
			gps = AmapUtils.addressToGPS(storeInfo.getAddr());
			storeInfo.setX(gps[0]);
			storeInfo.setY(gps[1]);			
		}		
		storeInfoService.clear();
		storeInfoService.update(storeInfo);
		return redirect("/store/storeInfo");
	}
	
	
	/** 案例管理列表*/
	@RequestMapping("/listCase")
	public String listCase(HttpSession session, HttpServletRequest request, Model model){
		
		User user = (User) session.getAttribute("currentUser");
		//User user = userService.get(1L);
		StoreInfo store = storeInfoService.getStoreInfoByUserId(user.getId());
		List<StoreCase> cases = storeCaseService.listStoreCaseByStoreId(store.getId());
		request.setAttribute("cases", cases);//getPath(cases));
		return "/store/listCase";
	}
	
	private List<StoreCase> getPath(List<StoreCase> list){
		
		List<StoreCase> newList = new ArrayList<StoreCase>();
		
		for(StoreCase sc : list){
			sc.setPath(UploadHelper.mmNum(sc.getId(),5000)+"/");
			newList.add(sc);
		}
		
		return newList;
	}
	
	/** 发布新案例
	 * @throws IOException */
	@RequestMapping("/addCase")
	public String addCase(HttpSession session, HttpServletRequest request, Model model) throws IOException{
		
		int sessionStatus = initialWeixinUser(session, request);
        if (sessionStatus == 1) return "redirect:/oAuth2/"+TokenController.RETURN_URL_ADD_CASE;
		User user = (User) session.getAttribute("currentUser");
		//User user = userService.get(1L);
		return "/store/addCase";
	}
	
	@RequestMapping(value="/addStoreCase",method=RequestMethod.POST)
	public String addStoreCase(HttpSession session, HttpServletRequest request,StoreCase storeCase) throws Exception{
		
		User user = (User) session.getAttribute("currentUser");
		StoreInfo store = storeInfoService.getStoreInfoByUserId(user.getId());
		
		StoreCase sc = null;
		String caseId = request.getParameter("caseId");
		if(caseId != null && caseId.length()>0){
			sc = storeCaseService.get(Long.valueOf(caseId));
			if(sc == null){
				sc = new StoreCase();
				sc.setStoreInfo(store);
				sc.setIsShow(Const.NO);
				storeCaseService.save(sc);				
			}else{
				sc.setIsShow(Const.YES);
				storeCaseService.update(sc);
			}
		}else{
			sc = new StoreCase();
			sc.setStoreInfo(store);
			sc.setIsShow(Const.NO);
			storeCaseService.save(sc);
		}
		
		// 上传图片
/*		List<Map<String, String>> fileMaps = UploadHelper.getCaseFileNamesById(
				request, "uploadFile", storeCase.getId());
		if (fileMaps != null) {
			for(Map<String,String> fileMap : fileMaps){
		 		StoreCaseImgs img = new StoreCaseImgs();
	 			String fileName = null;
	 			String fileExt = null;
	 			fileName = fileMap.get("fileName").toString();
	 			fileExt = fileMap.get("fileExt").toString();
	 			img.setImgUrl(fileName+fileExt);
	 			img.setStoreCase(storeCase);
	 			img.setStoreId(store.getId());
		 		storeCaseImgsService.save(img);
			}
		}*/
		return "redirect:/store/listCase";
			
	}
	
	/**删除案例*/
	@RequestMapping("/deleteCase/{id}")
	public String deleteCase(HttpSession session, HttpServletRequest request,@PathVariable long id){
		
		StoreCase sc = storeCaseService.get(id);
		List<StoreCaseImgs> imgs = sc.getImgs();
		if(imgs != null){
			for(StoreCaseImgs i : imgs){
				storeCaseImgsService.delete(i);
			}
		}
		storeCaseService.delete(sc);
		return "redirect:/store/listCase";
	}
	
	@RequestMapping(value="/addStoreCaseImage",method=RequestMethod.POST)
	public String addImages(HttpSession session, HttpServletRequest request, org.springframework.ui.Model m) throws Exception{
				
		User user = (User)session.getAttribute("currentUser");
		StoreInfo store = storeInfoService.getStoreInfoByUserId(user.getId());
        //User user = userService.get(1L);
		
		StoreCase sc = null;
		String caseId = request.getParameter("caseId");
		if(caseId != null && caseId.length()>0){
			sc = storeCaseService.get(Long.valueOf(caseId));
			if(sc == null){
				sc = new StoreCase();
				sc.setStoreInfo(store);
				sc.setIsShow(Const.NO);
				storeCaseService.save(sc);				
			}
		}else{
			sc = new StoreCase();
			sc.setStoreInfo(store);
			sc.setIsShow(Const.NO);
			storeCaseService.save(sc);
		}
		caseId = Long.toString(sc.getId());
		
		long imgId = Long.valueOf(request.getParameter("imageId"));		
		// 上传图片		
	    List<Map<String, String>> fileMaps = UploadHelper.getCaseFileNamesById(request, "uploadFile", sc.getId());
	    if(fileMaps != null){
	    	if(imgId==Const.NULL_VALUE_LONG){ //imgId=-99 为新上传图片 新增modelUserImgs
	    		for(Map<String,String> fileMap : fileMaps){
	    			StoreCaseImgs storeCaseImg = new StoreCaseImgs();
	    			if (fileMap != null) {
	    				String fileName = fileMap.get("fileName").toString();
	    	 			String fileExt = fileMap.get("fileExt").toString();
	    	 			storeCaseImg.setImgUrl(fileName+fileExt);
				 	}
	    			storeCaseImg.setStoreCase(sc);
	    			storeCaseImg.setStoreId(sc.getStoreInfo().getId());
	    			storeCaseImgsService.save(storeCaseImg);
			    }
	    	}else{
	    		for(Map<String,String> fileMap : fileMaps){
	    			StoreCaseImgs storeCaseImg = storeCaseImgsService.get(imgId);
	    			if (fileMap != null) {
	    				String fileName = fileMap.get("fileName").toString();
	    	 			String fileExt = fileMap.get("fileExt").toString();
	    	 			storeCaseImg.setImgUrl(fileName+fileExt);
	    			}
	    			storeCaseImgsService.update(storeCaseImg);
	    		}
	        }
	    }
		
	    /** 返回 **/
        long userId = user.getId();
        List<StoreCaseImgs> imgsList = storeCaseImgsService.findByCaseId(Long.valueOf(caseId));
        if(null!=imgsList && imgsList.size()>9){
        	imgsList.subList(0, 9);
        }
        if(imgsList != null){
	        for(StoreCaseImgs img :imgsList){
	        	request.setAttribute("imgs", img);
	        }
        }
        
        m.addAttribute("imgSize", imgsList.size());
        request.setAttribute("storeCase", sc);
		request.setAttribute("imgPath",UploadHelper.mmNum(sc.getId(),5000)+"/"+sc.getId()+"/");
		request.setAttribute("storeCaseImgs",imgsList);
        return "/store/addCase";
	}	
	
	@Override
	protected String getModule() {
		// TODO Auto-generated method stub
		return null;
	}
	/** ---------------------------- Autowired ------------------------------ **/
	@ModelAttribute("storeInfo")
	public StoreInfo getStoreInfo(HttpServletRequest request) {

		String requestURI = request.getRequestURI();
		Long id = extractId(requestURI);
		logger.debug("getStoreInfo: request.getRequestURI[{}], id[{}]",
				requestURI, id);

		if (id == null) {
			return new StoreInfo();
		}
		return storeInfoService.get(id);
	}

}
