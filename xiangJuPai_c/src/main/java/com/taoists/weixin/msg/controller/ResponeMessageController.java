package com.taoists.weixin.msg.controller;

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
import com.taoists.Module;
import com.taoists.common.Const;
import com.taoists.common.bean.Page;
import com.taoists.common.orm.PropertyFilter;
import com.taoists.weixin.article.entity.WeixinArticle;
import com.taoists.weixin.msg.entity.TextMsg;
import com.taoists.sys.dataDict.entity.DataDict;
import com.taoists.sys.dataDict.entity.DictCategory;

/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-11-21 上午11:23:17  
 *  
 */
@Controller
@RequestMapping("weixin/msg")
public class ResponeMessageController extends CommonProjectController{

	@Override
	protected String getModule() {
		return Module.sys.getName();
	}

	@RequestMapping("/listTextMsg")
	public String listTextMsg(HttpServletRequest request, Page page){
		List<PropertyFilter> filters = PropertyFilter
		.buildFromHttpRequest(request);
		textMsgService.findPage(page, filters);
		extractParams(request);
		return "weixin/msg/listTextMsg";
	}
	
	@RequestMapping("/newTextMsg")
	public String newTextMsg(HttpServletRequest request) {
		logger.debug("newTextMsg");
		return "weixin/msg/newTextMsg";
	}
	
	@RequestMapping(value = "/addTextMsg")
	public String addTextMsg(TextMsg textMsg,HttpServletRequest request) {
		logger.debug("add: TextMsg[{}]");
		textMsgService.clear();
		textMsgService.saveOrUpdate(textMsg);
		return "redirect:/weixin/msg/listTextMsg";
	}
	@RequestMapping("/editTextMsg/{id}")
	public String editTextMsg(@PathVariable("id") long id,
			HttpServletRequest request) {
		logger.debug("edit: id[{}]", id);
		return "weixin/msg/editTextMsg";
	}
	@RequestMapping(value = "/updateTextMsg/{textMsg.id}", method = RequestMethod.POST)
	public String updateTextMsg(TextMsg textMsg,HttpServletRequest request) {
		logger.debug("update: TextMsg[{}]", textMsg);
		textMsgService.clear();
		textMsgService.saveOrUpdate(textMsg);
		return "redirect:/weixin/msg/listTextMsg";
	}
	@RequestMapping("/destroyTextMsg/{id}")
	public String destroyTextMsg(@PathVariable long id,HttpServletRequest request) {
		logger.debug("remove: id[{}]", id);
		textMsgService.delete(id);
		return "redirect:/weixin/msg/listTextMsg";
	}
	
