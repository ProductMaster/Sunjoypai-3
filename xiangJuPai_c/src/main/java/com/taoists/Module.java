package com.taoists;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-2
 */
public enum Module {

	sys("/sys"), foundation("foundation"), business("/business"), perspective("/perspective");

	String name;

	Module(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
