package com.taoists.sys.dataDict.service;

import java.util.List;

import com.taoists.common.bean.Page;
import com.taoists.common.orm.dao.BaseDao;
import com.taoists.sys.dataDict.entity.DataDict;
import com.taoists.sys.dataDict.model.DataDictTimelineObjective;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-30
 */
public interface DataDictService extends BaseDao<DataDict> {

	public List<DataDict> findDataDictByCategoryId(long categoryId);
	
	public List<DataDict> findDataDictByCategoryId(long categoryId, long companyId);
	
	List<DataDict> findDataDictByCategoryCode(String categoryCode);

	List<DataDict> findDataDictByCategoryCode(String categoryCode, Page page);
	
	public DataDictTimelineObjective getTimelineObjective(long categoryId, long companyId);
	
	public DataDict findDataDictByTitle(String title,long categoryId, long companyId);
}
