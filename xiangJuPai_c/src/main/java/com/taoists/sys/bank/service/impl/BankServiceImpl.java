 
package com.taoists.sys.bank.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.taoists.common.Const;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.sys.bank.entity.SysBank;
import com.taoists.sys.bank.service.BankService;
 @Service("bankService")
public class BankServiceImpl  extends HibernateDaoSupport<SysBank> implements BankService{

	 
	public List<SysBank> findAllPayMethod() {
		Criteria criteria = createDetachedCriteria().getExecutableCriteria(
				getSession());
		criteria.add(Restrictions.ne("id", Const.NULL_VALUE_LONG));
		criteria.add(Restrictions.eq("status", Const.TRUE));
		return criteria.list();
	}
	
	public List<SysBank> findAllBank() {
		Criteria criteria = createDetachedCriteria().getExecutableCriteria(
				getSession());
		criteria.add(Restrictions.gt("id", Const.ZERO_VALUE_LONG));
		return criteria.list();
	}

	 
	public SysBank getBankById(long id) {
		Criteria criteria = createDetachedCriteria().getExecutableCriteria(
				getSession());
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("status", Const.TRUE));
		return (SysBank)criteria.list().get(0);
 
	}
	
	public SysBank getBankByBankName(String bankName) {
		Criteria criteria = createDetachedCriteria().getExecutableCriteria(
				getSession());
		criteria.add(Restrictions.like("bankName", bankName));
		criteria.add(Restrictions.eq("status", Const.TRUE));
		return (SysBank)criteria.list().get(0);
 
	}

}
