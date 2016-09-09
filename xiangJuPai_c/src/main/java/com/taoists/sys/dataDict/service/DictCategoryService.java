package com.taoists.sys.dataDict.service;
 
import java.util.List;

import com.taoists.common.orm.dao.BaseDao;
import com.taoists.sys.dataDict.entity.DictCategory;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-30
 */
public interface DictCategoryService extends BaseDao<DictCategory> {

	DictCategory getByCode(String categoryCode);
	public List<DictCategory> findDictCategoryByType(Integer typeId);
}
