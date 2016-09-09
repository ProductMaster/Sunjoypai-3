package com.taoists.weixin.article.service; 

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.taoists.common.orm.dao.BaseDao;
import com.taoists.weixin.article.entity.WeixinArticle;

/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-11-21 上午11:28:57  
 *  
 */
public interface WeixinArticleService extends BaseDao<WeixinArticle>{
	public void update(WeixinArticle articleMsg,String[] ids);

	public void saveWeixinArticle(HttpServletRequest request,WeixinArticle weixinArticle);
	
	public void deleteArticleMsg(long id);

	public List<WeixinArticle> findAll(long id);

	public void addMArticleMsgMenu(HttpServletRequest request,
			WeixinArticle weixinArticle);
}
