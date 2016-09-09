package com.taoists.common.IpAddr;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Lists;
import com.taoists.common.gson.GsonJSONHelper;
import com.taoists.common.util.HttpUtils;
import com.taoists.common.util.StringUtils;
import com.taoists.common.util.ValidateUtils;

/**
 * 
 * @author Simon Lv
 * @email simon-jiafa@126.com
 * @date 2015-6-30 上午11:21:38
 */
public class IPAddrUtils {
    /**
     * 获得IP地址
     * 
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
         ipAddress = request.getHeader("X-Real-IP");
         if(isEmpty(ipAddress)) {
          ipAddress = request.getHeader("X-Forwarded-For");
         }
         if(isEmpty(ipAddress)) {
             ipAddress = request.getHeader("Proxy-Client-IP");
         }
         if(isEmpty(ipAddress)){
             ipAddress = request.getHeader("WL-Proxy-Client-IP");
         }
         if(isEmpty(ipAddress)) {
             ipAddress = request.getRemoteAddr();
             if(ipAddress.equals("127.0.0.1")){
                 //根据网卡取本机配置的IP
                 InetAddress inet=null;
                 try {
                     inet = InetAddress.getLocalHost();
                 } catch (UnknownHostException e) {
                     e.printStackTrace();
                 }
                 ipAddress= inet.getHostAddress();
             }
         }

         //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
         if(ipAddress!=null && ipAddress.length()>15) { //"***.***.***.***".length() = 15
             if(ipAddress.indexOf(",")>0){
                 ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
             }
         }
         
         return getCityName4TB(ipAddress); 
    }
    
    private static String getCityName4TB(String ipAddr){
        if(isValidIP(ipAddr)){
            String url = "http://ip.taobao.com/service/getIpInfo.php?ip="+ipAddr;
            IP4TaobaoBean tbbean = GsonJSONHelper.fromJson(HttpUtils.get(url), IP4TaobaoBean.class);
            if(ValidateUtils.isNotEmpty(tbbean)){
                Integer code = tbbean.getCode();
                if(code == 0){
                    TaobaoData tdata = tbbean.getData();
                    if(ValidateUtils.isNotEmpty(tdata)){
                        return isInCitys(tdata.getCity());
                    }
                }
            }
        }
        
        return null;
    }
    
    private static boolean isValidIP(String ipAddr){
        if(StringUtils.isNoEmpty(ipAddr)){
            if(ipAddr.indexOf(":")<0){
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 是否在需要过滤的城市中
     * 
     * @param cityName
     * @return
     */
    private static String isInCitys(String cityName){
        List<String> citys = citys();
        
        if(citys.contains(cityName)){
            return cityName;
        }
        
        return null;
    }
    
    /**
     * 需要定位的城市列表
     * 
     * @return
     */
    private static List<String> citys(){
        List<String> citys = Lists.newArrayList();
        citys.add("北京市");
        citys.add("上海市");
        citys.add("南京市");
        citys.add("武汉市");
        
        return citys;
    }
    
    /**
     * 
     * @param ipAddress
     * @return
     */
    private static boolean isEmpty(String ipAddress){
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            return true;
        }
        
        return false;
    }
}
