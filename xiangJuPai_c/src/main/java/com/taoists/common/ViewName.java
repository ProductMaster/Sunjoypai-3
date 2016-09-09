package com.taoists.common;

/**
 * @author rubys
 * @since 2012-5-30
 */
public enum ViewName {

	list("-list"), insert("-insert"), show("-show"), edit("-edit"), redirect("redirect:");

	private String value;

	private ViewName(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
