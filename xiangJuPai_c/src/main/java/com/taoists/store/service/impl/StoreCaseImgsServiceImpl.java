package com.taoists.store.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.store.entity.StoreCaseImgs;
import com.taoists.store.service.StoreCaseImgsService;

@Service("storeCaseImgsService")
public class StoreCaseImgsServiceImpl extends HibernateDaoSupport<StoreCaseImgs> implements StoreCaseImgsService{

	//根据caseId获取图片列表
	public List<StoreCaseImgs> findByCaseId(long caseId){
		DetachedCriteria detachedCriteria = createDetachedCriteria();
	    detachedCriteria.add(Restrictions.eq("storeCase.id", caseId));
	    return detachedCriteria.getExecutableCriteria(getSession()).list();
	}
}
