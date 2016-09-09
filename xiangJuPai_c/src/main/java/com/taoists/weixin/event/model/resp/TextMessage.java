package com.taoists.weixin.event.model.resp; 


/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-11-15 下午06:10:19  
 *  
 */
 
public class TextMessage extends BaseMessage {  
	    // 回复的消息内容  
	    private String Content;  
	  
	    public String getContent() {  
	        return Content;  
	    }  
	  
	    public void setContent(String content) {  
	        Content = content;  
	    }  
}  

