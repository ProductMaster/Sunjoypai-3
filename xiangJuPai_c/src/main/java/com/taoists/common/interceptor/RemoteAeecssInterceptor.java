/**
 * 
 */
package com.taoists.common.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.taoists.common.util.StringUtils;
import com.taoists.common.util.ValidPassportUtils;

/**
 * 类说明
 * 
 * @author George
 * @date 2015-1-23
 */
public class RemoteAeecssInterceptor extends HandlerInterceptorAdapter {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("unchecked")
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		/**
		String servletPath = request.getServletPath();  
        for(String urlPattern : excludeUrlPatterns){  
            if(pathMatcher.match(urlPattern,servletPath)){  
                return true;  
            }  
        }  
        HttpSession session = request.getSession();  
        if(!Constants.CORRECT.equals(session.getAttribute(Constants.USER_NAME))){  
            response.sendRedirect(request.getContextPath() +"/login.jsp");  
            return false;  
        }    
        return true;
        **/		
		
		String validCode = (String) WebUtils.getSessionAttribute(request,	"validcode");
		if (StringUtils.isNoEmpty(validCode)) {
			return ValidPassportUtils.comparePassport(validCode);
		}

		return false;
	}
	
	/** ----------------------------  helper ------------------------------ **/
	
	private List<String> excludeUrlPatterns; 
	private PathMatcher pathMatcher = new AntPathMatcher();  
	
	public void setExcludeUrlPatterns(List<String> excludeUrlPatterns) {  
        this.excludeUrlPatterns = excludeUrlPatterns;  
    }  
}
