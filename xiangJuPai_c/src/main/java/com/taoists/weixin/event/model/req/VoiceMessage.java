package com.taoists.weixin.event.model.req; 
/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-11-15 下午06:13:04  
 *  
 */ 
public class VoiceMessage extends BaseMessage {  
    // 媒体ID  
    private String MediaId;  
    // 语音格式  
    private String Format;  
  
    public String getMediaId() {  
        return MediaId;  
    }  
  
    public void setMediaId(String mediaId) {  
        MediaId = mediaId;  
    }  
  
    public String getFormat() {  
        return Format;  
    }  
  
    public void setFormat(String format) {  
        Format = format;  
    }  
}  
