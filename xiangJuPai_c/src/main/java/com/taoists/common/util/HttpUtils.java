/**
 * 
 */
package com.taoists.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taoists.common.Security.MyX509TrustManager;

/**
 * @author Simon Lv
 * @date Oct 24, 2013
 */
public class HttpUtils {
	private static final String CLASS_NAME = HttpUtils.class.getName();
	private static int TIMEOUT = 60 * 1000;
	private static String HTTP_HEAD_CONTENT_TYPE = "Content-Type";
	private static String HTTP_HEAD_CONTENT_TYPE_VALUE = "application/json; charset=utf-8";
	/**
	 * 
	 * @param response
	 * @param jsonStr
	 * @throws Exception
	 */
	public static void respWrite(HttpServletResponse response, String jsonStr)
			throws Exception {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.write(jsonStr);
		writer.flush();
		writer.close();
	}
	
	/**
	 * 
	 * @param response
	 * @param jsonStr
	 * @throws Exception
	 */
	public static void respJsonpWrite(HttpServletResponse response, HttpServletRequest request, String jsonStr)
			throws Exception {
		response.setContentType("application/json;charset=UTF-8");
		String callbackFunName = request.getParameter("callbackparam");
		StringBuffer sb = new StringBuffer(callbackFunName);
		
		sb.append("(").append(jsonStr).append(")");
		
		PrintWriter writer = response.getWriter();
		writer.write(sb.toString());
		writer.flush();
		writer.close();
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String getReqJson(HttpServletRequest request)
			throws Exception {
		BufferedReader reader = request.getReader();
		StringBuffer buffer = new StringBuffer();
		String str;
		while ((str = reader.readLine()) != null) {
			buffer.append(str);
		}
		reader.close();

		return buffer.toString();
	}
	
	/**
	 * Get HttpClient
	 * 
	 * @return
	 */
	private static HttpClient getHttpClient() {
		HttpClient client = new DefaultHttpClient();
		HttpParams httpParams = client.getParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT);
		HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT);
		return client;
	}
	
	/**
	 * Use Http to post message 
	 * 传递的参数格式为json
	 * @param url
	 * @param request
	 * @return
	 */
	public static String post(String url, String request) {
		LoggerUtils.debug(CLASS_NAME, String.format("Http Status url = [%s]", url));
		LoggerUtils.debug(CLASS_NAME, String.format("Http Status request = [%s]", request));
		HttpClient client = getHttpClient();
		String response = null;
		try {
			HttpPost post = getHttpPost(url, request);
			HttpResponse res = client.execute(post);
			int statusCode = res.getStatusLine().getStatusCode();
			LoggerUtils.debug(CLASS_NAME, String.format("Http Status Code = [%s]", statusCode));
			if (statusCode == 200) {
				response = EntityUtils.toString(res.getEntity(), "UTF-8");
			} else {

			}
		} catch (Exception e) {
			LoggerUtils.error(CLASS_NAME, "Http Component Send Post Request Execption.", e);
		}
		return response;
	}
	

	
	/**
	 * 
	 * Get the HttpPost info
	 * 
	 * @param url
	 * @param contextString
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private static HttpPost getHttpPost(String url, String contextString) throws UnsupportedEncodingException {
		HttpPost post = new HttpPost(url);
		//post.setHeader(HTTP_HEAD_CONTENT_TYPE, HTTP_HEAD_CONTENT_TYPE_VALUE);
//		HttpEntity entity = new ByteArrayEntity(contextString.getBytes());
//		post.setEntity(entity);
		StringEntity reqEntity = new StringEntity(contextString,"UTF-8");
		reqEntity.setContentType("application/x-www-form-urlencoded");
		post.setEntity(reqEntity);
		return post;
	}

	/**
	 * Use HttpGet to get response message
	 * 
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		HttpClient client = getHttpClient();
		LoggerUtils.debug(CLASS_NAME, url);
		HttpGet get = getHttpGet(url);
		String response = null;
		try {
			HttpResponse res = client.execute(get);
			int statusCode = res.getStatusLine().getStatusCode();
			LoggerUtils.debug(CLASS_NAME, String.format("Http Status Code = %s", statusCode));
			if (statusCode == 200) {
				response = EntityUtils.toString(res.getEntity(), "UTF-8");
			} else {
			}
		} catch (Exception e) {
			LoggerUtils.error(CLASS_NAME, "Http Component Send Get Request Execption.", e);
		}
		return response;
	}

	/**
	 * Create HttpGet
	 * 
	 * @param url
	 * @return
	 */
	private static HttpGet getHttpGet(String url) {
		HttpGet get = new HttpGet(url);
		return get;
	}
	
	/**
     * 发起https请求并获取结果
     * 
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject   (通过JSONObject.get(key)的方式获取json对象的属性值)
     * 
     */
    public static JSONObject httpRequest(String requestUrl,
            String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(null, requestUrl, new sun.net.www.protocol.https.Handler());
            javax.net.ssl.HttpsURLConnection httpUrlConn = (javax.net.ssl.HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(
                    inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            logger.error("Http server connection timed out.");
        } catch (Exception e) {
            logger.error("https request error:{}", e);
        }
        return jsonObject;
    }
    
    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
}
