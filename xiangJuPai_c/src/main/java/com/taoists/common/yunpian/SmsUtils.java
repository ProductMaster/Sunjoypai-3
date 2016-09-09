package com.taoists.common.yunpian;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmsUtils {

    private static String yunpian_key = "592882b274ac2cc53d5dfb7cc4444ac9";
    private static String signature = "【多燕瘦】";
    
    // 查账户信息的http地址
    private static String URI_GET_USER_INFO = "http://yunpian.com/v1/user/get.json";

    //通用发送接口的http地址
    private static String URI_SEND_SMS = "http://yunpian.com/v1/sms/send.json";

    // 模板发送接口的http地址
    private static String URI_TPL_SEND_SMS = "http://yunpian.com/v1/sms/tpl_send.json";

    // 发送语音验证码接口的http地址
    private static String URI_SEND_VOICE = "http://yunpian.com/v1/voice/send.json";

    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";
    
    public static void main(String[] args)throws IOException, URISyntaxException{
      //修改为您要发送的手机号
        String mobile = "13671941376,18516145119";

        /**************** 查账户信息调用示例 *****************/
        System.out.println(SmsUtils.getUserInfo());

        /**************** 使用通用接口发短信(推荐) *****************/
        //设置您要发送的内容(内容必须和某个模板匹配。以下例子匹配的是系统提供的1号模板）
        String text = "【云片网】您的验证码是12345";
        //发短信调用示例
        System.out.println(SmsUtils.sendSms(text, mobile));
    }

    public static void mains() throws IOException, URISyntaxException {

        //修改为您要发送的手机号
        String mobile = "13671941376,18516145119";

        /**************** 查账户信息调用示例 *****************/
        System.out.println(SmsUtils.getUserInfo());

        /**************** 使用通用接口发短信(推荐) *****************/
        //设置您要发送的内容(内容必须和某个模板匹配。以下例子匹配的是系统提供的1号模板）
        String text = "【云片网】您的验证码是12345";
        //发短信调用示例
        System.out.println(SmsUtils.sendSms(text, mobile));

        /**************** 使用模板接口发短信(不推荐，建议使用通用接口) *****************/
        //设置模板ID，如使用1号模板:【#company#】您的验证码是#code#
        //long tpl_id = 1;
        //设置对应的模板变量值
        //如果变量名或者变量值中带有#&=%中的任意一个特殊符号，需要先分别进行urlencode编码
        //如code值是#1234#,需作如下编码转换
        //String codeValue = URLEncoder.encode("#1234#", ENCODING);
        //String tpl_value = "#code#=" + codeValue + "&#company#=云片网";
        //模板发送的调用示例
        //System.out.println(SmsUtils.tplSendSms(tpl_id, tpl_value, mobile));

        /**************** 使用接口发语音验证码 *****************/
        //String code = "1234";
        //System.out.println(SmsUtils.sendVoice(mobile ,code));
    }

    /**
     * 取账户信息
     *
     * @return json格式字符串
     * @throws java.io.IOException
     */
    public static String getUserInfo() throws IOException, URISyntaxException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", yunpian_key);
        return post(URI_GET_USER_INFO, params);
    }

    /**
     * 通用接口发短信
     *
     * @param apikey apikey
     * @param text   　短信内容
     * @param mobile 　接受的手机号
     * @return json格式字符串
     * @throws IOException
     */
    public static int sendSms(String text, String mobile) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", yunpian_key);
        params.put("text", signature + text);
        params.put("mobile", mobile);
        JSONObject jsonObject = JSONObject.fromObject(post(URI_SEND_SMS, params));
        return jsonObject.getInt("code");        
    }

    /**
     * 通过模板发送短信(不推荐)
     *
     * @param apikey    apikey
     * @param tpl_id    　模板id
     * @param tpl_value 　模板变量值
     * @param mobile    　接受的手机号
     * @return json格式字符串
     * @throws IOException
     */
    public static String tplSendSms(long tpl_id, String tpl_value, String mobile) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", yunpian_key);
        params.put("tpl_id", String.valueOf(tpl_id));
        params.put("tpl_value", tpl_value);
        params.put("mobile", mobile);
        return post(URI_TPL_SEND_SMS, params);
    }

    /**
    * 通过接口发送语音验证码
    * @param apikey apikey
    * @param mobile 接收的手机号
    * @param code   验证码
    * @return
    */
    public static String sendVoice(String mobile, String code) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", yunpian_key);
        params.put("mobile", mobile);
        params.put("code", code);
        return post(URI_SEND_VOICE, params);
    }

    /**
     * 基于HttpClient 3.1的通用POST方法
     *
     * @param url       提交的URL
     * @param paramsMap 提交<参数，值>Map
     * @return 提交响应
     */
    public static String post(String url, Map<String, String> paramsMap) {
        HttpClient client = new HttpClient();
        try {
            PostMethod method = new PostMethod(url);
            if (paramsMap != null) {
                NameValuePair[] namePairs = new NameValuePair[paramsMap.size()];
                int i = 0;
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new NameValuePair(param.getKey(), param.getValue());
                    namePairs[i++] = pair;
                }
                method.setRequestBody(namePairs);
                HttpMethodParams param = method.getParams();
                param.setContentCharset(ENCODING);
            }
            client.executeMethod(method);
            return method.getResponseBodyAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
