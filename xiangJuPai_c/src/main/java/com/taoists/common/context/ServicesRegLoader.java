package com.taoists.common.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


import com.taoists.sys.menu.entity.Menu;
import com.taoists.sys.menu.service.MenuService;

public class ServicesRegLoader {
	// 加载菜单
	public static Map<String, Menu> loadMenuURI(ServletContext sc) {
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(sc);
/*		ErpConstantService erpConstantService = (ErpConstantService)webApplicationContext.getBean("erpConstantService");
		List<ErpConstant> list = erpConstantService.findErpConstant();
		Map<String,String> constantMap = new HashMap<String,String>();
		Map<String,List<ErpConstant>> constantListMap = new HashMap<String,List<ErpConstant>>();
		for(ErpConstant constant : list){
			constantMap.put(constant.getConstantType()+"_"+constant.getConstantKey(), constant.getConstantValue());
			if(constant.getIsDefault() == 1){//如果是默认值
				constantMap.put(constant.getConstantType()+"_DEFAULT", constant.getConstantKey());
			}
			if(!constantListMap.containsKey(constant.getConstantType())){
				constantListMap.put(constant.getConstantType(), erpConstantService.findErpConstant(constant.getConstantType()));
			}
		}
		ErpConst.constantListMap = constantListMap;
		ErpConst.constantMap = constantMap;*/
		
		return webApplicationContext.getBean(MenuService.class).findURI();
	}
}
