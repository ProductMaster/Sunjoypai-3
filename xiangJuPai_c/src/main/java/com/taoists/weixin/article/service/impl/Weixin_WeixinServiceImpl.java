package com.taoists.weixin.article.service.impl; 

import org.springframework.stereotype.Service;

import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.weixin.article.entity.WeixinArticle_WeixinArticle;
import com.taoists.weixin.article.service.Weixin_WeixinService;

/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-12-13 下午04:28:41  
 *  
 */
@Service("weixin_WeixinService")
public class Weixin_WeixinServiceImpl extends HibernateDaoSupport<WeixinArticle_WeixinArticle> implements Weixin_WeixinService{

}