	/**
	 * ArticleRespMsg
	 * @param request
	 * @return
	 */
	/*@RequestMapping("/listArticleMsg")
	public String listArticleMsg(HttpServletRequest request, Page page){
		List<PropertyFilter> filters = PropertyFilter
		.buildFromHttpRequest(request);
		weixinArticleService.findPage(page, filters);
		extractParams(request);
		return "/weixin/msg/listArticleMsg";
	}
	
	@RequestMapping("/newArticleMsg")
	public String newArticleMsg(HttpServletRequest request) {
		logger.debug("newArticleMsg");
		List<DataDict> articleCategoryList = dataDictService.findDataDictByCategoryId(DictCategory.DICT_CATEGORY_ARTICLE_CATEGORY);
		request.setAttribute("articleCategoryList", articleCategoryList);
		return "/weixin/msg/newArticleMsg";
	}
	
	@RequestMapping(value = "/addArticleMsg")
	public String addArticleMsg(WeixinArticle weixinArticle,HttpServletRequest request) {
		logger.debug("add: ArticleMsg[{}]");
		weixinArticleService.clear();
		int nextViewType = Integer.parseInt(request.getParameter("nextViewType"));
		weixinArticle.setIsUrl(nextViewType);
		weixinArticleService.save(weixinArticle);
		if(nextViewType == Const.FALSE){
			weixinArticle.setUrl(weixinArticle.getUrl()+weixinArticle.getId());
			weixinArticleService.update(weixinArticle);
		}
		return "redirect:/weixin/msg/listArticleMsg";
	}
	
	@RequestMapping("/newMArticleMsg")
	public String newMArticleMsg(HttpServletRequest request) {
		logger.debug("newArticleMsg");
		List<DataDict> articleCategoryList = dataDictService.findDataDictByCategoryId(DictCategory.DICT_CATEGORY_ARTICLE_CATEGORY);
		request.setAttribute("articleCategoryList", articleCategoryList);
		return "/weixin/msg/newMArticleMsg";
	}
	
	@RequestMapping(value = "/addMArticleMsg")
	public String addMArticleMsg(WeixinArticle weixinArticle,HttpServletRequest request) {
		logger.debug("add: ArticleMsg[{}]");
		weixinArticleService.clear();
		weixinArticleService.saveWeixinArticle(request,weixinArticle);
		return "redirect:/weixin/msg/listArticleMsg";
	}
	
	@RequestMapping("/newMArticleMsgMenu")
	public String newMArticleMsgMenu(HttpServletRequest request) {
		logger.debug("newArticleMsg");
		List<DataDict> articleCategoryList = dataDictService.findDataDictByCategoryId(DictCategory.DICT_CATEGORY_ARTICLE_CATEGORY);
		request.setAttribute("articleCategoryList", articleCategoryList);
		return "/weixin/msg/newMArticleMsgMenu";
	}
	
	@RequestMapping(value = "/addMArticleMsgMenu")
	public String addMArticleMsgMenu(WeixinArticle weixinArticle,HttpServletRequest request) {
		logger.debug("add: ArticleMsg[{}]");
		weixinArticleService.clear();
		weixinArticleService.addMArticleMsgMenu(request,weixinArticle);
		return "redirect:/weixin/msg/listArticleMsg";
	}
	
	@RequestMapping("/showArticleMsg/{id}")
	public String showArticleMsg(@PathVariable Long id,HttpServletRequest request) {
		logger.debug("showArticleMsg");
		String weixinId = request.getParameter("weixinId");
		if(weixinId !=null)
			request.getSession().setAttribute("weixinId", weixinId);
		WeixinArticle articleMsg=weixinArticleService.get(id);
		String userId=request.getParameter("recommender");
		request.setAttribute("recommendUserName", userId);
		request.setAttribute("articleMsg", articleMsg);
		return "/weixin/msg/showArticleMsg";
	}
	@RequestMapping("/editArticleMsg/{id}")
	public String editArticleMsg(@PathVariable("id") long id,
			HttpServletRequest request) {
		List<WeixinArticle> allArticleMsgs=weixinArticleService.findAll();
		allArticleMsgs.remove(weixinArticleService.get(id));
		List<WeixinArticle> articleMsgs=weixinArticleService.get(id).getList();
		Map<WeixinArticle,Boolean> articleMsgMap=new HashMap<WeixinArticle, Boolean>();
		for(WeixinArticle msg:allArticleMsgs){
			articleMsgMap.put(msg , articleMsgs.contains(msg));
		}
		request.setAttribute("msgMap", articleMsgMap);
		logger.debug("edit: id[{}]", id);
		int viewType;
		if(!weixinArticleService.get(id).getUrl().contains("respMsg/showArticleMsg"))
			viewType=1;
		else
			viewType=0;
		request.setAttribute("viewType", viewType);
		return forward("editArticleMsg");
	}
	@RequestMapping(value = "/updateArticleMsg/{id}", method = RequestMethod.POST)
	public String updateArticleMsg(@ModelAttribute("articleMsg") WeixinArticle articleMsg,HttpServletRequest request) {
		logger.debug("update: WeixinArticle[{}]", articleMsg);
		weixinArticleService.clear();
		weixinArticleService.update(articleMsg);
		return "redirect:/respMsg/listArticleMsg";
	}
	
	@RequestMapping("/destroyArticleMsg/{id}")
	public String destroyArticleMsg(@PathVariable long id,HttpServletRequest request) {
		logger.debug("remove: id[{}]", id);
		try{
		weixinArticleService.deleteArticleMsg(id);
		}catch (Exception e) {
			request.setAttribute("errorMsg", "有文图消息关联该图文，请先删除关联！");
		}
		return "redirect:/respMsg/listArticleMsg";
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
		return "sys/respMsg/articleMsgList";
	}
	*//** 文章 *//*
	@RequestMapping("/listArticle/{id}")
	public String listArticle(HttpServletRequest request,@PathVariable long id){
		request.setAttribute("articles", weixinArticleService.findAll(id));
		request.setAttribute("weixinId", request.getParameter("weixinId"));
		DataDict articleCategory = dataDictService.get(id);
		request.setAttribute("title", articleCategory!=null?articleCategory.getTitle():"");
		return "sys/respMsg/listArticle";
	}
	@RequestMapping("/showArticle/{id}")
	public String showArticle(@PathVariable Long id,HttpServletRequest request) {
		logger.debug("showArticleMsg");
		WeixinArticle articleMsg=weixinArticleService.get(id);
		request.setAttribute("article", articleMsg);
		request.setAttribute("weixinId", request.getParameter("weixinId"));
		return "sys/respMsg/showArticle";
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
	@ModelAttribute("textMsg")
	public TextMsg getTextMsg(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		Long id = extractId(requestURI);
		logger.debug("getTextMsg: request.getRequestURI[{}], id[{}]",
				requestURI, id);

		if (id == null) {
			return new TextMsg();
		}
		return textMsgService.get(id);
	}
}
