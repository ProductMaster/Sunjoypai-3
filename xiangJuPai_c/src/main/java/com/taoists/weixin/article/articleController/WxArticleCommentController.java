package com.taoists.weixin.article.articleController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taoists.CommonProjectController;
import com.taoists.user.entity.User;
import com.taoists.weixin.article.entity.WeixinArticle;
import com.taoists.weixin.article.entity.WxArticleComment;

@Controller
@RequestMapping("/weixin/article")
public class WxArticleCommentController extends CommonProjectController{

	/*@RequestMapping(value = "/addArticleComment")
	public String addArticleComment(HttpSession session, HttpServletRequest request, WxArticleComment comment) {
		
		User user = (User) session.getAttribute("currentUser");
		String weixinId = session.getAttribute("weixinId").toString();
		WeixinArticle article = weixinArticleService.get(comment.getWeixinArticle().getId());
		comment.setWeixinArticle(article);
		comment.setUser(user);
		comment.setUserName(user.getUserName());
		wxArticleCommentService.save(comment);
		return "redirect:/weixin/article/showArticle/"+article.getId()+"?weixinId="+weixinId;
	}*/
	
	@Override
	protected String getModule() {
		// TODO Auto-generated method stub
		return null;
	}

}
