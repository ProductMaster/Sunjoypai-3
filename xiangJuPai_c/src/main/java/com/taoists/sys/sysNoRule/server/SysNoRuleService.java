package com.taoists.sys.sysNoRule.server; 

import com.taoists.common.orm.dao.BaseDao;
import com.taoists.sys.sysNoRule.entity.SysNoRule;

public interface SysNoRuleService extends BaseDao<SysNoRule> {

    //返回完整编号，列如:GB2009010100001(前缀+日期+流水号)
    public String getNextNoSerialFullNo(long noRuleId);
}
