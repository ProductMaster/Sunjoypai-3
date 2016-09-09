package com.taoists.weixin.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import com.taoists.common.Const;
import com.taoists.common.Security.MyX509TrustManager;
import com.taoists.common.gson.GsonJSONHelper;
import com.taoists.common.util.HttpUtils;
import com.taoists.common.util.JacksonHelper;
import com.taoists.weixin.context.AccessTokenThread;
import com.taoists.weixin.context.JsapiTicketThread;
import com.taoists.weixin.menu.model.Menu;
import com.taoists.weixin.model.AccessToken;
import com.taoists.weixin.model.JsapiTicket;
import com.taoists.weixin.model.WeixinOpenId;
import com.taoists.weixin.model.WeixinOpenUser;

/**
 * @author George E-mail:lendun@163.com
 * @version 创建时间： 2013-11-15 上午10:27:452013-11-15
 * 
 */
public class WeixinUtils {

	private static Logger logger = LoggerFactory.getLogger(WeixinUtils.class);

	/*
	 * 深圳版 public static String appId = "wxd0f38a8d54202316"; public static
	 * String appSecret = "c5662c0f5156814c6b2d4dae4be5d3c9"; public static
	 * String partnerKey = "aa2a55500429b817d488b6e546e8d43a"; public static
	 * String partnerId = "1218606301"; public static String paySignKey =
	 * "b0iBAt3Qx9VjphGlk9NBNL6CCnKkq4oMxf75Hov1I8xa0RQ356X46xWxjubH68E4xc46tFV0YlyIKTSqXRmPY6ZqsQr5kDQFTBSloprrFqXfM3vS4fXogQ6jakeB7E1K"
	 * ;
	 */

	// 公众账号ID
	public static String appId = "wx69d041c3f5edb145";
	// 公众账号密钥
	public static String appSecret = "0e8f423a4c24d99a67e74615dba36fa8";
	// 微信支付分配的商户号
	public static String partnerId = "1261493801";
	// 商户密钥
	public static String key = "Sb7B4Ov2XTzCu4vLn6gULM8MpbXTVmoZ";
	
	public static String partnerKey = "aa2a55500429b817d488b6e546e8d43a";
	public static String paySignKey = "b0iBAt3Qx9VjphGlk9NBNL6CCnKkq4oMxf75Hov1I8xa0RQ356X46xWxjubH68E4xc46tFV0YlyIKTSqXRmPY6ZqsQr5kDQFTBSloprrFqXfM3vS4fXogQ6jakeB7E1K";

	public static String mchId = "1249087201";
	
/*	// 公众账号ID
	public static String appId = "wx51b75b45569c1381";
	// 公众账号密钥
	public static String appSecret = "259173fd1c57e931c2ae735005914a61";
	// 商户密钥
	public static String key = "ZBXKqpqzxiPFdpfLkQv9B3zoI591Va78";
	// 微信支付分配的商户号
	public static String partnerId = "10053905";

	public static String partnerKey = "aa2a55500429b817d488b6e546e8d43a";
	public static String paySignKey = "b0iBAt3Qx9VjphGlk9NBNL6CCnKkq4oMxf75Hov1I8xa0RQ356X46xWxjubH68E4xc46tFV0YlyIKTSqXRmPY6ZqsQr5kDQFTBSloprrFqXfM3vS4fXogQ6jakeB7E1K";

	public static String mchId = "10053905";*/
	/**
	 * 主动推送信息接口
	 */
	private String sendMsgUrl = "https://api.weixin.qq.com/cgi-bin/message/send?access_token={0}";

