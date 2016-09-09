package com.taoists.weixin.article.service.impl;

import org.springframework.stereotype.Service;

import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.weixin.article.entity.WxArticleComment;
import com.taoists.weixin.article.service.WxArticleCommentService;

@Service("wxArticleCommentService")
public class WxArticleCommentServiceImpl extends HibernateDaoSupport<WxArticleComment> implements WxArticleCommentService {

}
