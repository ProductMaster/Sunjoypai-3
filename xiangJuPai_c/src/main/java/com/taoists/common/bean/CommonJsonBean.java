package com.taoists.common.bean;

import java.util.ArrayList;
import java.util.List;

/** 
 * 类说明 
 * @author George 
 * @date 2015-1-10  
 */
public class CommonJsonBean {

	public static final String MSG_SUCCES = "OK";
	public static final int CODE_SUCCESS = 0;
	public static final int CODE_FAIL = 1;
	
	private List datas;
	private int code = CODE_SUCCESS;
	private String msg = MSG_SUCCES;
	
	public CommonJsonBean(){
		datas = new ArrayList();
		msg = this.MSG_SUCCES;
		code = this.CODE_SUCCESS;		
	}

	public List getDatas() {
		return datas;
	}

	public void setDatas(List datas) {
		this.datas = datas;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
