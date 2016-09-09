package com.taoists.common.controller;
/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-6-10 上午01:16:09  
 *  
 */
public class SimpleStatisObject {

	private String label;
	private String aStr;
	private String bStr="0";
	private String cStr="0";
	private int aNum;
	private int bNum;
	
	public SimpleStatisObject(){}
	
	public SimpleStatisObject(String label, String aStr){
		this.label = label;
		this.aStr = aStr;
	}
	
	public SimpleStatisObject(String label, String aStr, String bStr){
		this.label = label;
		this.aStr = aStr;
		this.bStr = bStr;
	}
	
	public SimpleStatisObject(String label, String aStr, String bStr, String cStr){
		this.label = label;
		this.aStr = aStr;
		this.bStr = bStr;
		this.cStr = cStr;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	public String getaStr() {
		return aStr;
	}

	public void setaStr(String aStr) {
		this.aStr = aStr;
	}

	public String getbStr() {
		return bStr;
	}

	public void setbStr(String bStr) {
		this.bStr = bStr;
	}

	public int getaNum() {
		return aNum;
	}

	public void setaNum(int aNum) {
		this.aNum = aNum;
	}

	public int getbNum() {
		return bNum;
	}

	public void setbNum(int bNum) {
		this.bNum = bNum;
	}


	
}
