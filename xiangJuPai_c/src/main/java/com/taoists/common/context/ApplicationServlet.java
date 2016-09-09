package com.taoists.common.context;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.taoists.common.Const;
import com.taoists.common.util.ZoneNoUtil;
import com.taoists.sys.zoneCode.entity.ZoneCode;
import com.taoists.sys.zoneCode.service.ZoneCodeService;
import com.taoists.weixin.context.AccessTokenThread;
import com.taoists.weixin.context.JsapiTicketThread;


public class ApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());
	private java.util.Timer timer = null;
	private java.lang.String fatherNo = "000000000";

	public ApplicationServlet() {
		super();
	}

	public void init() throws ServletException {
		try {
			ServletContext sc = getServletContext();
			WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(sc);
			
			// 微信通知发送进程
			//WeixinNoticeTaskService weixinNoticeTaskService = (WeixinNoticeTaskService)applicationContext.getBean("weixinNoticeTaskService");
			//HongbaoService hongbaoService = (HongbaoService)applicationContext.getBean("hongbaoService");
			//timer = new java.util.Timer(true); 
			//timer.schedule(new WeixinTimedTask(weixinNoticeTaskService, hongbaoService), 60*1000, 5*60*1000);
			
			// 微信AccessToken缓存器			
			new Thread(new AccessTokenThread()).start();
			while(AccessTokenThread.accessToken==null){
				Thread.sleep(1000);
			}
			new Thread(new JsapiTicketThread()).start();
		
			sc.setAttribute(Const.APP_MENU, ServicesRegLoader.loadMenuURI(sc));
			
			ZoneCodeService zoneCodeService = (ZoneCodeService) applicationContext.getBean("zoneCodeService");
			ZoneNoUtil zoneNoUtil = new ZoneNoUtil();
			zoneNoUtil.setZoneCodeMap(zoneCodeService.findAllZoneCode());
			zoneNoUtil.setZoneCodeAllMap(zoneCodeService.findZoneCode());
			zoneNoUtil.CITYMAP = null;
			zoneNoUtil.AREA = null;
			zoneNoUtil.getCityList();//加载数据
			zoneNoUtil.getAreaList();//加载数据
			//重新写的城市操作
			List<ZoneCode> list = zoneCodeService.findByFatherNo(fatherNo);
			this.initProvince(list);//初始化省份
			this.initCity(zoneCodeService);//初始化城市
			this.initArea(zoneCodeService);//初始化区域
			
			logger.info("Application context load success!");
		} catch (Exception e) {
			logger.error("Application context load error!" + e.toString());
			e.printStackTrace();
		}

	}

	public void destroy() {
		super.destroy();
	}
	
	private void initProvince(List<ZoneCode> list)
	{
		for(ZoneCode zoneCode : list)
			ZoneNoUtil.LISTPROVINCE.add(zoneCode);
	}

	private void initCity(ZoneCodeService zoneCodeService)
	{
		for(ZoneCode zoneCode : ZoneNoUtil.LISTPROVINCE)
		{
			String zoneNo = zoneCode.getZoneNo();
			List<ZoneCode> list = zoneCodeService.findByFatherNo(zoneNo);
			Vector<ZoneCode> value = new Vector<ZoneCode>();
			for(ZoneCode entity : list) value.add(entity);
			ZoneNoUtil.TABLECITY.put(zoneNo, value);
		}
	}
	
	
	private void initArea(ZoneCodeService zoneCodeService){
		java.util.Map<String, Vector<ZoneCode>> tableCity = ZoneNoUtil.TABLECITY;
		Set<Map.Entry<String, Vector<ZoneCode>>> set = tableCity.entrySet();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			Map.Entry<String, Vector<ZoneCode>> entity = (Map.Entry<String, Vector<ZoneCode>>) it.next();
			Vector<ZoneCode> vector = entity.getValue();
			for(ZoneCode zoneCode : vector)
			{
				String zoneNo = zoneCode.getZoneNo();
				List<ZoneCode> list = zoneCodeService.findByFatherNo(zoneNo);
				Vector<ZoneCode> value = new Vector<ZoneCode>();
				for(ZoneCode zc : list)
					value.add(zc);
				ZoneNoUtil.TABLEAREA.put(zoneNo, value);
			}
		}
		
	}
}
