package com.taoists.weixin.article.entity; 

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.taoists.common.Const;
import com.taoists.common.orm.entity.BaseEntity;
import com.taoists.sys.dataDict.entity.DataDict;

/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-11-15 下午06:29:18  
 * 微信图文回复 
 */
@SuppressWarnings("serial")
@Entity
@Table(name="weixin_article_msg")
public class WeixinArticle extends BaseEntity{  
    
	// 图文消息名称  
    private String title;  
    // 图文消息描述  
    private String description;  
    // 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致  
    private String picUrl;  
    // 点击图文消息跳转链接  
    private String url;  
    //正文
    @Column(name = "content", length = 16777216)
    private String content;
    //关键字
    @Column(name="key_value")
    private String key = Const.NULL_VALUE_STRING;
    
    @Column(name = "is_url")
    private int isUrl = Const.FALSE;
    
    @ManyToMany
    @JoinColumn(name="article_id",nullable=true)
    List<WeixinArticle> list;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private DataDict articleCategory;
    
	public String getTitle() {  
        return title;  
    }  
  
    public void setTitle(String title) {  
        this.title = title;  
    }  
  
    public String getDescription() {  
        return null == description ? "" : description;  
    }  
  
    public void setDescription(String description) {  
        this.description = description;  
    }  
  
    public String getPicUrl() {  
        return null == picUrl ? "" : picUrl;  
    }  
  
    public void setPicUrl(String picUrl) {  
        this.picUrl = picUrl;  
    }  
  
    public String getUrl() {  
        return null == url ? "" : url;  
    }  
  
    public void setUrl(String url) {  
        this.url = url;  
    }

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<WeixinArticle> getList() {
		return list;
	}

	public void setList(List<WeixinArticle> list) {
		this.list = list;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIsUrl() {
		return isUrl;
	}

	public void setIsUrl(int isUrl) {
		this.isUrl = isUrl;
	}

	public DataDict getArticleCategory() {
		return articleCategory;
	}

	public void setArticleCategory(DataDict articleCategory) {
		this.articleCategory = articleCategory;
	}  
	
}  