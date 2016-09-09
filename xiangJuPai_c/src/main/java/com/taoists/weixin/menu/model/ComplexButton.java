package com.taoists.weixin.menu.model;
/**
 *   
 *   
 *   @author George 
 *   E-mail:lendun@163.com
 *   @version 创建时间： 2013-11-15 上午10:22:112013-11-15
 *
 */
public class ComplexButton extends Button{

	private Button[] sub_button;  
	  
    public Button[] getSub_button() {  
        return sub_button;  
    }  
  
    public void setSub_button(Button[] sub_button) {  
        this.sub_button = sub_button;  
    }  
}
