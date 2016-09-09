package com.taoists.sys.dataDict.service.impl;
 
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.sys.dataDict.entity.DictCategory;
import com.taoists.sys.dataDict.service.DictCategoryService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-30
 */
@Service("dictCategoryService")
public class DictCategoryServiceImpl extends HibernateDaoSupport<DictCategory>
		implements DictCategoryService {

	@Override
	public DictCategory getByCode(String categoryCode) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq("categoryCode", categoryCode));
		return (DictCategory) detachedCriteria.getExecutableCriteria(
				getSession()).uniqueResult();
	}

	
	@Override
	public List<DictCategory> findDictCategoryByType(Integer typeId){
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq("type", typeId));
		return findDatas(detachedCriteria);
	}
	
}
