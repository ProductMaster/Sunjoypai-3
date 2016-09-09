package com.taoists.common.util;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.taoists.sys.zoneCode.entity.ZoneCode;

/**
 * @author Enjoy E-mail:wangyi2200@yahoo.com.cn
 * @version 创建时间：Jul 25, 2008 10:56:56 AM
 * 类说明
 */
public class ZoneNoUtil {
	
	private static Map<String, ZoneCode> zoneCodeMap = null;
	private static Map<String, ZoneCode> zoneCodeAllMap = null;
	private static Map<String, ZoneCode> PROZONEMAP = null;
	public static Map<String,List<ZoneCode>> CITYMAP = null;
	public static Map<String,List<ZoneCode>> AREA = null;
	public static java.util.Vector<ZoneCode> LISTPROVINCE = null;//省份
	public static Map<String,Vector<ZoneCode>> TABLECITY = null;//城市
	public static Map<String,Vector<ZoneCode>> TABLEAREA = null;//区域
	static{
		initProvince();
		initCity();
		initArea();
	}	
	private static void initProvince()
	{
		LISTPROVINCE = new java.util.Vector<ZoneCode>();
	}
	private static void initCity()
	{
		TABLECITY = new Hashtable<String,Vector<ZoneCode>>();
	}
	private static void initArea()
	{
		TABLEAREA = new Hashtable<String,Vector<ZoneCode>>();
	}
	//获得省份no
	public static String getProzoneNo(String cityzoneNo){
		return cityzoneNo.substring(0,3)+"000000";
	}
	//获得名称
	public static String getZoneName(int zoneNo){
		if(zoneCodeAllMap.get(String.valueOf(zoneNo))==null){
			return "";
		}
    	return zoneCodeAllMap.get(String.valueOf(zoneNo)).getZoneName();
	}
	
	public static String getZoneName(String zoneNo){
		return getZoneName(Integer.parseInt(zoneNo));
	}
	public static String getProvinceName(String zoneNo){
		return getZoneName(Integer.parseInt(zoneNo.substring(0,3)+"000000"));
	}
	public static String getCityName(String zoneNo){
		return getZoneName(Integer.parseInt(zoneNo.substring(0,6)+"000"));
	}
	
	public static String getProvinceNo(String zoneNo){
		return zoneNo.substring(0,3)+"000000";
	}
	public static String getCityNo(String zoneNo){
		return zoneNo.substring(0,6)+"000";
	}
	@SuppressWarnings("unchecked")
	public  Map<String,List<ZoneCode>> getCityList()
	{
		Map<String, ZoneCode> prozoneMap = this.getProzoneMap();//省
		if(CITYMAP==null)
		{
			CITYMAP = new  HashMap<String, List<ZoneCode>>();
			Set<Map.Entry<String, ZoneCode>> set = prozoneMap.entrySet();
			Iterator it = set.iterator();
			while(it.hasNext())
			{
				Map.Entry<String, ZoneCode> entry = (Map.Entry<String, ZoneCode>) it.next();
				java.lang.String prozoneNo =  entry.getKey();//省编号
				//获得省下面的市
				Set<Map.Entry<String, ZoneCode>> zoneCodeMapSet = zoneCodeMap.entrySet();
				Iterator zoneCodeMapIt = zoneCodeMapSet.iterator();
				java.util.List<ZoneCode> listCity = new java.util.ArrayList<ZoneCode>();
				while(zoneCodeMapIt.hasNext())
				{
					Map.Entry<String, ZoneCode> entity = (Map.Entry<String, ZoneCode>) zoneCodeMapIt.next();
					ZoneCode zoneCode = entity.getValue();
					if(zoneCode.getFatherNo().equals(prozoneNo))listCity.add(zoneCode);
					
				}
				CITYMAP.put(entry.getKey(), listCity);
			}
		}
		return CITYMAP;
	}
	@SuppressWarnings("unchecked")
	public  Map<String,List<ZoneCode>> getAreaList()
	{
		Map<String,List<ZoneCode>> cityMap = this.getCityList();//市
		if(AREA==null)
		{
			AREA = new  HashMap<String, List<ZoneCode>>();
			Set<Map.Entry<String, List<ZoneCode>>> set = cityMap.entrySet();
			Iterator it = set.iterator();
			while(it.hasNext())
			{
				Map.Entry<String, List<ZoneCode>> entry = (Map.Entry<String, List<ZoneCode>>) it.next();
				List<ZoneCode> cityList = entry.getValue();//市信息
				List<ZoneCode> areaList = null;//new java.util.ArrayList<ZoneCode>();//区信息
				for(ZoneCode zoneCode : cityList)
				{
					areaList = new java.util.ArrayList<ZoneCode>();//区信息
					String cityNo = zoneCode.getZoneNo();
					//填充县
					Set<Map.Entry<String, ZoneCode>> zoneCodeMapSet = zoneCodeMap.entrySet();//全部的信息
					Iterator zoneCodeMapIt = zoneCodeMapSet.iterator();
					while(zoneCodeMapIt.hasNext())
					{
						Map.Entry<String, ZoneCode> entity = (Map.Entry<String, ZoneCode>) zoneCodeMapIt.next();
						ZoneCode temEntity = entity.getValue();//对应的基本信息
						if(cityNo.equals(temEntity.getFatherNo()))areaList.add(temEntity);
					}
					AREA.put(cityNo, areaList);
				}
			}
		}
		return AREA;
	}
	public Map<String, ZoneCode> getProzoneMap() {
		if(PROZONEMAP==null)
		{
			PROZONEMAP = new HashMap<String, ZoneCode>();
			Set<Map.Entry<String, ZoneCode>> set = zoneCodeMap.entrySet();
			Iterator it = set.iterator();
			while(it.hasNext())
			{
				Map.Entry<String, ZoneCode> entry = (Map.Entry<String, ZoneCode>) it.next();
				java.lang.String key = entry.getKey();
				ZoneCode value = entry.getValue();
				if(value.getFatherNo().equals("000000000"))
				{
					PROZONEMAP.put(key, value);
					
					//LISTPROVINCE.add(value);
				}
			}
		}
		return PROZONEMAP;
	}
	public void setZoneCodeMap(Map<String, ZoneCode> zoneCodeMap) {
		this.zoneCodeMap = zoneCodeMap;
	}
	public static String getAreaName(String zoneNo){
		return getZoneName(Integer.parseInt(zoneNo));
	}
	public void setZoneCodeAllMap(Map<String, ZoneCode> zoneCodeAllMap) {
		this.zoneCodeAllMap = zoneCodeAllMap;
	}
	
}
