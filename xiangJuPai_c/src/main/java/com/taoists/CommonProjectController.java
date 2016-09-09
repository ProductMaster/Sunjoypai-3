package com.taoists;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.taoists.appoint.service.MobileApplicationService;
import com.taoists.common.Const;
import com.taoists.common.controller.CommonFrameworkController;
import com.taoists.common.util.ImageUtils;
import com.taoists.common.util.StringUtils;
import com.taoists.common.util.UploadHelper;
import com.taoists.store.service.CatalogService;
import com.taoists.store.service.StoreCaseImgsService;
import com.taoists.store.service.StoreCaseService;
import com.taoists.store.service.StoreCategoryService;
import com.taoists.store.service.StoreInfoService;
import com.taoists.sys.dataDict.service.DataDictService;
import com.taoists.user.entity.User;
import com.taoists.user.service.UserInterestService;
import com.taoists.user.service.UserService;
import com.taoists.weixin.menu.service.MainMenuService;
import com.taoists.weixin.menu.service.SubMenuService;
import com.taoists.weixin.model.WeixinOpenId;
import com.taoists.weixin.model.WeixinOpenUser;
import com.taoists.weixin.msg.service.TextMsgService;
import com.taoists.weixin.util.WeixinUtils;


/**
 * @author George E-mail:lendun@163.com
 * @version 创建时间： 2013-8-29 上午12:26:172013-8-29
 * 
 */
public abstract class CommonProjectController extends CommonFrameworkController {
	
    @Value("#{messageSource.getMessage('imageQRCodeReferencePath',null,null)}")
    protected String imageQRCodeReferencePath;
    @Value("#{messageSource.getMessage('imageHeaderReferencePath',null,null)}")
    protected String imageHeaderReferencePath;
    @Value("#{messageSource.getMessage('defaultImageHeaderReferencePath',null,null)}")
    protected String defaultImageHeaderReferencePath;
    @Value("#{messageSource.getMessage('weixin_serverPath',null,null)}")
	protected String weixin_serverPath;
	
    @Autowired
    protected MobileApplicationService mobileApplicationService;
    @Autowired
    protected StoreInfoService storeInfoService;
    @Autowired
    protected StoreCaseService storeCaseService;
    @Autowired
    protected CatalogService catalogService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected MainMenuService mainMenuService;
    @Autowired
    protected SubMenuService subMenuService;
    @Autowired
    protected DataDictService dataDictService;
    @Autowired
    protected TextMsgService textMsgService;
    @Autowired
    protected UserInterestService userInterestService;
    @Autowired
    protected StoreCategoryService storeCategoryService;
    @Autowired
    protected StoreCaseImgsService storeCaseImgsService;
    
//========================================================================
	
