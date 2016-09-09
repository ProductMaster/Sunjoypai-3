package com.taoists.appoint.service;

import java.util.List;

import com.taoists.appoint.entity.MobileApplication;
import com.taoists.common.orm.dao.BaseDao;

public interface MobileApplicationService extends BaseDao<MobileApplication> {

    public List<MobileApplication> listMobileApplicationByMobile(String mobile);
}
