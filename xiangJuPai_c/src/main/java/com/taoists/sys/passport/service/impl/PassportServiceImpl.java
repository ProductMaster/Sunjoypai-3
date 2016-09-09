package com.taoists.sys.passport.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taoists.common.Const;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.sys.passport.entity.Passport;
import com.taoists.sys.passport.entity.PassportLoginIp;
import com.taoists.sys.passport.entity.PassportLoginRecord;
import com.taoists.sys.passport.service.PassportLoginIpService;
import com.taoists.sys.passport.service.PassportLoginRecordService;
import com.taoists.sys.passport.service.PassportService;

@SuppressWarnings("unchecked")
@Service("pssposrtService")
public class PassportServiceImpl extends HibernateDaoSupport<Passport> implements PassportService {
	@Autowired
	private PassportLoginIpService passportLoginIpService;
	@Autowired
	private PassportLoginRecordService passportLoginRecordService;
	
 	/** 经销商登陆，查询用户信息 **/
	public Passport getSignPassport(String loginName, String passwd, int userType, String ip) {
		Passport passport = this.getPassByLoginName(loginName, userType);
		return passport;
	}
	
 
	
	/** 供应商登陆，查询用户信息 **/
	public Passport getLoginPassport(String loginName, String passwd,int userType, String ip) {
		Passport passport = this.getPassByLoginName(loginName, userType);
		return passport;
	}

	/** 通过loginName查找Passport的数据 * */

	private Passport getPassByLoginName(String loginName, int userType) {
		Passport passport = null;
		List list = createCriteria(Restrictions.eq("loginName", loginName),
//				Restrictions.eq("passType", userType),
				Restrictions.eq("recordStatus", Passport.RECORD_STATUS_UNLIMITED)).list();

		if (list.size() > 0) {
			passport = (Passport) list.iterator().next();
			
			//int num=passport.getCustomer().getIsMedia();
 		}
		return passport;
	}
	
	public Passport getPassByLoginName(String loginName) {
		Passport passport = null;
		List list = createCriteria(Restrictions.eq("loginName", loginName)).list();
		if (list.size() > 0) {
			passport = (Passport) list.iterator().next();
 		}
		return passport;
	}

	@Transactional
	public void savePassportLoginRecord(String loginName, int userType,
			String ip, int loginStatus) {
		Passport passport = this.getPassByLoginName(loginName, userType);
		PassportLoginIp loginIp = passportLoginIpService.getPassportLoginIpByIp(ip);
		int failureCount = passportLoginRecordService.findLoginFailureCount(ip);
		if (loginIp == null || failureCount < loginIp.getFailureCount()) {
			this.savePassportLoginRecord(passport, ip, loginStatus);
		} else {
			this.updatePassportLoginIp(ip);
		}
	}

	@Transactional
	private void savePassportLoginRecord(Passport pass, String ip,
			int loginStatus) {
		PassportLoginIp loginIp = passportLoginIpService.getPassportLoginIpByIp(ip);
		if (loginIp == null) {
			loginIp = new PassportLoginIp();
			loginIp.setLoginIp(ip);
			this.getSession().save(loginIp);
 		}
		PassportLoginRecord r = new PassportLoginRecord();
		r.setLoginDate(System.currentTimeMillis());
		r.setLoginName(pass.getLoginName());
		r.setNickname(pass.getNickname());
		r.setPassId(pass.getId());
		r.setPassportLoginIp(loginIp);
		r.setStatus(loginStatus);
		this.getSession().save(r);
		
	}
	@Transactional
	private void updatePassportLoginIp(String ip) {
		PassportLoginIp loginIp = passportLoginIpService.getPassportLoginIpByIp(ip);
		loginIp.setStatus(PassportLoginIp.IP_STATUS_CLOSE);
		this.getSession().update(loginIp);
		
	}

	@Override
	public Passport getPassportByCustomerId(long id) {
		Criteria criteria = createDetachedCriteria().getExecutableCriteria(
				getSession());
		criteria.add(Restrictions.eq("customer.id", id));
 		return (Passport)criteria.list().get(0);
	}

}
