package com.taoists.user.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taoists.CommonProjectController;
import com.taoists.common.util.ImageUtils;
import com.taoists.common.util.StringUtils;
import com.taoists.common.util.UploadHelper;
import com.taoists.common.zxing.LogoConfig;
import com.taoists.common.zxing.ZXingCodeUtils;
import com.taoists.common.zxing.ZXingConfig;
import com.taoists.store.entity.StoreCase;
import com.taoists.store.entity.StoreInfo;
import com.taoists.user.entity.User;
import com.taoists.user.entity.UserInterest;
import com.taoists.weixin.event.controller.TokenController;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2015年7月19日 上午10:29:34 
 * 类说明 
 */

@Controller
@RequestMapping("/user")
public class UserController extends CommonProjectController {

  /*  @Value("#{messageSource.getMessage('weixin_serverPath',null,null)}")
    protected String weixinServerPath;*/
	public static final int TEMP_QRCODE_SHOW_GET = 1;
	public static final int TEMP_QRCODE_SHOW_TRIALCOMMIT = 2;
	
	//我要开店
	@RequestMapping("/addStore")
	public String addStore(HttpSession session, HttpServletRequest request)throws IOException{
		
		int sessionStatus = initialWeixinUser(session, request);
        if (sessionStatus == 1) return "redirect:/oAuth2/"+TokenController.RETURN_URL_USERINFO;

        User user = (User) session.getAttribute("currentUser");
        //User user = userService.get(1L);
        if(user.getUserType()==1){
        	
        	return "redirect:/user/userCenter";
        }
        
        
        StoreInfo store = new StoreInfo();
        
        store.setUserId(user.getId());
        store.setCateId(1L);
        store.setPhotoPath(UploadHelper.mmNum(store.getId(),5000)+"/");
        storeInfoService.save(store);
        
        user.setUserType(1);
        userService.update(user);

        return "redirect:/user/userCenter";
	}
	
	
	/** 个人中心（我的） */
	@RequestMapping("/userCenter")
	public String userCenter(HttpSession session, HttpServletRequest request)throws IOException{
		
		int sessionStatus = initialWeixinUser(session, request);
        if (sessionStatus == 1) return "redirect:/oAuth2/"+TokenController.RETURN_URL_USECENTER;

        User user = (User) session.getAttribute("currentUser");
        //User user = userService.get(1L);
        
        StoreInfo storeInfo = storeInfoService.getStoreInfoByUserId(user.getId());
        
        if(storeInfo != null){
        	request.setAttribute("storeInfo", storeInfo);
        }
        request.setAttribute("userInfo", user);
        request.setAttribute("photoPath",UploadHelper.mmNum(user.getId(),5000)+"/");
		return "/user/userCenter";
	}
	
	/** 我的关注 */
	@RequestMapping("/myInterest")
	public String myInterest(HttpSession session, HttpServletRequest request)throws IOException{
		
		int sessionStatus = initialWeixinUser(session, request);
        if (sessionStatus == 1) return "redirect:/oAuth2/"+TokenController.RETURN_URL_MYINTEREST;

        User user = (User) session.getAttribute("currentUser");
        //User user = userService.get(10009L);
        
        List<UserInterest> interests = userInterestService.getByUserId(user.getId());
        int size = 1;
        if(interests != null){
        	size = interests.size();
        }
        Long[] ids = new Long[size];
        int i = 0;
        if(interests != null){
        	for(UserInterest ui : interests){
        		ids[i] = ui.getStoreId();
        		i++;
        	}
        }
        
        List<StoreCase> cases = storeCaseService.listStoreCaseByStoreIds(ids);
        
        request.setAttribute("cases", getPath(cases));
        request.setAttribute("userInfo", user);
        request.setAttribute("photoPath",UploadHelper.mmNum(user.getId(),5000)+"/");
		return "/user/myInterest";
	}
	
	private List<StoreCase> getPath(List<StoreCase> list){
		
		List<StoreCase> newList = new ArrayList<StoreCase>();
		
		for(StoreCase sc : list){
			sc.setPath(UploadHelper.mmNum(sc.getId(),5000)+"/");
			newList.add(sc);
		}
		
		return newList;
	}
	
