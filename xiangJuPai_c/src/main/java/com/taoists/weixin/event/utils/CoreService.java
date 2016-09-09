package com.taoists.weixin.event.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.taoists.common.Const;
import com.taoists.common.util.StringUtils;
import com.taoists.common.util.UploadHelper;
import com.taoists.user.entity.User;
import com.taoists.user.service.UserService;
import com.taoists.weixin.article.entity.WeixinArticle;
import com.taoists.weixin.model.WeixinOpenUser;
import com.taoists.weixin.msg.entity.TextMsg;
import com.taoists.weixin.msg.service.TextMsgService;
import com.taoists.weixin.util.WeixinUtils;
import com.taoists.weixin.article.service.WeixinArticleService;
import com.taoists.weixin.event.model.resp.Article;
import com.taoists.weixin.event.model.resp.DKFServiceMessage;
import com.taoists.weixin.event.model.resp.NewsMessage;
import com.taoists.weixin.event.model.resp.TextMessage;

/**
 * @author George E-mail:lendun@163.com
 * @version 创建时间：2013-11-15 下午06:03:44
 * 
 */
public class CoreService {
    /**
     * 处理微信发来的请求
     * 
     * @param request
     * @return
     */
    public static String processRequest(HttpServletRequest request, String serverPath) {
        
        String respMessage = null;
        boolean isDFK = false;
        try {
            // 默认返回的文本消息内容
            String respContent = "";

            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);

            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");

            // 公众帐号
            String toUserName = requestMap.get("ToUserName");

            // 消息类型
            String msgType = requestMap.get("MsgType");

            NewsMessage newsMessage = null;
            DKFServiceMessage dfkMsg = null;
            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);

            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                // 接收用户发送的文本消息内容
                String content = requestMap.get("Content");
                TextMsgService textMsgService = (TextMsgService) WebApplicationContextUtils
                                .getWebApplicationContext(
                                                request.getSession()
                                                                .getServletContext())
                                .getBean("textMsgService");
                List<TextMsg> textMsgs = textMsgService.findAll();
                boolean hasResp = false;
                // for(TextMsg tm:textMsgs){
                // if(content.equals(tm.getKey())){
                // hasResp = true;
                // respContent=tm.getContent();
                // textMessage.setContent(respContent);
                // break;
                // }
                // }