	public static String notify_url = "http://c.beikool.com/pay/weixin/notify";
	public static String notify_byVip_url = "http://c.beikool.com/pay/weixin/notifyByVip";
	public static String notify_Exchange_url = "http://c.beikool.com/pay/weixin/notifyByExchange";

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
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

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
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
			logger.error("Weixin server connection timed out.");
		} catch (Exception e) {
			logger.error("https request error:{}", e);
		}
		return jsonObject;
	}

	/**
	 *  
	 *  获取access_token     
	 *  @param appid 凭证  
	 *  @param appsecret 密钥  
	 *  @return
	 *  
	 */
	// 获取access_token的接口地址（GET） 限200（次/天） 
	public static AccessToken getAccessToken() {

		logger.debug("[ getAccessToken() ]");
		String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
		AccessToken accessToken = null;
		String requestUrl = access_token_url.replace("APPID", appId).replace("APPSECRET", appSecret);

		int isOk = Const.TRUE;
		do {
			isOk = Const.TRUE;
			JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
			// 如果请求成功
			if (null != jsonObject) {
				try {
					accessToken = new AccessToken();
					accessToken.setToken(jsonObject.getString("access_token"));
					accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
					accessToken.setCreateDate(System.currentTimeMillis());

					System.out.println("New access_token ================================================");
					System.out.println("jsonObject==>"+jsonObject);
					System.out.println("create_date: " + accessToken.getCreateDate());
					System.out.println("access_token: " + accessToken.getToken());
					System.out.println("expires_in: " + accessToken.getExpiresIn());
					
				} catch (JSONException e) {
					accessToken = null;
					isOk = Const.FALSE;
					// 获取token失败
					logger.error("获取token失败 errcode:{} errmsg:{}",
							jsonObject.getInt("errcode"),
							jsonObject.getString("errmsg"));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ie) {
					}
				}
			}
		} while (isOk == Const.FALSE);
		return accessToken;
	}

	/**
	 * @desc 推送信息
	 * @param token
	 * @param msg
	 * @return
	 */
	/**
	 * 主动推送信息接口
	 */
	public static boolean sendCustomMessage(String jsonMsg){
		logger.info("消息内容：{"+jsonMsg+"}");
		String send_msg_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
		boolean result = false;
		
		String token = AccessTokenThread.accessToken.getToken();
		send_msg_url = send_msg_url.replace("ACCESS_TOKEN", token);
		System.out.println("mes send url: "+send_msg_url);
		//发送客服消息
		JSONObject jsonObject = httpRequest(send_msg_url, "POST", jsonMsg);
		if(null != jsonObject){
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if(0 == errorCode){
				result = true;
				logger.info("客服消息发送成功errorCode:{"+errorCode+"},errmsg:{"+errorMsg+"}");
				System.out.println("客服消息发送成功errorCode:{"+errorCode+"},errmsg:{"+errorMsg+"}");
			}else{
				logger.info("客服消息发送失败errorCode:{"+errorCode+"},errmsg:{"+errorMsg+"}");
				System.out.println("客服消息发送失败errorCode:{"+errorCode+"},errmsg:{"+errorMsg+"}");
				if(errorCode == 40001 || errorCode == 42001 ){
			
				}
			}
		}
		return result;
	}
	
	/**
	 * @desc 获取用户openid
	 * @param token
	 * @param openid
	 * @return
	 */
	/** 获取微信openid */
	public static WeixinOpenId getOpenId(String code) {

		logger.debug("[ getOpenId(String code) ]");
		String get_openid_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		String requestUrl = get_openid_url.replace("APPID", appId)
				.replace("SECRET", appSecret).replace("CODE", code);

		WeixinOpenId openId = new WeixinOpenId();
		int isOk = Const.TRUE;
		int i = 0;
		do {
			isOk = Const.TRUE;
			i = i + 1;
			JSONObject jsonObject = httpRequest(requestUrl, "GET", null);

			// 如果请求成功
			if (null != jsonObject) {
				try {
					logger.debug("get_openid_url:" + jsonObject.toString());
					openId.setOpenId(jsonObject.getString("openid"));
					openId.setToken(jsonObject.getString("access_token"));
					openId.setCreateDate(System.currentTimeMillis());
					openId.setExpiresIn(jsonObject.getInt("expires_in"));
					openId.setRefreshToken(jsonObject
							.getString("refresh_token"));
				} catch (JSONException e) {
					// 获取token失败
					isOk = Const.FALSE;
					logger.debug("获取OPENID失败 errcode:{} errmsg:{}",
							jsonObject.getInt("errcode"),
							jsonObject.getString("errmsg"));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ie) {
					}
				}
			}
		} while (isOk == Const.FALSE && i < 3);
		logger.debug("openid:" + openId.getOpenId());
		logger.debug("access_token:" + openId.getToken());
		logger.debug("exprires_in:" + openId.getExpiresIn());
		return openId;
	}

	/**
	 * 检验授权凭证（access_token）是否有效
	 */
	public static int validateAccessToken(String token, String openid) {

		logger.debug("[ validateAccessToken(String token, String openid) ]");
		String validate_access_token_url = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";
		int retVal = Const.YES;
		String url = validate_access_token_url.replace("ACCESS_TOKEN", token)
				.replace("OPENID", openid);
		JSONObject jsonObject = httpRequest(url, "GET", null);
		if (null != jsonObject) {
			try {
				int errorCode = jsonObject.getInt("errcode");
				if (errorCode != 0) {
					retVal = Const.NO;
				}
			} catch (JSONException e) {
				logger.error("验证access_token 失败 errcode:{} errmsg:{}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
			}
		} else {
			retVal = Const.NO;
		}
		return retVal;
	}

	/**
	 * 刷新access_token
	 */
	public static WeixinOpenId refreshAccessToken(String refreshToken) {

		logger.debug("[ refreshAccessToken(String refreshToken) ]");
		String refresh_access_token_url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
		WeixinOpenId openId = new WeixinOpenId();
		String url = refresh_access_token_url.replace("APPID", appId).replace(
				"REFRESH_TOKEN", refreshToken);

		int isOk = Const.TRUE;
		int i = 0;
		do {
			isOk = Const.TRUE;
			i = i + 1;
			JSONObject jsonObject = httpRequest(url, "GET", null);
			// 如果请求成功

			if (null != jsonObject) {
				try {
					openId.setOpenId(jsonObject.getString("openid"));
					openId.setToken(jsonObject.getString("access_token"));
					openId.setCreateDate(System.currentTimeMillis());
					openId.setExpiresIn(jsonObject.getInt("expires_in"));
					openId.setRefreshToken(jsonObject
							.getString("refresh_token"));
				} catch (JSONException e) {

					isOk = Const.FALSE;
					// 获取token失败
					logger.error("refresh token失败 errcode:{} errmsg:{}",
							jsonObject.getInt("errcode"),
							jsonObject.getString("errmsg"));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ie) {
					}
				}
			}
		} while (isOk == Const.FALSE && i < 3);
		logger.debug("openid:" + openId.getOpenId());
		logger.debug("access_token:" + openId.getToken());
		logger.debug("exprires_in:" + openId.getExpiresIn());
		return openId;
	}

	/**
	 * @desc 拉取用户信息
	 * @param token
	 * @param openid
	 * @return
	 */
	// 拉微信用户信息接口
	private static String getUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=TOKEN&openid=OPENID&lang=zh_CN";

	public static WeixinOpenUser getUserInfo(WeixinOpenId weixinOpenId) {

		logger.debug("[ getUserInfo(WeixinOpenId weixinOpenId) ]");
		WeixinOpenId localWeixinOpenId = weixinOpenId;
		/*
		 * if(validateAccessToken(weixinOpenId.getToken(),
		 * weixinOpenId.getOpenId()) != Const.YES){ localWeixinOpenId =
		 * refreshAccessToken(weixinOpenId.getRefreshToken()); }
		 */

		logger.info("getUserInfo start.{token:" + localWeixinOpenId.getToken()
				+ ",openid:" + localWeixinOpenId.getOpenId() + "}");
		WeixinOpenUser user = null;
		String url = getUserInfoUrl.replace("TOKEN",
				localWeixinOpenId.getToken()).replace("OPENID",
				localWeixinOpenId.getOpenId());
		logger.debug("getUserInfoUrl:" + url);
		
		//user = GsonJSONHelper.fromJson(HttpUtils.get(url), WeixinOpenUser.class);

		int i = 0;
		int isOk = Const.TRUE;
		do {
			i = i + 1;
			isOk = Const.TRUE;
			JSONObject jsonObject = httpRequest(url, "GET", null);
			logger.debug("getUserinfo return ==>" + jsonObject.toString());

			// 如果请求成功
			if (null != jsonObject) {
				try {
					if (jsonObject.getString("openid") != null) {
					    //user = GsonJSONHelper.fromJson(jsonObject.toString(), WeixinOpenUser.class);
						user = new WeixinOpenUser();
						user.setOpenid(jsonObject.getString("openid") != null ? jsonObject
								.getString("openid") : "");
						user.setNickname(jsonObject.getString("nickname") != null ? jsonObject
								.getString("nickname") : "");
						user.setSex(jsonObject.getString("sex") != null ? jsonObject
								.getString("sex") : "");
						user.setCity(jsonObject.getString("city") != null ? jsonObject
								.getString("city") : "");
						user.setProvince(jsonObject.getString("province") != null ? jsonObject
								.getString("province") : "");
						user.setLanguage(jsonObject.getString("language") != null ? jsonObject
								.getString("language") : "");
						user.setHeadimgurl(jsonObject.getString("headimgurl") != null ? jsonObject
								.getString("headimgurl") : "");
					}
				} catch (JSONException e) {
					// 获取token失败
					user = null;
					isOk = Const.FALSE;
					logger.debug(e.toString());
					// logger.error("获取userInfo失败 errcode:{} errmsg:{}",
					// jsonObject.getString("errcode"),
					// jsonObject.getString("errmsg"));

				}
			}
		} while (isOk == Const.FALSE && i < 3);
		return user;
	}

	// 获取api_ticket
	public static JsapiTicket getJsapiTicket(String token) {

		logger.debug("[ getJsapiTicket(String token) ]");
		String ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
		int result = 0;
		JsapiTicket jsapiTicket = null;
		// 拼装创建菜单Url
		String url = ticket_url.replace("ACCESS_TOKEN", token);
		int isOk = Const.TRUE;
		int i = 0;
		do {
			isOk = Const.TRUE;
			i = i + 1;
			// 调用接口获取jsapi_ticket
			JSONObject jsonObject = httpRequest(url, "GET", null);
			// 如果请求成功
			if (null != jsonObject) {
				try {
					jsapiTicket = new JsapiTicket();
					jsapiTicket.setTicket(jsonObject.getString("ticket"));
					jsapiTicket.setExpiresIn(jsonObject.getInt("expires_in"));
					jsapiTicket.setCreateDate(System.currentTimeMillis());

					logger.debug("New Jsapi_Ticket =========");
					logger.debug("create_date: " + jsapiTicket.getCreateDate());
					logger.debug("ticket: " + jsapiTicket.getTicket());
					logger.debug("expires_in: " + jsapiTicket.getExpiresIn());
				} catch (JSONException e) {
					jsapiTicket = null;
					isOk = Const.FALSE;
					if (0 != jsonObject.getInt("errcode")) {
						result = jsonObject.getInt("errcode");
						logger.error("JSAPI_Ticket获取失败 errcode:{} errmsg:{}",
								jsonObject.getInt("errcode"),
								jsonObject.getString("errmsg"));
					}
				}
			}
		} while (isOk == Const.FALSE && i < 2);
		return jsapiTicket;
	}
	
    /**
     * @Description:获取生成二维码所用的ticket
     * @return
     * @throws Exception     
     * @return String   返回类型
     * @author Qiu
     */ 
    public static String getQRCodeTicket(long userId){

        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+AccessTokenThread.accessToken.getToken();
        logger.debug("getQRCodeTicket url===>"+url);
        logger.debug("getQRCodeTicket weixinId===>"+userId);
        
        String myjson = "";
        try {
            Map<String, Object> submap1=new HashMap<String, Object>();
            submap1.put("scene_id", userId);
            Map<String, Object> submap2=new HashMap<String, Object>();
            submap2.put("scene", submap1);
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("action_name","QR_LIMIT_SCENE");
            map.put("action_info",submap2);
            
            String json=JacksonHelper.toJSON(map);
            System.out.println(json);
            myjson = HttpRequestHelper.postData(url, json,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jo = JSONObject.fromObject(myjson);
        logger.debug("ticket jsonObject:"+jo);
        String qrTicket = (String) jo.get("ticket");
        return qrTicket;
    }

	public static Map<String, String> sign(String jsapi_ticket,	String nonce_str, String timestamp, String url) {

		logger.debug("[ sign(String jsapi_ticket,String nonce_str,String timestamp, String url) ]");
		Map<String, String> ret = new HashMap<String, String>();
		String string1;
		String signature = "";

		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str
				+ "&timestamp=" + timestamp + "&url=" + url;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

	public static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	/**
	 * 创建菜单
	 * @param menu        菜单实例
	 * @param accessToken 有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	// 菜单创建（POST）
	public static int createMenu(Menu menu) {

		logger.debug("[ createMenu(Menu menu, String accessToken) ]");
		String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		int result = 0;

		// 拼装创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", AccessTokenThread.accessToken.getToken());
		System.out.println("");
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		logger.debug("createMenu json string : " + jsonMenu);
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				logger.error("创建菜单失败 errcode:{} errmsg:{}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
			}
		}

		return result;
	}
	
	
    public static void weixinPayPrepare(HttpSession session, HttpServletRequest request){
        
        logger.debug("[ weixinPayPrepare ]");
        //AccessToken at = AccessTokenThread.accessToken;
        String ticket =  JsapiTicketThread.jsapiTicket.getTicket();
        session.setAttribute("appId", WeixinUtils.appId);
        
        String nonce_str=Sha1Util.getNonceStr();
        String timestamp=Sha1Util.getTimeStamp(); 
        String url="";
        if(request.getQueryString()==null){
            url=request.getRequestURL().toString();
        }else {
            url=request.getRequestURL().toString()+"?"+request.getQueryString();
        }
        Map<String, String> ret = WeixinUtils.sign(ticket,nonce_str,timestamp,url);
        session.setAttribute("map", ret);            
    }
	
    /**
     * description: 解析微信通知xml
     */
    @SuppressWarnings({ "unused", "rawtypes", "unchecked" })
    public static Map parseXmlToList2(String xml) {
        Map retMap = new HashMap();
        try {
            StringReader read = new StringReader(xml);
            // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
            InputSource source = new InputSource(read);
            // 创建一个新的SAXBuilder
            SAXBuilder sb = new SAXBuilder();
            // 通过输入源构造一个Document
            Document doc = (Document) sb.build(source);
            Element root = doc.getRootElement();// 指向根节点
            List<Element> es = root.getChildren();
            if (es != null && es.size() != 0) {
                for (Element element : es) {
                    retMap.put(element.getName(), element.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retMap;
    }
    
	/*
	 * public static Map<String, String> sign(String jsapi_ticket, String url) {
	 * Map<String, String> ret = new HashMap<String, String>(); String nonce_str
	 * = create_nonce_str(); String timestamp = create_timestamp(); String
	 * string1; String signature = "";
	 * 
	 * //注意这里参数名必须全部小写，且必须有序 string1 = "jsapi_ticket=" + jsapi_ticket +
	 * "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
	 * System.out.println(string1);
	 * 
	 * try { MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	 * crypt.reset(); crypt.update(string1.getBytes("UTF-8")); signature =
	 * byteToHex(crypt.digest()); } catch (NoSuchAlgorithmException e) {
	 * e.printStackTrace(); } catch (UnsupportedEncodingException e) {
	 * e.printStackTrace(); }
	 * 
	 * ret.put("url", url); ret.put("jsapi_ticket", jsapi_ticket);
	 * ret.put("nonceStr", nonce_str); ret.put("timestamp", timestamp);
	 * ret.put("signature", signature);
	 * 
	 * return ret; }
	 * 
	 * private static String byteToHex(final byte[] hash) { Formatter formatter
	 * = new Formatter(); for (byte b : hash) { formatter.format("%02x", b); }
	 * String result = formatter.toString(); formatter.close(); return result; }
	 * 
	 * private static String create_nonce_str() { return
	 * UUID.randomUUID().toString(); }
	 * 
	 * private static String create_timestamp() { return
	 * Long.toString(System.currentTimeMillis() / 1000); }
	 */

}