	/** 关注店铺*/
	@RequestMapping("/interestStore/{id}")
	public String interestStore(HttpSession session, HttpServletRequest request,@PathVariable long id)throws IOException{
		 
		int sessionStatus = initialWeixinUser(session, request);
        if (sessionStatus == 1) return "redirect:/oAuth2/"+TokenController.RETURN_URL_USERINFO;
		User user = (User) session.getAttribute("currentUser");
		 //User user = userService.get(1L);
		 UserInterest old = userInterestService.getByUserIdAndStoreId(user.getId(), id);
		 if(old == null){
			 
			 UserInterest ui = new UserInterest();
			 ui.setStoreId(id);
			 ui.setUserId(user.getId());
			 userInterestService.save(ui);
		 }
		 
		 
		 return redirect("/store/storeDetail/"+id);
	}
	
	/** 个人用户信息 */
	@RequestMapping("/userInfo")
	public String userInfo(HttpSession session, HttpServletRequest request)throws IOException{
		
		int sessionStatus = initialWeixinUser(session, request);
        if (sessionStatus == 1) return "redirect:/oAuth2/"+TokenController.RETURN_URL_USERINFO;

        User user = (User) session.getAttribute("currentUser");
        User user2 = userService.get(user.getId());
        request.setAttribute("userInfo", user2);
        request.setAttribute("photoPath",UploadHelper.mmNum(user.getId(),5000)+"/");
		return "/user/userInfo";
	}
	
	/** 修改个人信息 
	 * @throws IOException */
	@RequestMapping("/settingInfo")
	public String settingInfo(HttpSession session, HttpServletRequest request) throws IOException{
		
		int sessionStatus = initialWeixinUser(session, request);
        if (sessionStatus == 1) return "redirect:/oAuth2/"+TokenController.RETURN_URL_USERINFO;

        User user = (User) session.getAttribute("currentUser");
        User user2 = userService.get(user.getId());
        request.setAttribute("userInfo", user2);
		return "/user/settingInfo";
	}
	@RequestMapping(value="/updateUserInfo/{id}",method=RequestMethod.POST)
	public String settingInfo(HttpServletRequest request,@ModelAttribute("user")User user){
		
		userService.clear();
		userService.update(user);
		return redirect("/user/userInfo");
	}
	
	/** 修改头像 */
	@RequestMapping("/settingUserPic")
	public String settingUserPic(HttpSession session, HttpServletRequest request)throws IOException{
		
		int sessionStatus = initialWeixinUser(session, request);
        if (sessionStatus == 1) return "redirect:/oAuth2/"+TokenController.RETURN_URL_USERINFO;

        User user = (User) session.getAttribute("currentUser");
        //User user = userService.get(1L);
        request.setAttribute("user", user);
    	request.setAttribute("photoPath", UploadHelper.mmNum(user.getId(),5000)+"/");
		return "/user/settingUserPic";
	}
	
	@RequestMapping(value="/settingUserPic",method=RequestMethod.POST)
	public String addJianFeiLogPost(HttpSession session, HttpServletRequest request) throws Exception{
		
		User user = (User)session.getAttribute("currentUser");
        //User user = userService.get(1L);
		
        // 上传头像
        Map<String, String> fileMap = new HashMap<String, String>();
        fileMap = UploadHelper.getFileNameByUser(
 				request, "uploadFile", user.getId());
        if(fileMap != null){
	        
 			String fileName = null;
 			String fileExt = null;
 			fileName = fileMap.get("fileName").toString();
 			fileExt = fileMap.get("fileExt").toString();
 			user.setPicUrl(fileName+fileExt);
		 		
        }
        userService.update(user);
        
        return redirect("/user/userInfo");
		
	}
	

	
	/** 刷新个人信息 */
	@RequestMapping("/refreshUserInfo")
	public String refreshUserInfo(HttpSession session, HttpServletRequest request)throws IOException{
		
		int sessionStatus = refreshWeixinUser(session, request);
        if (sessionStatus == 1) return "redirect:/oAuth2/"+TokenController.RETURN_URL_REFRESHUSERINFO;
        
        return redirect("/user/userInfo");
	}
	
