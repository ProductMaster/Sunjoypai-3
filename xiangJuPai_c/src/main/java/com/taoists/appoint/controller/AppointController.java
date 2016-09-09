package com.taoists.appoint.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taoists.CommonProjectController;
import com.taoists.appoint.entity.MobileApplication;
import com.taoists.common.bean.CommonCond;
import com.taoists.common.bean.Page;
import com.taoists.common.orm.PropertyFilter;

@Controller
@RequestMapping("/appoint")
public class AppointController extends CommonProjectController {

    @RequestMapping("/listMobileApplication")
    public String listDealer(HttpServletRequest request,Page page,Model model,CommonCond cond){
        List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
        
        extractParams(request);
        mobileApplicationService.findPage(page, filters);
        return "/appoint/listMobileApplication";
    }
    
    @RequestMapping(value = "/addMobileApplication", method = RequestMethod.POST)  
    @ResponseBody  
    public void addMobileApplication(HttpServletRequest request, HttpServletResponse response) throws Exception {  
        
      Map<String, String> map = new HashMap<String, String>(1);
      String mobile = request.getParameter("mobile");
      String sourcePage = request.getParameter("sourcePage");
      List<MobileApplication> list = mobileApplicationService.listMobileApplicationByMobile(mobile);
      MobileApplication application = new MobileApplication();
      response.setCharacterEncoding("utf-8");
      if(list.size()>0){
          //map.put("success", "1");
          response.getWriter().write("{\"success\":0}");
      }else{
          application.setMobile(mobile);
          application.setSourcePage(sourcePage);
          mobileApplicationService.save(application);
          //map.put("success", "OK");
          response.getWriter().write("{\"success\":1}");
      }    
      
      response.getWriter().flush();  
    }  

    @Override
    protected String getModule() {
        // TODO Auto-generated method stub
        return null;
    }
}
