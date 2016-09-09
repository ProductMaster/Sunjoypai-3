package com.taoists.weixin.msg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.taoists.common.orm.entity.BaseEntity;

/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-11-15 下午06:10:19  
 * 微信文本回复
 */
@SuppressWarnings("serial")
@Entity
@Table(name="text_msg")
public class TextMsg extends BaseEntity {  
	
	// 消息内容  
	@Column(name="content")
    private String content;  
    //关键字
	@Column(name="key_value")
    private String key;	

	public String getContent() {  
        return content;  
    }  
  
    public void setContent(String content) {  
        this.content = content;  
    }

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}  
}  

