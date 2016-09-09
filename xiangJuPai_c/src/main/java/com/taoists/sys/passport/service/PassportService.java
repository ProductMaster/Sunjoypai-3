package com.taoists.sys.passport.service;

 
import com.taoists.common.orm.dao.BaseDao;
import com.taoists.sys.passport.entity.Passport;


public interface PassportService extends BaseDao<Passport>{
	
	//	-----------公司员工操作员信息--------------------------------	
	public Passport getSignPassport(String loginName, String passwd, int userType,String ip);
	public void savePassportLoginRecord(String loginName,int userType,String ip,int loginStatus);
	public Passport getLoginPassport(String loginName, String passwd,int userType, String ip) ;
	public Passport getPassportByCustomerId(long id);
	public Passport getPassByLoginName(String loginName);
 
}

