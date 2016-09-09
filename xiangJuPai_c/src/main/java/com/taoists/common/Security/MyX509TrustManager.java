package com.taoists.common.Security;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 *   @author George 
 *   E-mail:lendun@163.com
 *   @version 创建时间： 2013-11-15 上午10:46:162013-11-15
 *
 */
public class MyX509TrustManager implements X509TrustManager {

	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {  
    }  
        
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {  
    }  
        
    public X509Certificate[] getAcceptedIssuers() {  
        return null;  
    }  
}
