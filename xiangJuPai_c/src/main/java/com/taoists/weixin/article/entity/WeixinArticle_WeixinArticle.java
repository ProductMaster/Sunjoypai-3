package com.taoists.weixin.article.entity; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.taoists.common.orm.entity.BaseEntity;

/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-12-13 下午04:20:18  
 *  
 */
@Entity
@Table(name="weixin_article_msg_weixin_article_msg")
@SuppressWarnings("serial")
public class WeixinArticle_WeixinArticle extends BaseEntity{
	
	@Column(name="weixin_article_msg_id")
	private long msgId;
	@Column(name="list_id")
	private long listId;
	
	public long getMsgId() {
		return msgId;
	}
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}
	public long getListId() {
		return listId;
	}
	public void setListId(long listId) {
		this.listId = listId;
	}
	
}
