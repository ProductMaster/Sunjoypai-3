package com.taoists.weixin.context;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taoists.weixin.model.AccessToken;
import com.taoists.weixin.util.WeixinUtils;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2015年6月6日 下午12:40:32 类说明
 */
public class AccessTokenThread implements Runnable {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	public static volatile AccessToken accessToken;
    
    @Override
    public void run() 
    {
    	logger.debug("[ AccessTokenThread running ]");
        while(true) 
        {
        	AccessToken token = WeixinUtils.getAccessToken(); // 从微信服务器刷新access_token
            if(token != null){
            	accessToken = token;
            }
             
            try{
                if(null != accessToken){
                    Thread.sleep((accessToken.getExpiresIn() - 1200) * 1000);    // 休眠6000秒
                }else{
                    Thread.sleep(60 * 1000);    // 如果access_token为null，60秒后再获取
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