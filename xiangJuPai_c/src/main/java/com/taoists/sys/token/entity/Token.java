package com.taoists.sys.token.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.taoists.common.Const;
import com.taoists.common.orm.entity.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name="sys_token")
public class Token extends BaseEntity{
    private static final long serialVersionUID = 1L;
    public static final int Active_Day = 1;//1天有效

    public static final int TYPE_EMAIL  = 1;
    public static final int TYPE_MOBILE = 2;
    
/*    @Id
    @GeneratedValue(generator="identity")
    @GenericGenerator(name="identity",strategy="identity")
    @Column(name="token_id")
    private long tokenId;*/
    private String token  = Const.NULL_VALUE_STRING;
    private String email  = Const.NULL_VALUE_STRING;
    private String mobile = Const.NULL_VALUE_STRING;
    @Column(name = "end_date")
    private long endDate;

    public Token(){}
    
    public Token(String channel, int type){
        this.endDate = System.currentTimeMillis() + 86400000 * Active_Day;
        this.token = Long.toString(Math.round(Math.random()*8999+1000));
        if(type == TYPE_EMAIL){ this.email = channel; }            
        if(type == TYPE_MOBILE){ this.mobile = channel; }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }
}