	/** 获取二维码 */
	@RequestMapping("/getQRCode")
	public String getQRCode(HttpSession session, HttpServletRequest request)throws Exception{
		
		logger.debug("【/user/getQRCode】");
		int sessionStatus = initialWeixinUser(session, request);
        if (sessionStatus == 1) return "redirect:/oAuth2/"+TokenController.RETURN_URL_GETQRCODE;
		User user = (User) session.getAttribute("currentUser");
		//User user = userService.get(1L);
		 
		long mkdirNum = UploadHelper.mmNum(user.getId(),5000); 
		File file =new File(imageQRCodeReferencePath+mkdirNum);
		File file1 =new File(imageHeaderReferencePath+mkdirNum);  
		//如果文件夹不存在则创建    
		if  (!file .exists()  && !file .isDirectory())      
		{       			 
		    file .mkdir();  
		   
		}
		if  (!file1 .exists()  && !file1 .isDirectory())      
		{       
		    
		    file1 .mkdir();  
		   
		}
		File file2=new File(imageQRCodeReferencePath+mkdirNum+"/"+user.getId()+".png"); 
		 
		if(!file2.exists()){
			StoreInfo store = storeInfoService.getStoreInfoByUserId(user.getId());
    		String url = weixin_serverPath +"/store/storeDetail/"+store.getId();
    		
    /*		ImageUtils.getUrlImg2File(user.getLogo(), 
    									imageHeaderReferencePath+user.getId()/1000+"/"+user.getId()+".jpg",
    									defaultImageHeaderReferencePath);		*/
    	    ImageUtils.getUrlImg2File(user.getLogo(), 
                              imageHeaderReferencePath+mkdirNum+"/"+user.getId()+".jpg",
                              defaultImageHeaderReferencePath);   
    
            try {
                // 生成二维码
                //File file = new File(imageQRCodeReferencePath+user.getId()/1000+"/"+user.getId()+".png");
                //file = new File(imageQRCodeReferencePath+"1/"+user.getId()+".png");
                ZXingCodeUtils zp = new ZXingCodeUtils(); 		// 实例化二维码工具
                ZXingConfig zxingconfig = new ZXingConfig();    // 实例化二维码配置参数
                zxingconfig.setHints(zp.getDecodeHintType());   // 设置二维码的格式参数
                zxingconfig.setContent(url);				    // 设置二维码生成内容
                //zxingconfig.setLogoPath(imageHeaderReferencePath+user.getId()/1000+"/"+user.getId()+".jpg"); 		// 设置Logo图片
                zxingconfig.setLogoPath(imageHeaderReferencePath+mkdirNum+"/"+user.getId()+".jpg");         // 设置Logo图片
                zxingconfig.setLogoConfig(new LogoConfig());    // Logo图片参数设置   
                zxingconfig.setLogoFlg(true);   				// 设置生成Logo图片
                BufferedImage bim = zp.getQR_CODEBufferedImage(zxingconfig);// 生成二维码
                ImageIO.write(bim, "png", file2);    			// 图片写出
                Thread.sleep(500);  							// 缓冲
    
                zp.parseQR_CODEImage(bim);  // 解析调用
            } catch (Exception e) {
                e.printStackTrace();
                // 如果文件未创建，则显示提示信息
                file2=new File(imageQRCodeReferencePath+mkdirNum+"/"+user.getId()+".png");    
                if(!file2.exists())    
                {  
                   	request.setAttribute("message", "二维码创建失败，请重新操作！");
                    return "/message";	
                }
            }
		}
        
        StringBuffer sb = new StringBuffer();
        sb.append("/user/displayQRCode?userId=");
        sb.append(user.getId());
        sb.append("&tempId=");
        sb.append(TEMP_QRCODE_SHOW_GET);
        return redirect(sb.toString());
	}
	
	
	/** 显示推广二维码 */
	@RequestMapping("/displayQRCode")
	public String displayQRCode(HttpSession session, HttpServletRequest request, Model model)throws Exception{
		
		logger.debug("【/user/displayQRCode】");
		String id = request.getParameter("userId");
		String tempId = request.getParameter("tempId");	
		
		
		if(StringUtils.isNoEmpty(id)){
			long mkdirNum = UploadHelper.mmNum(Long.parseLong(id),5000);
            session.setAttribute("refereeId", id);
            session.setAttribute("userId", id);
            session.setAttribute("mkdirNum", mkdirNum);
        }
		if(StringUtils.isNoEmpty(tempId)){
		    session.setAttribute("tempId", tempId);
		    
		}
		
	    int sessionStatus = initialWeixinUser(session, request);
	    if (sessionStatus == 1) return "redirect:/oAuth2/"+TokenController.RETURN_URL_DISPLAYQRCODE;
		
        //weixinShare(request, "", "");
	
		return "/user/displayQRCode";				
	}

	@Override
	protected String getModule() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/** ---------------------------- Autowired ------------------------------ **/
	@ModelAttribute("user")
	public User getUser(HttpServletRequest request) {

		String requestURI = request.getRequestURI();
		Long id = extractId(requestURI);
		logger.debug("getUser: request.getRequestURI[{}], id[{}]",
				requestURI, id);

		if (id == null) {
			return new User();
		}
		return userService.get(id);
	}
}
