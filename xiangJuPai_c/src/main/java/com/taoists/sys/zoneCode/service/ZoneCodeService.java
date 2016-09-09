package com.taoists.sys.zoneCode.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;

import com.taoists.common.Const;
import com.taoists.common.orm.dao.BaseDao;
import com.taoists.sys.zoneCode.entity.ZoneCode;

/**
 * @author George E-mail:lendun@163.com
 * @version 创建时间：2012-7-25 下午05:16:21
 * 
 */
public interface ZoneCodeService extends BaseDao<ZoneCode> {

	public List<ZoneCode> getRootZones();

	public List<ZoneCode> findZonesParent(String parentCode);

	public Map<String, ZoneCode> findAllZoneCode();

	public Map<String, ZoneCode> findZoneCode();

	public List<ZoneCode> findByFatherNo(String fatherNo);
}
