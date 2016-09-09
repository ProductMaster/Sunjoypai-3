package com.taoists.common.bean;

public class DBInfo {

	public static final String USER_TOTAL_COUNT = "SELECT * FROM v_user_total_count";
	public static final String USER_CONSUMPTION_LEVEL = "SELECT * FROM user_consumption_level";
	public static final String USER_ACTIVITY_LEVEL	= "SELECT * FROM user_activity_level";
	public static final String USER_REPEATBUYING_LEVEL = "SELECT * FROM user_repeatbuying_level";
	public static final String USER_SOURCE_SETTING = "SELECT * FROM user_source_setting"; 
	public static final String SQ_MEDIA = "SELECT * FROM sq_media"; 
	public static final String SQ_MEDIA_TYPE_SETTING = "SELECT * FROM sq_media_type_setting"; 
	
	public static final String ORDER_SUCCESS_RATIO = "SELECT * FROM order_status_setting WHERE id in(4,5,7,8) ";
	public static final String ZONE_PROVINCE = "SELECT zone_no, zone_name FROM zone_code WHERE level = 1";
	
	public static final String PRODUCT_SALE_SUMMARY_GLOBAL="select IFNULL(SUM(qty),0) AS global_qty, IFNULL(SUM(sub_price),0.0) AS global_price, IFNULL(COUNT(order_id),0) AS global_order_count, IFNULL(COUNT(user_id),0) AS global_user_count FROM v_orderitems_finish ";
	/**----- 数据字典 -----**/
    public  static final String DATADICT_ORDER_STATUS_NORMAL =  "SELECT * FROM sys_data_dict where category_id=6 and value in(1, 2, 3, 9, 10, 11, 12, 13)";
	
}
