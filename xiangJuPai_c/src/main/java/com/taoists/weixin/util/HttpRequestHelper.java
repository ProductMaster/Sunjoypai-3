package com.taoists.weixin.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.client.params.CookiePolicy;

import com.taoists.common.util.StringUtils;

public class HttpRequestHelper {
	
	/**
	 * 拼接url
	 * @param head--url开头
	 * @param tail--url结束
	 * @return
	 */
	public static String addUrl(String head, String tail) {
		if (head.endsWith("/")) {
			if (tail.startsWith("/")) {
				return head.substring(0, head.length() - 1) + tail;
			} else {
				return head + tail;
			}
		} else {
			if (tail.startsWith("/")) {
				return head + tail;
			} else {
				return head + "/" + tail;
			}
		}
	}

	/**
	 * POST发送请求
	 * @param url--请求地址
	 * @param params--请求数据
	 * @param codePage--数据编码
	 * @return
	 * @throws Exception
	 */
	public synchronized static String postData(String url, Map<String, String> params, String codePage) throws Exception {

		final HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10 * 1000);//设置建立链接的超时时间
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(20 * 1000);//设置Socket的超时时间

		final PostMethod method = new PostMethod(url);
		if (params != null) {
			method.getParams().setParameter("http.protocol.cookie-policy",CookiePolicy.BROWSER_COMPATIBILITY);
			method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, codePage);
			method.setRequestBody(assembleRequestParams(params));
		}
		String result = "";
		try {
			httpClient.executeMethod(method);
			InputStream  is=method.getResponseBodyAsStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,codePage));    
			StringBuilder sb = new StringBuilder();    
			String line = null;    
			while ((line = reader.readLine()) != null) {    
				sb.append(line);    
			}
			result=sb.toString();
		} catch (final Exception e) {
			throw e;
		} finally {
			method.releaseConnection();
		}
		return result;
	}
	
	/**
	 * POST发送请求
	 * @param url--请求地址
	 * @param codePage--数据编码
	 * @return
	 * @throws Exception
	 */
	public synchronized static String postData(String url, String codePage) throws Exception {
		final HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10 * 1000);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(10 * 1000);

		final GetMethod method = new GetMethod(url);
		String result = "";
		try {
			httpClient.executeMethod(method);
			InputStream  is=method.getResponseBodyAsStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,codePage));    
			StringBuilder sb = new StringBuilder();    
			String line = null;    
			while ((line = reader.readLine()) != null) {    
				sb.append(line);    
			}
			result=sb.toString();
		} catch (final Exception e) {
			throw e;
		} finally {
			method.releaseConnection();
		}
		return result;
	}
	
	/**
	 * POST发送请求
	 * @param url--请求地址
	 * @param xml--请求数据
	 * @param codePage--数据编码
	 * @return
	 * @throws Exception
	 */
	public synchronized static String postData(String url, String xml, String codePage) throws Exception {
		
		final HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10 * 1000);//设置建立链接的超时时间
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(10 * 1000);//设置Socket的超时时间
		
		final PostMethod method = new PostMethod(url);
		if (!"".equals(xml) && xml != null) {
			RequestEntity requestEntity = new StringRequestEntity(xml,"application/xml","UTF-8");       
			method.setRequestEntity(requestEntity);
		}
		String result = "";
		try {
			httpClient.executeMethod(method);
			InputStream  is=method.getResponseBodyAsStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,codePage));    
			StringBuilder sb = new StringBuilder();    
			String line = null;    
			while ((line = reader.readLine()) != null) {    
				sb.append(line);    
			}
			result=sb.toString();
		} catch (final Exception e) {
			throw e;
		} finally {
			method.releaseConnection();
		}
		return result;
	}
	
	/**
	 * GET发送请求
	 * @param url--请求地址
	 * @param codePage--数据编码
	 * @return
	 * @throws Exception
	 */
	public synchronized static String getData(String url, String codePage) throws Exception {
		final HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10 * 1000);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(10 * 1000);

		final GetMethod method = new GetMethod(url);
		String result = "";
		try {
			httpClient.executeMethod(method);
			InputStream  is=method.getResponseBodyAsStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,codePage));    
			StringBuilder sb = new StringBuilder();    
			String line = null;    
			while ((line = reader.readLine()) != null) {    
				sb.append(line);    
			}
			result=sb.toString();
		} catch (final Exception e) {
			throw e;
		} finally {
			method.releaseConnection();
		}
		return result;
	}


	/**
	 * 组装http请求参数
	 * @param params
	 * @param menthod
	 * @return
	 */
	private synchronized static NameValuePair[] assembleRequestParams(Map<String, String> data) {
		final List<NameValuePair> nameValueList = new ArrayList<NameValuePair>();

		Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
			nameValueList.add(new NameValuePair((String) entry.getKey(), (String) entry.getValue()));
		}

		return nameValueList.toArray(new NameValuePair[nameValueList.size()]);
	}
	
	/**
	 * @Description: 拼接url参数
	 * @param urlHead
	 * @param parame
	 * @return String    返回类型
	 * @author luffy
	 */
	public static String spliceUrlParame(String urlHead,String parame){
		if (StringUtils.isNoEmpty(urlHead)) {
			if (urlHead.indexOf("?") != -1) {
				if (urlHead.endsWith("?") || urlHead.endsWith("&")) {
					if (parame.startsWith("?") || parame.startsWith("&")){
						return urlHead+parame.substring(1, parame.length());
					} else {
						return urlHead+parame;
					}
				} else {
					if (parame.startsWith("?")) {
						return urlHead+parame.substring(1, parame.length());
					} else if(parame.startsWith("&")){
						return urlHead+parame;
					} else {
						return urlHead+"&"+parame;
					}
				}
			} else {
				if (parame.startsWith("?")) {
					return urlHead+parame;
				} else if (parame.startsWith("&")) {
					return urlHead+"?"+parame.substring(1, parame.length());
				} else {
					return urlHead+"?"+parame;
				}
			}
		}
		return urlHead;
	}
	/**
	 * post请求发送数据
	 * @return
	 * @throws Exception
	 */
	public static String paramsPost(String sendurl,String params) throws Exception{
        StringBuffer buffer = new StringBuffer();
		
        try {
	           URL uploadServlet = new URL(sendurl);
               BufferedReader reader = null;
	           String strMessage = "";
	           HttpURLConnection servletConnection = (HttpURLConnection) uploadServlet
	                  .openConnection();
	           // 设置连接参数
	           servletConnection.setRequestMethod("POST");
	           servletConnection.setDoOutput(true);
	           servletConnection.setDoInput(true);
	           servletConnection.setAllowUserInteraction(true);
	
	           // 开启流，写入XML数据
	           OutputStream output = servletConnection.getOutputStream();
	           output.write(params.toString().getBytes("UTF-8"));
	           output.flush();
	           output.close();
	
	           // 获取返回的数据
	           InputStream inputStream = servletConnection.getInputStream();
	           reader = new BufferedReader(new InputStreamReader(inputStream));
	           while ((strMessage = reader.readLine()) != null) {
	              buffer.append(strMessage);
	           }
	
	    } catch (java.net.ConnectException e) {
	            e.printStackTrace();
		}
		return buffer.toString();
	}
	public static void main(String[] args) throws Exception {
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("orderNo", "A0010010010041");
//		try {
//			postData("http://180.168.150.10:8011/pushSinaBjOrders.html",params,"UTF-8");
//			postData("http://180.168.150.10:8011/pushSinaBjOrders.html",params,"UTF-8");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		for(int i=0;i<=10;i++){
			long b=System.currentTimeMillis();
			System.out.println("-");
			String a=getData("http://outer.interface.vipishow.com/wxtgticket/showTip.html?openId=oTw2RjvIIYUIgwADw-92qsPrlo6o&r="+i,"UTF-8");
			System.out.println(System.currentTimeMillis()-b+"|"+a);
			
		}
	}
}
