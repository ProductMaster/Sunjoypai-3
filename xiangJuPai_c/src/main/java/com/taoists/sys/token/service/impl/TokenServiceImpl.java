package com.taoists.sys.token.service.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.common.util.DateUtils;
import com.taoists.sys.token.entity.Token;
import com.taoists.sys.token.service.TokenService;

@Service("tokenService")
public class TokenServiceImpl extends HibernateDaoSupport<Token> implements TokenService{

    public Token getTokenByEmail(String email){
        
        Token token = null;
        DetachedCriteria detachedCriteria = createDetachedCriteria();
        detachedCriteria.add(Restrictions.eq("email", email));
        List<Token> TokenList = detachedCriteria.getExecutableCriteria(getSession()).list();
        if(TokenList.size()>0) return TokenList.get(0);
        return token;
    }
    
    public Token getTokenByEmailAndTokenAndDate(String email, String tokenStr, long endDate){
        
        Token token = null;
        DetachedCriteria detachedCriteria = createDetachedCriteria();
        detachedCriteria.add(Restrictions.eq("email", email));
        detachedCriteria.add(Restrictions.eq("token", tokenStr));
        detachedCriteria.add(Restrictions.ge("endDate", endDate));
        List<Token> TokenList = detachedCriteria.getExecutableCriteria(getSession()).list();
        if(TokenList.size()>0) return TokenList.get(0);
        return token;
    }
    
    
    public Token getTokenByMobile(String mobile){
        
        Token token = null;
        DetachedCriteria detachedCriteria = createDetachedCriteria();
        detachedCriteria.add(Restrictions.eq("mobile", mobile));
        List<Token> TokenList = detachedCriteria.getExecutableCriteria(getSession()).list();
        if(TokenList.size()>0) return TokenList.get(0);
        return token;
    }
    
    public Token getTokenByMobileAndTokenAndDate(String mobile, String tokenStr, long endDate){
        
        Token token = null;
        DetachedCriteria detachedCriteria = createDetachedCriteria();
        detachedCriteria.add(Restrictions.eq("mobile", mobile));
        detachedCriteria.add(Restrictions.eq("token", tokenStr));
        detachedCriteria.add(Restrictions.ge("endDate", endDate));
        List<Token> TokenList = detachedCriteria.getExecutableCriteria(getSession()).list();
        if(TokenList.size()>0) return TokenList.get(0);
        return token;
    }
    
    public Token saveOrUpdateTokenByEmail(String email){
        
        Token token = getTokenByEmail(email);
        
        if (token != null) {// token已经存在
            token.setEndDate(System.currentTimeMillis() + DateUtils.getMillsForDay(Token.Active_Day));
            token.setToken(UUID.randomUUID().toString());
            saveOrUpdate(token);// 更新token并延长有效期
        } else {
            token = new Token(email, Token.TYPE_EMAIL);
            save(token);// 保存token
        }
        return  token;
    }
    
    public Token saveOrUpdateTokenByMobile(String mobile){
        
        Token token = getTokenByMobile(mobile);
        
        if (token != null) {// token已经存在
            token.setEndDate(System.currentTimeMillis() + DateUtils.getMillsForDay(Token.Active_Day));
            token.setToken(Long.toString(Math.round(Math.random()*8999+1000)));
            saveOrUpdate(token);// 更新token并延长有效期
        } else {
            token = new Token(mobile, Token.TYPE_MOBILE);
            save(token);// 保存token
        }
        return  token;
    }
}
