package com.taoists.weixin.event.controller; 

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taoists.CommonProjectController;
import com.taoists.common.Const;
import com.taoists.common.util.StringUtils;
import com.taoists.weixin.event.utils.CoreService;
import com.taoists.weixin.event.utils.SHA1;
import com.taoists.weixin.util.WeixinUtils;


/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-11-14 下午04:44:31  
 *  
 */
@SuppressWarnings("serial")
@Controller
public class TokenController extends CommonProjectController{
	
    @Value("#{messageSource.getMessage('weixin_serverPath',null,null)}")
    private String serverPath;
	@Value("#{messageSource.getMessage('weixin_token',null,null)}")
	private String TOKEN;
	
	// GET请求用于响应微信Token认证
	@RequestMapping(value="/weixin",method=RequestMethod.GET)
    public void token(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
 
        String[] str = { TOKEN, timestamp, nonce };
        Arrays.sort(str); // 字典序排序
        String bigStr = str[0] + str[1] + str[2];
        // SHA1加密
        String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();
 
        // 确认请求来至微信
        if (digest.equals(signature)) {
            response.getWriter().print(echostr);
        }
    }
	
	// POST请求用于响应微信菜单及事件
	@RequestMapping(value="/weixin",method=RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        // 调用核心业务类接收消息、处理消息  
        String respMessage = CoreService.processRequest(request,serverPath);  
          
        // 响应消息  
        PrintWriter out = response.getWriter();  
        out.print(respMessage);  
        out.close();  
    }	
	
	//==============================================================================
	
    @Value("#{messageSource.getMessage('weixin_redirect_url',null,null)}")
    private String wx_redirect_url;
	
    //** 向微信发出授权请求 *//*    
    @RequestMapping("/oAuth2/{redirectUrlCode}")
    public String oAuth2(HttpServletRequest request,@PathVariable int redirectUrlCode){
        
        logger.debug("[ /oAuth2 ]");
        //String redirect_uri="/crowd/recommendCrowd/"+orderId;
        //String redirect_uri="http://m.5qbb.cn/crowd/joinOrder/"+orderId;

        
        //网页授权方式（snsapi_userinfo：要用户手动确定；snsapi_base:不需要客户确认）
        String scope="snsapi_userinfo";
        
        //状态标识（a:snsapi_userinfo;b:snsapi_base）
        String state="a";
        
        //授权地址
        String wx_redirect="https://open.weixin.qq.com/connect/oauth2/authorize";
        
        //跳转地址
        //redirect_uri=redirect_uri+"?upOpenid="+upOpenid;
        return "redirect:"+wx_redirect+"?appid="+WeixinUtils.appId+"&redirect_uri="+StringUtils.URLEncode(wx_redirect_url+"/"+redirectUrlCode)+"&response_type=code&scope="+scope+"&state="+state+"#wechat_redirect";
        //WeixinUtils.httpRequest("https://open.weixin.qq.com/connect/oauth2/authorize"+"?appid="+WeixinUtils.appId+"&redirect_uri="+StringUtils.URLEncode(redirect_uri)+"&response_type=code&scope="+scope+"&state="+state+"#wechat_redirect", "GET", null);
    }
    
    //** 解析微信的认证反馈信息 *  
    @RequestMapping("/raise/{redirectUrlCode}")
    public String raise(HttpSession session, HttpServletRequest request, HttpServletResponse response,@PathVariable int redirectUrlCode) throws IOException{
        
        logger.debug("[ /raise/"+ redirectUrlCode + "]");
        String code = request.getParameter("code");
        if(StringUtils.isNoEmpty(code)){
        	
        	long catalogId = Const.NULL_VALUE_LONG;
        	if(!"".equals(session.getAttribute("catalogId"))&&session.getAttribute("catalogId")!=null){
        		catalogId = Long.parseLong(session.getAttribute("catalogId").toString());
        	}
            String redirectUrl = getRedirectUrlFromCode(redirectUrlCode);
            return "redirect:/" + redirectUrl+"?catalog="+catalogId+"&code="+code;
        }
        return "redirect:/oAuth2/"+redirectUrlCode;
    }
    
    public static final int RETURN_URL_DEFAULT       = 0;
    public static final int RETURN_URL_TUI001        = 1;
    public static final int RETURN_URL_MYINTEREST	 	= 2;
    public static final int RETURN_URL_LISTORDER   	 = 3;
    public static final int RETURN_URL_DISPLAYQRCODE = 4;
    public static final int RETURN_URL_USECENTER    	 = 5;
    public static final int RETURN_URL_USERINFO 	 = 6;
    public static final int RETURN_URL_NEWORDER   	 = 7;
    public static final int RETURN_URL_GETQRCODE   	 = 8;
    public static final int RETURN_URL_REFRESHUSERINFO = 9;
    public static final int RETURN_URL_DISPLAYORDERSHARE = 10;
    public static final int RETURN_URL_DISPLAYACTIVITYSHARE = 11;
    public static final int RETURN_URL_ADDVIP        = 12;
    public static final int RETURN_URL_ZENGQUAN        = 13;
    
    public static final int RETURN_URL_ADD_CASE        = 14;
    
    private String getRedirectUrlFromCode(int code){
        String retVal = "";
        switch(code){
        case 0:
            retVal = "store/index";
            break;
        case 2:
            retVal = "user/myInterest";
            break;
        case 3:
            retVal = "/appoint/user/listAppoint/1";
            break;
        case 4:
        	retVal = "user/displayQRCode";
            break;  
        case 5:
        	retVal = "user/userCenter";
            break;
        case 6:
        	retVal = "user/userInfo";
            break;
            
        case 8:
        	retVal = "user/getQRCode";
        	break;	
        case 9:
        	retVal = "user/refreshUserInfo";
        	break;
        case 12:
        	retVal = "pay/weixin/addVip";
        	break;
        case 13:
        	retVal = "zengquan/receiveVoucher";
        	break;
        	
        case 14:
        	retVal = "store/addCase";
        	break;
        }
        return retVal;
    }
    
    //==============================================================================
	protected String getModule() {
		// TODO Auto-generated method stub
		return null;
	}  
}
