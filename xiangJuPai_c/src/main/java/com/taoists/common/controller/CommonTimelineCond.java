package com.taoists.common.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.taoists.common.Const;
import com.taoists.common.util.CalendarUtils;
import com.taoists.common.util.DateUtils;

public class CommonTimelineCond {

	protected String requestType = "list";
	
	public static final int TIMELINE_UNIT_YEAR  = 1;
	public static final int TIMELINE_UNIT_MONTH = 2;
	public static final int TIMELINE_UNIT_WEEK  = 3;
	public static final int TIMELINE_UNIT_DAY   = 4;
	public static final int TIMELINE_UNIT_HOUR  = 5;
	
	private String startDateStr;    						// 映射表单起始时间控件
	private String endDateStr;      						// 映射表单结束时间控件
	private int timelineUnit;								// 粒度(年月周日时)，映射表单的时间粒度下拉列表
	private int totalTimelineStep;							// 步长总长度，映射表单的长度下拉列表
	private int offset;										// 数据库保存的偏移值
	
	private Calendar	dateBaseline = Calendar.getInstance();			// 内部运算的统计时间起始点
	private Calendar    currentTimelineFrom = Calendar.getInstance();	// 当前时间段起始时间
	private Calendar    currentTimelineTo = Calendar.getInstance();		// 当前时间段结束时间
	private int 		currentTimelineStep ;							// 当前步长值	
	private String  	dateTitle;										// 当前日期的字串
	


	private String 		seleClause;								// select主句
	private String  	whereClause = Const.NULL_VALUE_STRING;	// where子句
	private ArrayList<String> objectiveValues;					// 目标值
	private ArrayList<String> objectiveTitles;					// 
	private String 		dateField;								// 起始时间范围字段名
	
	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public int getTimelineUnit() {
		return timelineUnit;
	}

	public void setTimelineUnit(int timelineUnit) {
		this.timelineUnit = timelineUnit;
	}

	public int gettotalTimelineStep() {
		return totalTimelineStep;
	}