    protected int initialWeixinUser(HttpSession session, HttpServletRequest request) throws IOException{
        
        logger.debug("[ initialWeixinUser ]");
        
        // 是否从菜单获取code，无则判断是否已登录，否则跳转微信认证
        String code = request.getParameter("code");
        if(!StringUtils.isNoEmpty(code)){
            User user = (User)session.getAttribute("currentUser");
            if(user != null){
                logger.debug("【initialWeixinUser】user exist:"+user.getNickname()+"("+user.getWxOpenId()+")("+user.getId()+")");
                return 0;
            }else{
                logger.debug("【initialWeixinUser】code and user not exist!");
                return 1;
            }
        }
        
        // 通过code获取微信帐号的OpenId      
        WeixinOpenId weixinOpenId = WeixinUtils.getOpenId(code);
        String openId = weixinOpenId.getOpenId();
        if(openId ==null || openId.equals("")){
            logger.debug("【initialWeixinUser】weixinopenId get fail!");
            return 1;   // 获取失败了，重新认证吧
        }        
        logger.debug("【initialWeixinUser】initialWeixinUser-openid:"+openId);
        
        //AccessToken token = AccessTokenThread.accessToken;
        WeixinOpenUser weixinUser = WeixinUtils.getUserInfo(weixinOpenId);
        session = request.getSession(); 
        session.setAttribute("weixintUser", weixinUser);
        session.setAttribute("openId", openId);
        logger.debug("【initialWeixinUser】current openId ===>"+openId);
        
        User oldUser = userService.getUserByWeixinId(openId);
        if(oldUser!=null){			// 如果会员信息已经存在
            logger.debug("【initialWeixinUser】oldUser exist:"+oldUser.getNickname()+"("+oldUser.getWxOpenId()+")("+oldUser.getId()+")");
            
            if(weixinUser !=null && !StringUtils.isNoEmpty(oldUser.getLogo())){
                oldUser.setNickname(weixinUser.getNickname().trim());
                oldUser.setUserName(weixinUser.getNickname().trim());
                oldUser.setLogo(weixinUser.getHeadimgurl());
                setWeixinUserSex(oldUser, weixinUser);
                userService.update(oldUser);
                
                String url = weixin_serverPath +"/activity/tui001?userid="+oldUser.getId();
                try{
/*                    ImageUtils.getUrlImg2File(oldUser.getLogo(), 
                                                imageHeaderReferencePath+oldUser.getId()/1000+"/"+oldUser.getId()+".jpg",
                                                defaultImageHeaderReferencePath);   */
                    ImageUtils.getUrlImg2File(oldUser.getLogo(), 
                                    imageHeaderReferencePath+"1/"+oldUser.getId()+".jpg",
                                    defaultImageHeaderReferencePath);
                }catch(Exception e){
                    e.printStackTrace();
                }
                //String filePath = imageQRCodeReferencePath+oldUser.getId()/1000+"/"+oldUser.getId()+".png";
                String filePath = imageQRCodeReferencePath+"1/"+oldUser.getId()+".png";
                //String logoPath = imageHeaderReferencePath+oldUser.getId()/1000+"/"+oldUser.getId()+".jpg";
                String logoPath = "";
                ImageUtils.generateQRCode(filePath, logoPath, url);
            }
            session.setAttribute("currentUser", oldUser);
            
        }else{						// 如果会员信息不存在，则创建新用户
            logger.debug("【initialWeixinUser】create new user");
            User user = new User();
            user.setWxOpenId(openId);
            user.setEmail(openId);
            user.setPwd(openId);
            user.setLevel(0);
            // 是否有推荐人
            if(session.getAttribute("refereeId") != null){
                User refereeUser = userService.get( Long.valueOf( (String)session.getAttribute("refereeId") ));
                User referee2User = userService.get(refereeUser.getReferee().getId());
                User referee3User = userService.get(referee2User.getReferee().getId());
                refereeUser.setSubordinateNum(refereeUser.getSubordinateNum()+1);
                userService.update(refereeUser);
                user.setReferee( refereeUser );
                user.setReferee2Id(referee2User.getId());
                user.setReferee3Id(referee3User.getId());
                
            }else{
                user.setReferee(userService.get(-99L));
                user.setReferee2Id(-99L);
                user.setReferee3Id(-99L);
            }
            if(weixinUser !=null && StringUtils.isNoEmpty(weixinUser.getNickname())){
                user.setNickname(weixinUser.getNickname().trim());
                user.setUserName(weixinUser.getNickname().trim());
                user.setLogo(weixinUser.getHeadimgurl());   
                setWeixinUserSex(user, weixinUser);
            }
            userService.save(user);      
            user.setPhotoPath(UploadHelper.mmNum(user.getId(),5000)+"/");
            userService.update(user);
            session.setAttribute("currentUser", user);
            
            String url = weixin_serverPath +"/activity/tui001?userid="+user.getId();
            try{
 /*               ImageUtils.getUrlImg2File(user.getLogo(), 
                                            imageHeaderReferencePath+user.getId()/1000+"/"+user.getId()+".jpg",
                                            defaultImageHeaderReferencePath);   */
                ImageUtils.getUrlImg2File(user.getLogo(), 
                                imageHeaderReferencePath+"1/"+user.getId()+".jpg",
                                defaultImageHeaderReferencePath);
                
            }catch(Exception e){
                e.printStackTrace();
            }
            /*String filePath = imageQRCodeReferencePath+user.getId()/1000+"/"+user.getId()+".png";
            String logoPath = imageHeaderReferencePath+user.getId()/1000+"/"+user.getId()+".jpg";*/
            String filePath = imageQRCodeReferencePath+"1/"+user.getId()+".png";
            String logoPath = imageHeaderReferencePath+"1/"+user.getId()+".jpg";
            ImageUtils.generateQRCode(filePath, logoPath, url);
        }        
        return 0;
    }
    
