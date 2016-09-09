package com.taoists.sys.zoneCode.service.impl;
 
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.taoists.common.Const;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.sys.zoneCode.entity.ZoneCode;
import com.taoists.sys.zoneCode.service.ZoneCodeService;

/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2012-7-25 下午05:17:43  
 *  
 */
@Service("zoneCodeService")
public class ZoneCodeServiceImpl extends HibernateDaoSupport<ZoneCode> implements ZoneCodeService {

	public List<ZoneCode> getRootZones() {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		criteria.add(Restrictions.eq("level", 1));
		return criteria.list();
	}
	
	public List<ZoneCode> findZonesParent(String parentCode) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		Criteria criteria = detachedCriteria
				.getExecutableCriteria(getSession());
		criteria.add(Restrictions.eq("fatherNo", parentCode));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,ZoneCode> findAllZoneCode() {
		List<ZoneCode> listZoneCode = createCriteria(
									Restrictions.ne("fatherNo", Const.NULL_VALUE_STRING), 
									Restrictions.eq("status", 0)
								).list();
		//将list数据填充到Map里面
		Map<String, ZoneCode> returnMap = new java.util.HashMap<String, ZoneCode>();
		for(ZoneCode zoneCode : listZoneCode)
			returnMap.put(zoneCode.getZoneNo(), zoneCode);
		return returnMap;
	}
	
	/**
	 * 获得所有的城市编号(不过滤)
	 * add 2011/11/11 by derek
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,ZoneCode> findZoneCode(){
		List<ZoneCode> listZoneCode = createCriteria(Restrictions.ne("fatherNo", Const.NULL_VALUE_STRING)).list();
		//将list数据填充到Map里面
		Map<String, ZoneCode> returnMap = new java.util.HashMap<String, ZoneCode>();
		for(ZoneCode zoneCode : listZoneCode)
			returnMap.put(zoneCode.getZoneNo(), zoneCode);
		return returnMap;
	}
	
	@SuppressWarnings("unchecked")
	public List<ZoneCode> findByFatherNo(String fatherNo) {
		return createCriteria(
				Restrictions.eq("status", 0),
				Restrictions.eq("fatherNo", fatherNo)
		).list();
	}
}
