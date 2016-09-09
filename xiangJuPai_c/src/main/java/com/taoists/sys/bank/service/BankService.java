 
package com.taoists.sys.bank.service;

import java.util.List;

import com.taoists.common.orm.dao.BaseDao;
import com.taoists.sys.bank.entity.SysBank;
 
public interface BankService extends BaseDao<SysBank> {

	
	public List<SysBank> findAllPayMethod();
	public List<SysBank> findAllBank();
	public SysBank getBankById(long id);
	public SysBank getBankByBankName(String bankName);
}
