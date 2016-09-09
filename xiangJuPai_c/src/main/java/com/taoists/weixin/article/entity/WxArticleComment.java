package com.taoists.weixin.article.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.taoists.common.Const;
import com.taoists.common.orm.entity.BaseEntity;
import com.taoists.user.entity.User;

@SuppressWarnings("serial")
@Entity
@Table(name="wx_article_comment")
public class WxArticleComment extends BaseEntity{

	@Column(name = "user_name")
    private String userName = Const.NULL_VALUE_STRING;
    @Column(name = "content")
    private String content;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "article_msg_id")
    private WeixinArticle weixinArticle;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public WeixinArticle getWeixinArticle() {
		return weixinArticle;
	}

	public void setWeixinArticle(WeixinArticle weixinArticle) {
		this.weixinArticle = weixinArticle;
	}
    
    
}
