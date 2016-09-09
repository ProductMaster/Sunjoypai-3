package com.taoists.sys.dataDict.service.impl;
 
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.taoists.common.Const;
import com.taoists.common.bean.Page;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.sys.dataDict.entity.DataDict;
import com.taoists.sys.dataDict.model.DataDictTimelineObjective;
import com.taoists.sys.dataDict.service.DataDictService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-30
 */
@Service("dataDictService")
public class DataDictServiceImpl extends HibernateDaoSupport<DataDict>
		implements DataDictService {

	@Override
	public List<DataDict> findDataDictByCategoryId(long categoryId) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.createAlias("dictCategory", "dc");
		detachedCriteria.add(Restrictions.eq("dc.id", categoryId));
		return findDatas(detachedCriteria);
	}
	
	public List<DataDict> findDataDictByCategoryId(long categoryId, long companyId) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.createAlias("dictCategory", "dc");
		detachedCriteria.add(Restrictions.eq("dc.id", categoryId));
		detachedCriteria.add(Restrictions.eq("company.id", companyId));
		return findDatas(detachedCriteria);
	}
	
	public DataDict findDataDictByTitle(String title,long categoryId, long companyId) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq("title", title));
		detachedCriteria.createAlias("dictCategory", "dc");
		detachedCriteria.add(Restrictions.eq("dc.id", categoryId));
		detachedCriteria.add(Restrictions.eq("company.id", companyId));
		List<DataDict> list = findDatas(detachedCriteria); 
		if (list.size() > 0)
			return list.get(0);
		return new DataDict();
	}
	
	@Override
	public List<DataDict> findDataDictByCategoryCode(String categoryCode) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.createAlias("dictCategory", "dc");
		detachedCriteria.add(Restrictions.eq("dc.categoryCode", categoryCode));
		return findDatas(detachedCriteria);
	}

	@Override
	public List<DataDict> findDataDictByCategoryCode(String categoryCode,
			Page page) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.createAlias("dictCategory", "dc");
		detachedCriteria.add(Restrictions.eq("dc.categoryCode", categoryCode));
		return findPage(detachedCriteria, page);
	}
	
	public DataDictTimelineObjective getTimelineObjective(long categoryId, long companyId){
		
		ArrayList<Long> ids = new ArrayList<Long>();
		ArrayList<String> titles = new ArrayList<String>();
		titles.add(Const.NULL_VALUE_STRING);
		ArrayList<String> parameters = new ArrayList<String>();
		
		List<DataDict> list = findDataDictByCategoryId(categoryId, companyId);
		for(DataDict dataDict : list){
			ids.add(dataDict.getId());
			titles.add( dataDict.getTitle()  ); 
			parameters.add( Long.toString(dataDict.getId()) ); 
		}
		DataDictTimelineObjective o = new DataDictTimelineObjective(ids, titles, parameters);
		return o;
	}

}
