package com.taoists.weixin.context;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taoists.weixin.model.JsapiTicket;
import com.taoists.weixin.util.WeixinUtils;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2015年6月6日 下午12:48:19 
 * 类说明 
 */
public class JsapiTicketThread  implements Runnable {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	public static volatile JsapiTicket jsapiTicket;
    
    @Override
    public void run() 
    {
    	logger.debug("[ JsapiTicketThread running ]");
        while(true) 
        {
			logger.debug("access_token info =========");
			logger.debug("create_date: "+AccessTokenThread.accessToken.getCreateDate());
			logger.debug("access_token: "+AccessTokenThread.accessToken.getToken());
			logger.debug("expires_in: "+AccessTokenThread.accessToken.getExpiresIn());
			logger.debug("current currentTimeMillis:"+System.currentTimeMillis());
			
           	JsapiTicket ticket = WeixinUtils.getJsapiTicket(AccessTokenThread.accessToken.getToken()); // 从微信服务器刷新access_token
           	if(ticket != null){
           		jsapiTicket = ticket;
            }
             
            try{
                if(null != jsapiTicket){
                    Thread.sleep((jsapiTicket.getExpiresIn() - 1200) * 1000);    // 休眠6000秒
                }else{
                    Thread.sleep(60 * 1000);    // 如果access_token为null，180秒后再获取
                }
            }catch(InterruptedException e){
                try{
                    Thread.sleep(60 * 1000);
                }catch(InterruptedException e1){
                    e1.printStackTrace();
                }
            }
        }
    }
}