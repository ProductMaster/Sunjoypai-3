package com.taoists.sys.zoneCode.controller;
 
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taoists.Module;
import com.taoists.common.controller.CommonFrameworkController;
import com.taoists.common.util.ZoneNoUtil;
import com.taoists.sys.common.controller.path.ResultPath;
import com.taoists.sys.zoneCode.entity.ZoneCode;
import com.taoists.sys.zoneCode.service.ZoneCodeService;

/**
 *   @author George 
 *   E-mail:lendun@163.com
 *   @version 创建时间： 2013-9-11 下午04:55:142013-9-11
 *
 */

@Controller
@RequestMapping(ResultPath.sys)
public class ZoneCodeController extends CommonFrameworkController {

	/** ----------------------------  business ------------------------------ **/
	
	/** 获取省份列表 **/
	@RequestMapping(value = "/initZone", produces = "text/javascript;charset=UTF-8")
	public @ResponseBody String initZone()throws Exception {	
		ZoneNoUtil zoneNoUtil = new ZoneNoUtil();
		StringBuffer sb = new StringBuffer();
//		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//		sb.append("<root>");
		sb.append("jinit={'options':[");
		Map<String, ZoneCode> prozoneMap = zoneNoUtil.getProzoneMap();
		Set<Map.Entry<String, ZoneCode>> set = prozoneMap.entrySet();
		
		Iterator it = set.iterator();
		int temp = 0;
		while(it.hasNext())
		{
//			sb.append("<entity>");
			Map.Entry<String, ZoneCode> entry = (Map.Entry<String, ZoneCode>) it.next();
			ZoneCode value = entry.getValue();
//			sb.append("<zoneNo>"+value.getZoneNo()+"</zoneNo>");
//			sb.append("<zoneName>"+value.getZoneName()+"</zoneName>");
//			sb.append("</entity>");
			if (temp > 0)
				sb.append(",");
			sb.append("{'zoneNo':'").append(value.getZoneNo()).append("','zoneName':'").append(value.getZoneName()).append("'").append("}");
			temp++;
		}
//		sb.append("</root>");
		sb.append("]}");
		return sb.toString();
	}
	
	/** 获取省份列表 **/
	@RequestMapping(value = "/getZone", produces = "text/javascript;charset=UTF-8")
	public @ResponseBody String getZone(@RequestParam("zoneNo") String zoneNo, HttpServletRequest request, HttpServletResponse response)throws Exception {
	
		logger.debug("[enter Controller]GetZoneController");
		//江南 通用的城市插件
		int type = ServletRequestUtils.getIntParameter(request, "type");
		ZoneNoUtil zoneNoUtil = new ZoneNoUtil();
		List<ZoneCode> list = null;
		if(type==1)
		{
			list = zoneNoUtil.getCityList().get(zoneNo);
		}
		else
		{
			list = zoneNoUtil.getAreaList().get(zoneNo);
		}
		//System.out.println(zoneNoUtil.getAreaList());
		StringBuffer sb = new StringBuffer();
		//sb.append("<entity><zoneNo> </zoneNo><zoneName>---请选择---</zoneName></entity>");
		sb.append("jinitcity={'options':[");
		if(list==null)  list = zoneCodeService.findZonesParent(zoneNo);
		int temp = 0;
		for(ZoneCode zoneCode: list){
			if (temp > 0)
				sb.append(",");
			sb.append("{'zoneNo':'").append(StringEscapeUtils.escapeXml(zoneCode.getZoneNo())).append("','zoneName':'").append(StringEscapeUtils.escapeXml(zoneCode.getZoneName())).append("'").append("}");
			temp++;
		}
		sb.append("]}");
		return sb.toString();
	}
	
	/** ----------------------------  helper ------------------------------ **/
	
	protected String getModule() {
		return Module.sys.getName();
	}

	/** ----------------------------  Autowired ------------------------------ **/
	@Autowired
	protected ZoneCodeService zoneCodeService;
}
