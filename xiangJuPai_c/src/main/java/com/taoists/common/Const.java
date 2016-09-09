package com.taoists.common;

public class Const {

	/**-------   NULL值     ------- **/
	public final static int NULL_VALUE_INT = -99;
	public final static long NULL_VALUE_LONG = -99;
	public final static double NULL_VALUE_DOUBLE = -99;
	public final static String NULL_VALUE_STRING = "";
	public final static String NULL_VALUE_DISPLAY = "-";
	
	/**-------   ZERO值     ------- **/
	public final static int ZERO_VALUE_INT = 0;
	public final static long ZERO_VALUE_LONG = 0;
	
	/**-------   最大值     ------- **/
	public final static double MAX_VALUE_LONG = 99999999;
	
	/**-------   一天初秒和最后一秒的字符串     ------- **/  
	public final static String ZERO_TIME_STRING = " 00:00:00";
	public final static String FINAL_TIME_STRING = " 23:59:59";

	/**-------   BOOLEAN值     ------- **/
	public final static int TRUE = 1;
	public final static int FALSE = 0;

	/**-------   禁用启用     ------- **/
	public final static int ENABLE = 1;
	public final static int DISABLE = 0;
	
	/** ------   是否             -------- **/
	public final static int YES = 1;
	public final static int NO = 0;
	
	/** --------帐号状态    ----------**/
	public static final int USER_ACTIVATION= 1; // 激活
	public static final int USER_FREEZE = 0;    // 冻结
	public static final int USER_DISABLE = -1;  // 禁用
	
	/** -------  性别    -----------**/
	public static final int SEX_MAN =1;      // 男
	public static final int SEX_WOMAN = 0;   // 女
	public static final int SEX_UNKNOW = -1; // 未知
	
	/** -------  时间    ----------**/
	public static final int DAY_OF_YEAR = 365;
	public static final long YEAR_MILLLIS = 31536000000L ;
	
	/** -------  统计数据类型 -----**/
	public static final int DATA_DETAIL = 1;
	public static final int DATA_OUTER  = 0;
	public static final int DATA_TOTAL  = -1;
	public static final int DATA_INVALID  = -2;
	
	//审核状态
	public static final int APPLICATION_NO_SUBMit= 0; //未提交
	public static final int APPLICATION_NEW = 1;    // 新申请
	public static final int APPLICATION_PASS = 2;    // 通过
	public static final int APPLICATION_NO_PASS = -1;   //未通过
  
	public String getApplicationStatusStr(int appStatus) {
	    String strInfo = "";
	    switch (appStatus) {
	        case APPLICATION_NO_SUBMit:
	            strInfo = "未提交";
	            break;
	        case APPLICATION_NEW:
	            strInfo = "待审核";
	            break;
	        case APPLICATION_PASS:
	            strInfo = "已审核";
	            break;
	        case APPLICATION_NO_PASS:
	            strInfo = "未通过";
	            break;
	    }
	    return strInfo;
	}
	
	
	/** --------  图片   ---------**/
	public static final String PIC_PATH = "/images/";
	public static final String ARROW_RED   = PIC_PATH+"arrow_red.png";
	public static final String ARROW_GREEN = PIC_PATH+"arrow_green.png";
	public static final String ARROW_BLACK = PIC_PATH+"arrow_black.png";
	
	/** --------  操作  ---------**/
	public static final String insert = "insert";
	public static final String update = "update";
	public static final String delete = "delete";
	public static final String nonParentUpdate = "non_parent_update";

	/** --------  application  --------变量名 **/
	public static final String APP_MENU = "appMenus";
	public static final String APP_ACCESS_TOKEN = "accessToken";
	public static final String APP_TICKET = "jsapiTicket";
	
	/** --------  域名 ----------**/
	public static final String DOMAIN_NAME = "http://m.5qbb.cn";
		   
    /** 文件夹包含生成图片的数量:系统开始投用后，此参数不允许修改 **/
    public static final int FOLDER_PIC_NUM = 1000;
	
	public static final String getSexPic(int i){
		String retVal = "";
		switch(i)
		{
			case SEX_MAN:
				retVal = "<a href='#' class='sweet-tooltip' data-style-tooltip='tooltip-mini-slick' data-text-tooltip='男性'><img src=" + "/images/ico_man.png"+ " width=16 height=16></a>";
				break;
			case SEX_WOMAN:
				retVal = "<a href='#' class='sweet-tooltip' data-style-tooltip='tooltip-mini-slick' data-text-tooltip='女性'><img src=" + "/images/ico_woman.png"+ " width=16 height=16></a>";
				break;
			case SEX_UNKNOW:
				retVal = "<a href='#' class='sweet-tooltip' data-style-tooltip='tooltip-mini-slick' data-text-tooltip='未填写'><img src=" + "/images/ico_unknown.png"+ " width=16 height=16></a>";
				break;	
			default:
				retVal = "";
		}
		return retVal;
	}
	
	public static final String getAccountActivePic(int i){
		String retVal = "";
		switch(i)
		{
			case USER_ACTIVATION:
				retVal = "";
				break;
			case USER_FREEZE:
				retVal = "<a href='#' class='sweet-tooltip' data-style-tooltip='tooltip-mini-slick' data-text-tooltip='帐号冻结'><img src=" + "/images/ico_lock-16.png"+ " width=16 height=16></a>";
				break;
			default:
				retVal = "";
		}
		return retVal;
	}
	
	public static final String getMemoPic(String memo){
		String retVal = "<img src=" + "/images/ico_comment.png"+ " width=16 height=16>";
		if( memo == null || memo.length()==0){
			retVal = "";
		}
		return retVal;
	}
}