                WeixinArticleService weixinArticleService = (WeixinArticleService) WebApplicationContextUtils
                                .getWebApplicationContext(
                                                request.getSession()
                                                                .getServletContext())
                                .getBean("weixinArticleService");
                List<WeixinArticle> articleMsgs = weixinArticleService
                                .findAll();
                List<Article> articleList = new ArrayList<Article>();
                for (WeixinArticle articleMsg : articleMsgs) {
                    if (content.equals(articleMsg.getKey())
                                    && !"".equals(content)) {
                        hasResp = true;
                        // 创建图文消息
                        newsMessage = new NewsMessage();
                        newsMessage.setToUserName(fromUserName);
                        newsMessage.setFromUserName(toUserName);
                        newsMessage.setCreateTime(new Date().getTime());
                        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                        newsMessage.setFuncFlag(0);
                        

                        Article article_index = new Article();
                        article_index.setTitle(articleMsg.getTitle());
                        article_index.setDescription(articleMsg
                                        .getDescription());
                        article_index.setPicUrl(articleMsg.getPicUrl());
                        String url = articleMsg.getUrl();
                        if (url.toLowerCase().startsWith("http"))
                            article_index.setUrl(url + "?weixinId="
                                            + fromUserName);
                        else
                            article_index.setUrl(serverPath
                                            + articleMsg.getUrl()
                                            + "?weixinId=" + fromUserName);
                        articleList.add(article_index);
                        for (WeixinArticle wa : articleMsg.getList()) {
                        	
                        	
                            Article article = new Article();
                            article.setTitle(wa.getTitle());
                            article.setDescription(wa.getDescription());
                            article.setPicUrl(wa.getPicUrl());
                            String url1 = wa.getUrl();
                            if (url1.toLowerCase().startsWith("http"))
                                article.setUrl(url1 + "?weixinId="
                                                + fromUserName);
                            else
                                article.setUrl(serverPath + url1 + "?weixinId="
                                                + fromUserName);
                            articleList.add(article);
                        }
                        // 设置图文消息个数
                        newsMessage.setArticleCount(articleList.size());
                        // 设置图文消息包含的图文集合
                        newsMessage.setArticles(articleList);

                    }
                }
                if (!hasResp) {
                    isDFK = true;
                    dfkMsg = new DKFServiceMessage();
                    dfkMsg.setToUserName(fromUserName);
                    dfkMsg.setFromUserName(toUserName);
                    dfkMsg.setCreateTime(new Date().getTime());
                    dfkMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_SERVICE);
                }
            }
            // 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                isDFK = true;
                dfkMsg = new DKFServiceMessage();
                dfkMsg.setToUserName(fromUserName);
                dfkMsg.setFromUserName(toUserName);
                dfkMsg.setCreateTime(new Date().getTime());
                dfkMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_SERVICE);
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                String location_X = requestMap.get("Location_X");
                String location_Y = requestMap.get("Location_Y");

                newsMessage = new NewsMessage();
                newsMessage.setToUserName(fromUserName);
                newsMessage.setFromUserName(toUserName);
                newsMessage.setCreateTime(new Date().getTime());
                newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                newsMessage.setFuncFlag(0);
                List<Article> articleList = new ArrayList<Article>();

                Article article = new Article();
                article.setTitle("周边");
                article.setDescription(requestMap.get("Label"));
                article.setPicUrl("http://st.map.qq.com/api?size=604*300&center="
                                + location_Y
                                + ","
                                + location_X
                                + "&zoom=14&markers="
                                + location_Y
                                + ","
                                + location_X + ",b");
                article.setUrl("http://st.map.qq.com/api?size=604*300&center="
                                + location_Y + "," + location_X
                                + "&zoom=14&markers=" + location_Y + ","
                                + location_X + ",b");
                articleList.add(article);
                // 设置图文消息个数
                newsMessage.setArticleCount(articleList.size());
                // 设置图文消息包含的图文集合
                newsMessage.setArticles(articleList);
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                isDFK = true;
                dfkMsg = new DKFServiceMessage();
                dfkMsg.setToUserName(fromUserName);
                dfkMsg.setFromUserName(toUserName);
                dfkMsg.setCreateTime(new Date().getTime());
                dfkMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_SERVICE);
            }
            // 音频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                isDFK = true;
                dfkMsg = new DKFServiceMessage();
                dfkMsg.setToUserName(fromUserName);
                dfkMsg.setFromUserName(toUserName);
                dfkMsg.setCreateTime(new Date().getTime());
                dfkMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_SERVICE);
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                
                // 订阅事件处理
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    
                    System.out.println("subscribe event start ...");
                    UserService userService = (UserService) WebApplicationContextUtils.getWebApplicationContext(
                                                    request.getSession().getServletContext()).getBean("userService");

                    
                    User oldUser = userService.getUserByWeixinId(fromUserName);
                    User user = new User();
                    if (oldUser == null) {     
                    	user.setReferee(userService.get(Const.NULL_VALUE_LONG));
                        user.setWxOpenId(fromUserName);
                        userService.save(user);
                        user.setPhotoPath(UploadHelper.mmNum(user.getId(),5000)+"/");
                        userService.update(user);
                    }else{
                        user = oldUser;
                    }
                    
                    String key = requestMap.get("EventKey");
                    System.out.println("EventKey ===> "+ key);
                    if (StringUtils.isNoEmpty(key)) {
                        //String qrcodeOwnerId = key.replace("qrscene_", "");
/*                        long qrcodeOwnerId = Long.valueOf(key.substring(key.indexOf("_")+1, key.length()));
                        System.out.println("parent userId :"+qrcodeOwnerId);
                        user.setParentId(qrcodeOwnerId);
                        userService.update(user);
                        
                        Users qrcodeOwner = userService.get(qrcodeOwnerId);
                        String strJson = "{\"touser\" :\""+ qrcodeOwner.getWeixinId() + "\",";
                        strJson += "\"msgtype\":\"text\",";
                        strJson += "\"text\":{";
                        strJson += "\"content\":\"受到您的感召，您的一位好友投入了谷生谷长的怀抱\"";
                        strJson += "}}";
                        WeixinUtils.sendCustomerMessage(strJson);*/
                    }   
                    
                    respContent = "欢迎关注享居派！";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                    return respMessage;
                   
                   /* newsMessage = new NewsMessage();
                    newsMessage.setToUserName(fromUserName);
                    newsMessage.setFromUserName(toUserName);
                    newsMessage.setCreateTime(new Date().getTime());
                    newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                    newsMessage.setFuncFlag(0);
                    List<Article> articleList = new ArrayList<Article>();
                    Article article = new Article();  
                    article.setTitle("贝酷153引领车漆养护潮流");  
                    article.setDescription("贝酷153引领车漆养护潮流");  
                    article.setPicUrl("http://m.beikool.com/article1.png");  
                    article.setUrl("http://mp.weixin.qq.com/s?__biz=MzIwNjAyNzcxOA==&mid=401354179&idx=1&sn=f50a74d1a8b8bbc9995442db3b23443d&scene=0&previewkey=ovzEhSVg%2BWfE8kgloFMqSMwqSljwj2bfCUaCyDofEow%3D#wechat_redirect");  
                    articleList.add(article); 
                    Article article2 = new Article();  
                    article2.setTitle("冬天来了，您需要的爱车养护知识");  
                    article2.setDescription("冬天来了，您需要的爱车养护知识");  
                    article2.setPicUrl("http://m.beikool.com/article2.png");  
                    article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzIwNjAyNzcxOA==&mid=400801332&idx=1&sn=55c8a2bb5759a27a97688b6d9f39e9b1&scene=0&previewkey=ovzEhSVg%2BWfE8kgloFMqSMwqSljwj2bfCUaCyDofEow%3D#wechat_redirect");  
                    articleList.add(article2); 
                    // 设置图文消息个数  
                    newsMessage.setArticleCount(articleList.size());  
                    // 设置图文消息包含的图文集合  
                    newsMessage.setArticles(articleList);  
                    // 将图文消息对象转换成xml字符串  
                    respMessage = MessageUtil.newsMessageToXml(newsMessage); 
                   
                    return respMessage;*/

                } else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
                    System.out.println("SCAN event start ...");
                    respContent = "欢迎关注享居派！";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                    return respMessage;
                    /*newsMessage = new NewsMessage();
                    newsMessage.setToUserName(fromUserName);
                    newsMessage.setFromUserName(toUserName);
                    newsMessage.setCreateTime(new Date().getTime());
                    newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                    newsMessage.setFuncFlag(0);
                    List<Article> articleList = new ArrayList<Article>();
                    Article article = new Article();  
                    article.setTitle("贝酷153引领车漆养护潮流");  
                    article.setDescription("贝酷153引领车漆养护潮流");  
                    article.setPicUrl("http://m.beikool.com/article1.png");  
                    article.setUrl("http://mp.weixin.qq.com/s?__biz=MzIwNjAyNzcxOA==&mid=401354179&idx=1&sn=f50a74d1a8b8bbc9995442db3b23443d&scene=0&previewkey=ovzEhSVg%2BWfE8kgloFMqSMwqSljwj2bfCUaCyDofEow%3D#wechat_redirect");  
                    articleList.add(article); 
                    Article article2 = new Article();  
                    article2.setTitle("冬天来了，您需要的爱车养护知识");  
                    article2.setDescription("冬天来了，您需要的爱车养护知识");  
                    article2.setPicUrl("http://m.beikool.com/article2.png");  
                    article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzIwNjAyNzcxOA==&mid=400801332&idx=1&sn=55c8a2bb5759a27a97688b6d9f39e9b1&scene=0&previewkey=ovzEhSVg%2BWfE8kgloFMqSMwqSljwj2bfCUaCyDofEow%3D#wechat_redirect");  
                    articleList.add(article2);  
                     
                    
                    // 设置图文消息个数  
                    newsMessage.setArticleCount(articleList.size());  
                    // 设置图文消息包含的图文集合  
                    newsMessage.setArticles(articleList);  
                    // 将图文消息对象转换成xml字符串  
                    respMessage = MessageUtil.newsMessageToXml(newsMessage); 
                    
                    return respMessage;*/
                }
                // 取消订阅
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                }
                // 自定义菜单点击事件
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    String eventKey = requestMap.get("EventKey");

                    TextMsgService textMsgService = (TextMsgService) WebApplicationContextUtils
                                    .getWebApplicationContext(
                                                    request.getSession()
                                                                    .getServletContext())
                                    .getBean("textMsgService");
                    List<TextMsg> textMsgs = textMsgService.findAll();
                    if ("我的订单".equals(eventKey)) {
                    	// 定制化信息输出
                    } else {
                        for (TextMsg tm : textMsgs) {
                            if (eventKey.equals(tm.getKey())) {
                                respContent = tm.getContent();
                                textMessage.setContent(respContent);
                                respMessage = MessageUtil.textMessageToXml(textMessage);
                            }
                        }
                    }


                    WeixinArticleService weixinArticleService = (WeixinArticleService) WebApplicationContextUtils
                                    .getWebApplicationContext(
                                                    request.getSession()
                                                                    .getServletContext())
                                    .getBean("weixinArticleService");
                    List<WeixinArticle> articleMsgs = weixinArticleService
                                    .findAll();
                    for (WeixinArticle articleMsg : articleMsgs) {
                        if (eventKey.equals(articleMsg.getKey())
                                        && !"".equals(eventKey)) {
                            // 创建图文消息
                            newsMessage = new NewsMessage();
                            newsMessage.setToUserName(fromUserName);
                            newsMessage.setFromUserName(toUserName);
                            newsMessage.setCreateTime(new Date().getTime());
                            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                            newsMessage.setFuncFlag(0);
                            List<Article> articleList = new ArrayList<Article>();

                            Article article_index = new Article();
                            article_index.setTitle(articleMsg.getTitle());
                            article_index.setDescription(articleMsg
                                            .getDescription());
                            article_index.setPicUrl(articleMsg.getPicUrl());
                            String url = articleMsg.getUrl();
                            if (url.toLowerCase().startsWith("http"))
                                article_index.setUrl(url + "?weixinId="
                                                + fromUserName);
                            else
                                article_index.setUrl(serverPath + url
                                                + "?weixinId=" + fromUserName);
                            articleList.add(article_index);
                            for (WeixinArticle wa : articleMsg.getList()) {
                                Article article = new Article();
                                article.setTitle(wa.getTitle());
                                article.setDescription(wa.getDescription());
                                article.setPicUrl(wa.getPicUrl());
                                String url1 = wa.getUrl();
                                if (url1.toLowerCase().startsWith("http"))
                                    article.setUrl(url1 + "?weixinId="
                                                    + fromUserName);
                                else
                                    article.setUrl(serverPath + url1
                                                    + "?weixinId="
                                                    + fromUserName);
                                articleList.add(article);
                            }
                            // 设置图文消息个数
                            newsMessage.setArticleCount(articleList.size());
                            // 设置图文消息包含的图文集合
                            newsMessage.setArticles(articleList);

                        }
                    }

                }
            }
            if (isDFK) {
                respMessage = MessageUtil.dkfServiceMessageToXml(dfkMsg);
            } else if (newsMessage == null || newsMessage.getArticles() == null
                            || newsMessage.getArticles().size() == 0) {
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            } else {
                // 将图文消息对象转换成xml字符串
                respMessage = MessageUtil.newsMessageToXml(newsMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
        return respMessage;
    }

    /**
     * emoji表情转换(hex -> utf-16)
     * 
     * @param hexEmoji
     * @return
     */
    public static String emoji(int hexEmoji) {
        return String.valueOf(Character.toChars(hexEmoji));
    }

}
