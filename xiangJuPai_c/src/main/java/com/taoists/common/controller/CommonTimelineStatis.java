package com.taoists.common.controller;

import java.util.ArrayList;
import java.util.List;


/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-5-29 下午03:40:49  
 *  
 */
public class CommonTimelineStatis {

	// 行标题
	private ArrayList<String> timelineRowTitles = new ArrayList<String>();
	// 列标题
	private ArrayList<String> timelineColTitles = new ArrayList<String>();
	// 单元值一
	ArrayList<String[]> timelineCellValues = new ArrayList<String[]>() ;
	// 单元值二
	ArrayList<String[]> timeline2CellValues = new ArrayList<String[]>() ;
	// 一维结果值
	ArrayList<SimpleStatisObject> timelineArrayValues = new ArrayList();
	
	public ArrayList<String> getTimelineRowTitles() {
		return timelineRowTitles;
	}
	public void setTimelineRowTitles(ArrayList<String> timelineRowTitles) {
		this.timelineRowTitles = timelineRowTitles;
	}
	public ArrayList<String> getTimelineColTitles() {
		return timelineColTitles;
	}
	public void setTimelineColTitles(ArrayList<String> timelineColTitles) {
		this.timelineColTitles = timelineColTitles;
	}
	public ArrayList<String[]> getTimelineCellValues() {
		return timelineCellValues;
	}
	public void setTimelineCellValues(ArrayList<String[]> timelineCellValues) {
		this.timelineCellValues = timelineCellValues;
	}
	public ArrayList<String[]> getTimeline2CellValues() {
		return timeline2CellValues;
	}
	public void setTimeline2CellValues(ArrayList<String[]> timeline2CellValues) {
		this.timeline2CellValues = timeline2CellValues;
	}
	public ArrayList<SimpleStatisObject> getTimelineArrayValues() {
		return timelineArrayValues;
	}
	public void setTimelineArrayValues(
			ArrayList<SimpleStatisObject> timelineArrayValues) {
		this.timelineArrayValues = timelineArrayValues;
	}
	
}
