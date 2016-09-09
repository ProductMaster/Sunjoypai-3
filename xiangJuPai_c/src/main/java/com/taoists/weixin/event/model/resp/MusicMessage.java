package com.taoists.weixin.event.model.resp; 

 
/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-11-15 下午06:15:50  
 *  
 */
public class MusicMessage extends BaseMessage {  
    // 音乐  
    private Music Music;  
  
    public Music getMusic() {  
        return Music;  
    }  
  
    public void setMusic(Music music) {  
        Music = music;  
    }  
}  