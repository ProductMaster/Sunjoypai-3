package com.taoists.weixin.article.service.impl; 

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taoists.common.Const;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.sys.dataDict.entity.DataDict;
import com.taoists.sys.dataDict.service.DataDictService;
import com.taoists.weixin.article.entity.WeixinArticle;
import com.taoists.weixin.article.entity.WeixinArticle_WeixinArticle;
import com.taoists.weixin.article.service.WeixinArticleService;
import com.taoists.weixin.article.service.Weixin_WeixinService;


/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-11-21 下午03:34:28  
 *  
 */
@Service("weixinArticleService")
public class WeixinArticleServiceImpl extends HibernateDaoSupport<WeixinArticle> implements WeixinArticleService{
	@Autowired
	protected Weixin_WeixinService weixin_WeixinService;

	@Override
	public List<WeixinArticle> findAll(long id){
		Criteria criteria = createDetachedCriteria().getExecutableCriteria(
				getSession());
		criteria.add(Restrictions.eq("isUrl", Const.FALSE));
		criteria.add(Restrictions.eq("articleCategory.id", id));
		return (List<WeixinArticle>) criteria.list();
	}
	
	@Transactional
	public void update(WeixinArticle articleMsg,String[] ids){
		update(articleMsg);
		long msgId=articleMsg.getId();
		for(WeixinArticle_WeixinArticle ww:weixin_WeixinService.findDatas("msgId", msgId)){
			weixin_WeixinService.delete(ww);
		}
		if(ids!=null)
			for(String id:ids){
				WeixinArticle_WeixinArticle ww = new WeixinArticle_WeixinArticle();
				ww.setMsgId(msgId);
				ww.setListId(Long.parseLong(id));
				weixin_WeixinService.save(ww);
			}
	}

	@Override
	@Transactional
	public void saveWeixinArticle(HttpServletRequest request,WeixinArticle weixinArticle) {
		String[] titles=request.getParameterValues("title_m");
		String[] picUrls=request.getParameterValues("picUrl_m");
		String[] descriptions=request.getParameterValues("description_m");
		String[] urls=request.getParameterValues("url_m");
		String[] contents=request.getParameterValues("content_m");
		String articleCategoryId = request.getParameter("articleCategoryId");
		DataDict articleCategory = null;
		if(articleCategoryId!=null&&!"".equals(articleCategoryId))
			articleCategory = dataDictService.get(Long.parseLong(articleCategoryId));
		else
			articleCategory = dataDictService.get(Const.NULL_VALUE_LONG);
		weixinArticle.setArticleCategory(articleCategory);
		save(weixinArticle);
		String url=weixinArticle.getUrl()+weixinArticle.getId();
		weixinArticle.setUrl(url);
		update(weixinArticle);
		long msgId=weixinArticle.getId();
		if(titles!=null){
			for(int i=0;i<titles.length;i++){
				WeixinArticle article = new WeixinArticle();
				article.setTitle(titles[i]);
				article.setPicUrl(picUrls[i]);
				article.setDescription(descriptions[i]);
				article.setUrl(urls[i]);
				article.setContent(contents[i]);
				article.setArticleCategory(articleCategory);
				
				save(article);
				article.setUrl(article.getUrl()+article.getId());
				update(article);
					
				WeixinArticle_WeixinArticle w_w=new WeixinArticle_WeixinArticle();
				w_w.setMsgId(msgId);
				w_w.setListId(article.getId());
				weixin_WeixinService.save(w_w);
			}
		}
	}
	
	@Override
	@Transactional
	public void addMArticleMsgMenu(HttpServletRequest request,WeixinArticle weixinArticle) {
		String[] titles=request.getParameterValues("title_m");
		String[] picUrls=request.getParameterValues("picUrl_m");
		String[] descriptions=request.getParameterValues("description_m");
		String[] urls=request.getParameterValues("url_m");
		String articleCategoryId = request.getParameter("articleCategoryId");
		DataDict articleCategory = null;
		if(articleCategoryId!=null&&!"".equals(articleCategoryId))
			articleCategory = dataDictService.get(Long.parseLong(articleCategoryId));
		else
			articleCategory = dataDictService.get(Const.NULL_VALUE_LONG);
		weixinArticle.setArticleCategory(articleCategory);
		save(weixinArticle);
		
		long msgId=weixinArticle.getId();
		if(titles!=null){
			for(int i=0;i<titles.length;i++){
				WeixinArticle article = new WeixinArticle();
				article.setTitle(titles[i]);
				article.setPicUrl(picUrls[i]);
				article.setDescription(descriptions[i]);
				article.setUrl(urls[i]);
				article.setArticleCategory(articleCategory);
				
				save(article);
					
				WeixinArticle_WeixinArticle w_w=new WeixinArticle_WeixinArticle();
				w_w.setMsgId(msgId);
				w_w.setListId(article.getId());
				weixin_WeixinService.save(w_w);
			}
		}
	}
	
	@Override
	@Transactional
	public void deleteArticleMsg(long id) {
		List<WeixinArticle_WeixinArticle> list = weixin_WeixinService.findDatas("msgId", id);
		for(WeixinArticle_WeixinArticle w:list){
			weixin_WeixinService.delete(w.getId());
			delete(w.getListId());
		}
		delete(id);
	}
	
	@Resource
	private DataDictService dataDictService;
}
