package com.taoists.sys.common;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-2
 */
public enum Module {

	dept("/dept"), base("/base"), code("/code"), ias("/ias"), sys("/sys");

	String name;

	Module(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
 