    protected int refreshWeixinUser(HttpSession session, HttpServletRequest request) throws IOException{
        
        System.out.println("[ refreshWeixinUser ]");
        
        // 是否从菜单获取code，无则判断是否已登录，否则跳转微信认证
        String code = request.getParameter("code");
        if(!StringUtils.isNoEmpty(code)){
            System.out.println("【refreshWeixinUser】code not exist!");
        	return 1;           
        }
        
        // 通过code获取微信帐号的OpenId      
        WeixinOpenId weixinOpenId = WeixinUtils.getOpenId(code);
        String openId = weixinOpenId.getOpenId();
        if(openId ==null || openId.equals("")){
            System.out.println("【refreshWeixinUser】weixinopenId get fail!");
            return 1;   // 获取失败了，重新认证吧
        }        
        System.out.println("【refreshWeixinUser】initialWeixinUser-openid:"+openId);
        
        //AccessToken token1 = AccessTokenThread.accessToken;
        WeixinOpenUser weixinUser = WeixinUtils.getUserInfo(weixinOpenId);
        session = request.getSession(); 
        session.setAttribute("weixintUser", weixinUser);
        session.setAttribute("openId", openId);
        System.out.println("【refreshWeixinUser】current openId ===>"+openId);
        
        User oldUser = userService.getUserByWeixinId(openId);
        if(oldUser!=null){			// 如果会员信息已经存在
            System.out.println("【refreshWeixinUser】oldUser exist:"+oldUser.getNickname()+"("+oldUser.getWxOpenId()+")("+oldUser.getId()+")");
            
            if(weixinUser !=null && StringUtils.isNoEmpty(weixinUser.getHeadimgurl())){
                System.out.println("【refreshWeixinUser】update user logo and nickname");
                oldUser.setNickname(weixinUser.getNickname().trim());
                oldUser.setUserName(weixinUser.getNickname().trim());
                oldUser.setLogo(weixinUser.getHeadimgurl());
                System.out.println("【refreshWeixinUser】logo :"+weixinUser.getHeadimgurl());
                setWeixinUserSex(oldUser, weixinUser);
                userService.update(oldUser);
                System.out.println("【refreshWeixinUser】logo url:"+oldUser.getLogo());
                
                String url = weixin_serverPath +"/activity/tui001?userid="+oldUser.getId();
                try{
                    ImageUtils.getUrlImg2File(oldUser.getLogo(), 
                                                imageHeaderReferencePath+"1/"+oldUser.getId()+".jpg",
                                                defaultImageHeaderReferencePath);   
                }catch(Exception e){
                    e.printStackTrace();
                }
                String filePath = imageQRCodeReferencePath+"1/"+oldUser.getId()+".png";
                String logoPath = imageHeaderReferencePath+"1/"+oldUser.getId()+".jpg";
                ImageUtils.generateQRCode(filePath, logoPath, url);
            }
            session.setAttribute("currentUser", oldUser);
        }else{						// 如果会员信息不存在，则创建新用户
            System.out.println("【refreshWeixinUser】create new user");
            User user = new User();
            user.setWxOpenId(openId);
            user.setEmail(openId);
            user.setPwd(openId);
            user.setLevel(0);
            // 是否有推荐人
            if(session.getAttribute("refereeId") != null && StringUtils.isNoEmpty((String)session.getAttribute("refereeId"))){
                User refereeUser = userService.get( Long.valueOf( (String)session.getAttribute("refereeId") ));
                User referee2User = userService.get(refereeUser.getReferee().getId());
                User referee3User = userService.get(referee2User.getReferee().getId());
                refereeUser.setSubordinateNum(refereeUser.getSubordinateNum()+1);
                userService.update(refereeUser);
                user.setReferee( refereeUser );
                user.setReferee2Id(referee2User.getId());
                user.setReferee3Id(referee3User.getId());
                
            }else{
                user.setReferee(userService.get(-99L));
                user.setReferee2Id(-99L);
                user.setReferee3Id(-99L);
            }
            if(weixinUser !=null && StringUtils.isNoEmpty(weixinUser.getNickname())){
                user.setNickname(weixinUser.getNickname().trim());
                user.setUserName(weixinUser.getNickname().trim());
                user.setLogo(weixinUser.getHeadimgurl());   
                setWeixinUserSex(user, weixinUser);
            }
            userService.save(user);   
            user.setPhotoPath(UploadHelper.mmNum(user.getId(),5000)+"/");
            userService.update(user);
            
            session.setAttribute("currentUser", user);
            
            String url = weixin_serverPath +"/activity/tui001?userid="+user.getId();
            try{
                ImageUtils.getUrlImg2File(user.getLogo(), 
                                            imageHeaderReferencePath+"1/"+user.getId()+".jpg",
                                            defaultImageHeaderReferencePath);   
            }catch(Exception e){
                e.printStackTrace();
            }
            String filePath = imageQRCodeReferencePath+"1/"+user.getId()+".png";
            String logoPath = imageHeaderReferencePath+"1/"+user.getId()+".jpg";
            ImageUtils.generateQRCode(filePath, logoPath, url);
        }        
        return 0;
    }
    
    private void setWeixinUserSex(User user, WeixinOpenUser weixinUser){
    	if(weixinUser.getSex().equals("0")) user.setSex(Const.SEX_UNKNOW);
    	if(weixinUser.getSex().equals("1")) user.setSex(Const.SEX_MAN);
    	if(weixinUser.getSex().equals("2")) user.setSex(Const.SEX_WOMAN);    	
    }
}
