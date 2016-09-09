package com.taoists.sys.passport.service.impl;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.common.util.DateUtils;
import com.taoists.sys.passport.entity.PassportLoginRecord;
import com.taoists.sys.passport.service.PassportLoginRecordService;

@Service("passportLoginRecordService")
public class PassportLoginRecordServiceImpl extends HibernateDaoSupport<PassportLoginRecord> implements PassportLoginRecordService{

	@Override
	public int findLoginFailureCount(String ip) {
		String time = DateUtils.long2ShortString(System.currentTimeMillis());
		long startTime = DateUtils.string2Date(time).getTime();
		long endTime = DateUtils.string2Date(time).getTime();
		return ((Long)createCriteria(
				Restrictions.between("loginDate", startTime, endTime),
				Restrictions.eq("status", PassportLoginRecord.LOGIN_STATUS_FAILURE)
 		).createAlias("passportLoginIp", "pl").add(Restrictions.eq("pl.loginIp", ip)).setProjection(Projections.count("id")).uniqueResult()).intValue();
	}
	
}