	public void setTotalTimelineStep(int totalTimelineStep) {
		this.totalTimelineStep = totalTimelineStep;
		
		switch(timelineUnit){
			case TIMELINE_UNIT_YEAR	:	
				if(totalTimelineStep > 10) this.totalTimelineStep = 10;
				break;
			case TIMELINE_UNIT_MONTH	:	
				if(totalTimelineStep > 24) this.totalTimelineStep = 24;
				break;
			case TIMELINE_UNIT_WEEK	:	
				if(totalTimelineStep > 31) this.totalTimelineStep = 31;
				break;
			case TIMELINE_UNIT_DAY	:	
				if(totalTimelineStep > 31) this.totalTimelineStep = 31;
				break;
			case TIMELINE_UNIT_HOUR	:	
				if(totalTimelineStep > 24) this.totalTimelineStep = 24;
				break;
		}			
	}
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getStartDateStr() {
		return startDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	
	public boolean hasNextTimeline(){
		return currentTimelineStep > totalTimelineStep ? false: true;
	}
	
	public void nextTimeline(){
		setCurrentTimelineStep(currentTimelineStep+1);
	}
	
	public Calendar getDateBaseline() {
		return dateBaseline;
	}

	public void setDateBaseline(Calendar dateBaseline) {
		this.dateBaseline = dateBaseline;
	}

	public int getCurrentTimelineStep() {
		return currentTimelineStep;
	}

	public String getSeleClause() {
		return seleClause;
	}

	public void setSeleClause(String seleClause) {
		this.seleClause = seleClause;
	}
	
	public String getWhereClause() {
		return whereClause;
	}

	public void setWhereClause(String whereClause) {
		this.whereClause = whereClause;
	}

	public int getTotalTimelineStep() {
		return totalTimelineStep;
	}

	public List<String> getObjectiveValues() {
		return objectiveValues;
	}

	public void setObjectiveValues(ArrayList<String> objectiveValues) {
		this.objectiveValues = objectiveValues;
	}

	public ArrayList<String> getObjectiveTitles() {
		return objectiveTitles;
	}

	public void setObjectiveTitles(ArrayList<String> objectiveTitles) {
		this.objectiveTitles = objectiveTitles;
	}

	public String getDateField() {
		return dateField;
	}

	public void setDateField(String dateField) {
		this.dateField = dateField;
	}

	public String getDateTitle() {
		return dateTitle;
	}

	public void setDateTitle(String dateTitle) {
		this.dateTitle = dateTitle;
	}
	
	public Calendar getCurrentTimelineFrom() {
		return currentTimelineFrom;
	}

	public void setCurrentTimelineFrom(Calendar currentTimelineFrom) {
		this.currentTimelineFrom = currentTimelineFrom;
	}

	public Calendar getCurrentTimelineTo() {
		return currentTimelineTo;
	}

	public void setCurrentTimelineTo(Calendar currentTimelineTo) {
		this.currentTimelineTo = currentTimelineTo;
	}
	
	public void setStartDateStr(String startDateStr) throws Exception{
		
		this.startDateStr = startDateStr;
		dateBaseline.setTime(DateUtils.string2Datetime(startDateStr+" 00:00:00"));
		
		switch(timelineUnit){
			case TIMELINE_UNIT_YEAR	:				
				dateBaseline.set(Calendar.MONTH, 0);
				dateBaseline.set(Calendar.DATE, 1);
				break;
			case TIMELINE_UNIT_MONTH :	
				dateBaseline.set(Calendar.MONTH, 0);
				dateBaseline.set(Calendar.DATE, 1);
				break;
			case TIMELINE_UNIT_WEEK	:	
				// todo
				break;
			case TIMELINE_UNIT_DAY	:	
				dateBaseline.set(Calendar.DATE, 1);
				break;
			case TIMELINE_UNIT_HOUR	:	
				
				break;
		}	
	}
	

	public void setCurrentTimelineStep(int currentTimelineStep) {
		this.currentTimelineStep = currentTimelineStep;
		
		switch(timelineUnit){
			case TIMELINE_UNIT_YEAR	:				
				currentTimelineFrom.setTime(dateBaseline.getTime());
				currentTimelineFrom.add(Calendar.YEAR, currentTimelineStep-1);
				currentTimelineTo.setTime(dateBaseline.getTime());
				currentTimelineTo.add(Calendar.YEAR, currentTimelineStep);
				currentTimelineTo.add(Calendar.SECOND, -1);				
				
				dateTitle = String.valueOf( currentTimelineFrom.get(Calendar.YEAR) );				
				break;
			case TIMELINE_UNIT_MONTH :	
				currentTimelineFrom.setTime(dateBaseline.getTime());
				currentTimelineFrom.add(Calendar.MONTH, currentTimelineStep-1);
				currentTimelineTo.setTime(dateBaseline.getTime());
				currentTimelineTo.add(Calendar.MONTH, currentTimelineStep);
				currentTimelineTo.add(Calendar.SECOND, -1);				
				
				dateTitle = CalendarUtils.getYYYYMM(currentTimelineFrom);				
				break;
			case TIMELINE_UNIT_WEEK	:	
				// todo
				// startDate = dateBaseline.getTime();
				break;
			case TIMELINE_UNIT_DAY	:	
				currentTimelineFrom.setTime(dateBaseline.getTime());
				currentTimelineFrom.add(Calendar.DATE, currentTimelineStep-1);
				currentTimelineTo.setTime(dateBaseline.getTime());
				currentTimelineTo.add(Calendar.DATE, currentTimelineStep);
				currentTimelineTo.add(Calendar.SECOND, -1);				
				
				dateTitle = CalendarUtils.getYYYYMMDD(currentTimelineFrom);				
				break;
			case TIMELINE_UNIT_HOUR	:	
				currentTimelineFrom.setTime(dateBaseline.getTime());
				currentTimelineFrom.add(Calendar.HOUR_OF_DAY, currentTimelineStep-1);
				currentTimelineTo.setTime(dateBaseline.getTime());
				currentTimelineTo.add(Calendar.HOUR_OF_DAY, currentTimelineStep);
				currentTimelineTo.add(Calendar.SECOND, -1);				
				
				dateTitle = String.valueOf( currentTimelineFrom.get(Calendar.HOUR_OF_DAY) );				
				break;
		}
	}


	
}
