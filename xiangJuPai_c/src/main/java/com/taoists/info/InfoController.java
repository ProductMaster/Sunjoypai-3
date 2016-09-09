package com.taoists.info;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taoists.CommonProjectController;
import com.taoists.common.util.HttpUtils;

import net.sf.json.JSONObject;


/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2015年7月19日 上午10:29:34 
 * 类说明 
 */

@Controller
@RequestMapping("/info")
public class InfoController extends CommonProjectController {

	/** 主页 */
    @RequestMapping("/home")
    public String home(HttpSession session, HttpServletRequest request)throws IOException{
        
        return "/info/home";
    }
    
    /** 内墙装修*/
    @RequestMapping("/neiqiang")
    public String neiqiang(HttpSession session, HttpServletRequest request)throws IOException{
        
        return "/info/neiqiang";
    }
    
    /** 外墙装修 */
    @RequestMapping("/waiqiang")
    public String waiqiang(HttpSession session, HttpServletRequest request)throws IOException{
        
        return "/info/waiqiang";
    }
    
    /** 智能家居 */
    @RequestMapping("/zhineng")
    public String zhineng(HttpSession session, HttpServletRequest request)throws IOException{
        
        return "/info/zhineng";
    }
    
    /*公司信息*/
    @RequestMapping("/company")
    public String company(HttpServletRequest request) {
    	return "/info/company";
    } 	
    
    /*关于信息*/
    @RequestMapping("/about")
    public String about(HttpServletRequest request) {
    	return "/info/about";
    }    
	
    @RequestMapping(value = "/getHouseModify")
    public void getHouseModify(HttpServletResponse response) throws IOException {
    	String url = "http://www.sunjoypai.com/api/userapp/paintwall/getHouseModify.json";
    	String resp = HttpUtils.post(url,"");    	    	
    	response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(resp);
        writer.flush(); 
        writer.close(); 
    }
    
    @RequestMapping(value = "/calculatecost")
	public void calculatecost(HttpServletRequest request,HttpServletResponse response,Integer spaceType,Integer metopeType,String roomType,String materialType,Double forecastCoveredArea,
			Integer saloonNum,Integer roomNum,Integer metopeCase1,Integer metopeCase2,Integer metopeCase3,Integer metopeCaseNoneFlag,Integer scaffoldFlag,String houseModify
			) throws IOException {
    	Map<String,String[]> map = request.getParameterMap();
    	
    	StringBuilder builder = new StringBuilder();
    	for(String s:map.keySet()) {
    		System.out.println(s);
    		builder.append(s);
    		builder.append("=");
    		builder.append(map.get(s)[0]);
    		builder.append("&");
    	}
    	builder.deleteCharAt(builder.length()-1);
    	System.out.println(builder.toString());
    	String url = "http://www.sunjoypai.com/api/userapp/order/calculatecost.json";
    	String resp = HttpUtils.post(url,builder.toString());    	    	
    	response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(resp);
        writer.flush(); 
        writer.close();     	

    }
    
	/** ---------------------------- helper ------------------------------ **/

	@Override
	protected String getModule() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
