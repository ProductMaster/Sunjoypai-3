package com.taoists.sys.passport.service.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.sys.passport.entity.PassportLoginIp;
import com.taoists.sys.passport.service.PassportLoginIpService;

@Service("passportLoginIpService")
public class PassportLoginIpServiceImpl extends HibernateDaoSupport<PassportLoginIp> implements PassportLoginIpService{

	@Override
	public PassportLoginIp getPassportLoginIpByIp(String ip) {
		List<PassportLoginIp> ipList = createCriteria(
				Restrictions.eq("loginIp", ip)
				
		).list();
		return ipList.size() > 0 ? ipList.get(0) : null;
	}

}
