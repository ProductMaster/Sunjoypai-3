package com.taoists.common.util;

import java.util.Enumeration;
import java.util.Map;
import java.util.SortedMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;

import com.google.common.collect.Maps;

/**
 * @author rubys
 * @since 2012-5-31
 */
public class ServletUtils {

	public static void setExpiresHeader(HttpServletResponse response,
			long expiresSeconds) {
		// Http 1.0 header
		response.setDateHeader("Expires", System.currentTimeMillis()
				+ expiresSeconds * 1000);
		// Http 1.1 header
		response.setHeader("Cache-Control", "private, max-age="
				+ expiresSeconds);
	}

	public static void setDisableCacheHeader(HttpServletResponse response) {
		// Http 1.0 header
		response.setDateHeader("Expires", 1L);
		response.addHeader("Pragma", "no-cache");
		// Http 1.1 header
		response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
	}

	@SuppressWarnings("rawtypes")
	public static Map<String, Object> getParametersStartingWith(
			ServletRequest request, String prefix) {
		Assert.notNull(request, "Request must not be null");
		Enumeration paramNames = request.getParameterNames();
		Map<String, Object> params = Maps.newTreeMap();
		if (prefix == null) {
			prefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					params.put(unprefixed, values);
				} else {
					params.put(unprefixed, values[0]);
				}
			}
		}

		paramNames = request.getAttributeNames();
		params = Maps.newTreeMap((SortedMap<String, ? extends Object>) params);
		if (prefix == null) {
			prefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String value = request.getAttribute(paramName).toString();
				if (value == null || value.length() == 0) {
					// Do nothing, no values found at all.
				} else {
					params.put(unprefixed, value);
				}
			}
		}
		return params;
	}
	
	public static String getIpAddr(HttpServletRequest request) {      
	      String ip = request.getHeader("x-forwarded-for");      
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	          ip = request.getHeader("X-Real-IP");      
	      }      
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	          ip = request.getHeader("REMOTE-HOST");      
	       }      
	     if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	           ip = request.getRemoteAddr();      
	      }      
	     return ip;      
	}  

}
