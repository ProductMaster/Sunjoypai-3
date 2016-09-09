package com.taoists.weixin.article.articleController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taoists.CommonProjectController;
import com.taoists.common.Const;
import com.taoists.common.bean.Page;
import com.taoists.common.orm.PropertyFilter;
import com.taoists.sys.dataDict.entity.DataDict;
import com.taoists.sys.dataDict.entity.DictCategory;
import com.taoists.weixin.article.entity.WeixinArticle;

@Controller
@RequestMapping("/weixin/article")
public class ArticleController  extends CommonProjectController{


	
	/*@RequestMapping("/showArticleMsg/{id}")
	public String showArticleMsg(@PathVariable Long id,HttpServletRequest request) {
		logger.debug("showArticleMsg");
		String weixinId = request.getParameter("weixinId");
		if(weixinId !=null)
			request.getSession().setAttribute("weixinId", weixinId);
		WeixinArticle articleMsg=weixinArticleService.get(id);
		String userId=request.getParameter("recommender");
		request.setAttribute("recommendUserName", userId);
		request.setAttribute("articleMsg", articleMsg);
		return "/weixin/article/showArticleMsg";
	}


	*//** 多图文选择列表 *//*
	@RequestMapping("/articleMsgList/{id}")
	public String articleMsgList(@PathVariable("id") long id,HttpServletRequest request,Page page){
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		List<WeixinArticle> allArticleMsgs=weixinArticleService.findPage(page, filters);
		allArticleMsgs.remove(weixinArticleService.get(id));
		List<WeixinArticle> articleMsgs=weixinArticleService.get(id).getList();
		Map<WeixinArticle,Boolean> articleMsgMap=new HashMap<WeixinArticle, Boolean>();
		for(WeixinArticle msg:allArticleMsgs){
			articleMsgMap.put(msg , articleMsgs.contains(msg));
		}
		request.setAttribute("msgMap", articleMsgMap);
		request.setAttribute("id", id);
		return "/weixin/article/articleMsgList";
	}
	*//** 文章 *//*
	@RequestMapping("/listArticle/{id}")
	public String listArticle(HttpServletRequest request,@PathVariable long id){
		request.setAttribute("articles", weixinArticleService.findAll(id));
		request.setAttribute("weixinId", request.getParameter("weixinId"));
		DataDict articleCategory = dataDictService.get(id);
		request.setAttribute("title", articleCategory!=null?articleCategory.getTitle():"");
		return "/weixin/article/listArticle";
	}
	@RequestMapping("/showArticle/{id}")
	public String showArticle(@PathVariable Long id,HttpServletRequest request) {
		logger.debug("showArticleMsg");
		WeixinArticle articleMsg=weixinArticleService.get(id);
		request.setAttribute("article", articleMsg);
		request.setAttribute("weixinId", request.getParameter("weixinId"));
		return "/weixin/article/showArticle";
	}
	*//*****************************************************************//*
	@ModelAttribute("articleMsg")
	public WeixinArticle getArticleMsg(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		Long id = extractId(requestURI);
		logger.debug("getArticleMsg: request.getRequestURI[{}], id[{}]",
				requestURI, id);

		if (id == null) {
			return new WeixinArticle();
		}
		return weixinArticleService.get(id);
	}*/
	
	
	
	
	@Override
	protected String getModule() {
		// TODO Auto-generated method stub
		return null;
	}

}
