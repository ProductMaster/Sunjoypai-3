package com.taoists.common.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Sets;
import com.taoists.common.ViewName;
import com.taoists.sys.common.Module;

/**
 * @author rubys@vip.qq.com
 * @since 2012-8-9
 */

public abstract class CommonFrameworkController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected String show = "show";
	protected String edit = "edit";
	protected String update = "update";
	protected Set<String> methodMappings = Sets.newHashSet(show, edit, update);

	protected abstract String getModule();

	protected String forward(String viewName) {
		String path = this.getClass().getAnnotation(RequestMapping.class)
				.value()[0];
		return getModule() + path.replaceAll("-", "") + "/" + viewName;
	}

	protected String forward(ViewName viewName) {
		String path = this.getClass().getAnnotation(RequestMapping.class)
				.value()[0];
		return getModule() + path.replaceAll("-", "") + path
				+ viewName.getValue();
	}

	protected String forward(Module module, String path, ViewName viewName) {
		return module.getName() + path.replaceAll("-", "") + path
				+ viewName.getValue();
	}

	protected String redirect() {
		return ViewName.redirect.getValue()
				+ this.getClass().getAnnotation(RequestMapping.class).value()[0];
	}

	protected String redirect(String action) {
		return ViewName.redirect.getValue() + action;
	}

	protected Long extractId(String requestURI) {
		for (String methodMapping : methodMappings) {
			methodMapping = "/" + methodMapping;

			if (StringUtils.contains(requestURI, methodMapping)) {
				String id = requestURI.substring(requestURI
						.indexOf(methodMapping) + 1);
				id = id.substring(id.indexOf("/"));
				if (id.indexOf("/") != -1) {
					id = id.substring(1);
				}
				if (NumberUtils.isNumber(id)) {
					return Long.valueOf(id);
				}
			}
		}
		return null;
	}

	protected Long extractId(String requestURI, String method) {
		if (StringUtils.contains(requestURI, method)) {
			String id = requestURI.substring(requestURI.indexOf(method)
					+ method.length());
			if (id.indexOf("/") != -1) {
				id = id.substring(0, id.indexOf("/"));
			}
			if (NumberUtils.isNumber(id)) {
				return Long.valueOf(id);
			}
		}
		return null;
	}

	protected void extractParams(HttpServletRequest request) {
		Enumeration<String> keys = request.getParameterNames();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String[] values = request.getParameterValues(key);
			String paramName = key.replaceAll("\\.", "_");
			if (values.length > 1) {
				Arrays.sort(values);
				request.setAttribute(paramName, values[0]);
				request.setAttribute(paramName + "_", values[1]);
				logger.debug("extractParam key[{}], value[{}]", paramName,
						values);
			} else {
				request.setAttribute(paramName, request.getParameter(key));
				logger.debug("extractParam key[{}], value[{}]", paramName,
						request.getParameter(key));
			}
		}
	}

	protected void addMethodMappings(Collection<String> methodMappings) {
		this.methodMappings.addAll(methodMappings);
	}



/*	protected Employee getEmployee(Model model) {
		Object value = model.asMap().get("currentEmployee");
		if (value != null && value instanceof Employee) {
			return (Employee) value;
		}
		throw new IllegalStateException();
	}*/
}
