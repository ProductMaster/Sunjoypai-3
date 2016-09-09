package com.taoists.sys.token.service;


import com.taoists.common.orm.dao.BaseDao;
import com.taoists.sys.token.entity.Token;

public interface TokenService  extends BaseDao<Token>{

    public Token getTokenByEmail(String email);
    public Token getTokenByEmailAndTokenAndDate(String email, String tokenStr, long endDate);
    public Token getTokenByMobile(String mobile);
    public Token getTokenByMobileAndTokenAndDate(String mobile, String tokenStr, long endDate);
    public Token saveOrUpdateTokenByEmail(String email);
    public Token saveOrUpdateTokenByMobile(String mobile);
}
