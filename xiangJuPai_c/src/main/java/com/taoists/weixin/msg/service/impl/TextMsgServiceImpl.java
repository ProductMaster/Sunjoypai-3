package com.taoists.weixin.msg.service.impl;

import org.springframework.stereotype.Service;

import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.weixin.msg.entity.TextMsg;
import com.taoists.weixin.msg.service.TextMsgService;


/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-11-21 下午03:49:39  
 *  
 */
@Service("textMsgService")
public class TextMsgServiceImpl extends HibernateDaoSupport<TextMsg> implements TextMsgService{

}
