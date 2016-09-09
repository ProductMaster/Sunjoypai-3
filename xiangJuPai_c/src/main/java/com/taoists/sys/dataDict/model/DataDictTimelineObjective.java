package com.taoists.sys.dataDict.model;

import java.util.ArrayList;

/**
 *   @author George 
 *   E-mail:lendun@163.com
 *   @version 创建时间： 2013-10-12 下午09:46:422013-10-12
 *
 */
public class DataDictTimelineObjective {

	ArrayList<Long> ids;
	ArrayList<String> titles;
	ArrayList<String> parameters;
	
	public DataDictTimelineObjective(ArrayList<Long> i, ArrayList<String> t, ArrayList<String> p){
		ids = i;
		titles = t;
		parameters = p;
	}
	
	public ArrayList<String> getTitles() {
		return titles;
	}
	public void setTitles(ArrayList<String> titles) {
		this.titles = titles;
	}
	public ArrayList<String> getParameters() {
		return parameters;
	}
	public void setParameters(ArrayList<String> parameters) {
		this.parameters = parameters;
	}

	public ArrayList<Long> getIds() {
		return ids;
	}

	public void setIds(ArrayList<Long> ids) {
		this.ids = ids;
	}
	
	
}
