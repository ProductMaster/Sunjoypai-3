package com.taoists.common.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CharacterEncodingFilter implements Filter {
	private String encoding = null;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpreq = (HttpServletRequest) request;
		if (httpreq.getMethod().equalsIgnoreCase("get")) {
			Map<String, String[]> paramMap = request.getParameterMap();
			
			Set<String> keySet = paramMap.keySet();
			for (String key : keySet) {
				String[] paramArray = (String[]) paramMap.get(key);
				for (int i = 0; i < paramArray.length; i++) {
					paramArray[i] = new String(
							paramArray[i].getBytes("iso-8859-1"), encoding);
				}
			}
		} else {
			request.setCharacterEncoding(encoding);
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("encoding");
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

}
