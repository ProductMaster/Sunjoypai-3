package com.taoists.appoint.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.taoists.appoint.entity.MobileApplication;
import com.taoists.appoint.service.MobileApplicationService;
import com.taoists.common.orm.dao.HibernateDaoSupport;


@Service("mobileApplicationService")
public class MobileApplicationServiceImpl extends HibernateDaoSupport<MobileApplication> implements MobileApplicationService  {
    
    public List<MobileApplication> listMobileApplicationByMobile(String mobile){
            
        DetachedCriteria detachedCriteria = createDetachedCriteria();
        detachedCriteria.add(Restrictions.eq("mobile", mobile));
        return detachedCriteria.getExecutableCriteria(getSession()).list();        
     }
    
    
}
