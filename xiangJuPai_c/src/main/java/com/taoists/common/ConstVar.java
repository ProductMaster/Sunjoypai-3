package com.taoists.common; 

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstVar {
	
	// ConstVar
	//记录状态

	public final static int RECORD_STATUS_DISABLE   = -1;
	public final static int RECORD_STATUS_UNLIMITED = 0;
	public final static int RECORD_STATUS_READONLY  = 1;
	/**无理由退货期(天)**/
	public static final long SALE_BACK_DAY = 7;
	/**款到发货订单未付款自动取消(天)**/
	public static final int UNPAY_CANCEL_DAY = 7;
	
	/**无理由退货期(long)**/
	public static final long SALE_BACK_DAY_MILLS = 604800000l;
	public static final long ONE_DAY_MILLS = 86400000l;
	public static String SEPARATOR = File.separator;
	public final static String BASE_UPLOAD_PATH = "D:"+SEPARATOR+"work"+SEPARATOR+"TEB2C"; //基础上传根目录
	public final static String KUAIDI_URL = "http://api.kuaidi100.com";//快递Id
	public final static String KUAIDI_ID = "c4c61683191a5cfc";//快递Id
	public static final long CUSTOMER_ID_RETAIL_DEFAULT = -1 ;//零售客户id
	public static final String DEPTNO_HUJIAOZHONGXIN = "001005" ;//呼叫中心部门编号
	
//	public static String FREEMARKER_TEMPLATE_PATH = "";//不填即为tomcat项目部署路径-xp系统
	public static String FREEMARKER_TEMPLATE_PATH = "D:"+SEPARATOR+"server"+SEPARATOR+"apache-tomcat-6.0.32"+SEPARATOR+"webapps"+SEPARATOR+"TEB2C"+SEPARATOR+"html";//不填即为tomcat项目部署路径-xp系统
//	public static String FREEMARKER_TEMPLATE_PATH = SEPARATOR+"usr"+SEPARATOR+"local"+SEPARATOR+"apache-tomcat-6.0.28"+SEPARATOR+"webapps"+SEPARATOR+"CNRmall"+SEPARATOR+"html";//不填即为tomcat项目部署路径-linux系统
	public static String DOMAIN = "";

	public final static String DEFAULT_TEMPLATE="template_01";
	
	public final static boolean QTY_SETTING= false;
	
	public final static String PAGE_ROOT = SEPARATOR+"root";
	public final static String PAGE_HELP = SEPARATOR+"help";
	public final static String PAGE_COMPANY = SEPARATOR+"company";
	public final static String GONGGAO_BULLETIN = SEPARATOR+"bulletin";
	public final static String BRAND_PATH     = SEPARATOR+"brand";
	public final static String PRODUCT_PATH   = SEPARATOR+"product";
	
	public static final int SAVE_TO_NEW = 1;//提交并新增
	
	public final static String AD_XML_FILE_NAME   = "index_slide.xml";
	public final static String FLASH16_XML_FILE_NAME   = "flash16.xml";
	public final static String INDEX_CHANNEL_XML_FILE_NAME   = "index_channel.xml";
	
	public final static String STATIC_PAGE_SUFFIX = ".htm";
	//设置文章上传图片地址
	public final static String PATH_NEWS = BASE_UPLOAD_PATH +SEPARATOR+"images"+SEPARATOR+"news"; 
	//设置健康超市上传图片地址
	public final static String PATH_TEMARKET = BASE_UPLOAD_PATH +SEPARATOR+"images"+SEPARATOR+"temarket"; 
	//抓取记录初始值
	public static final int ACCEPT_COUNT=1;			//每次接受记录数
	public static final int ACCEPT_IF=0;			//是否接受  (0:不接受;1:接受)
	public static final int ACCEPT_MAX_COUNT = 10;	//当前最大处理数量
	public static final int ACCEPT_TIME=60;			//接受间隔时间
	
	//转义字符
    public final static String SCRIPT_SPIT_ONE = "/";
	
	public final static String STRING_TOKEN=":";

	public final static int TRUE = 1;
	public final static int FALSE = 0;

	public final static int ENABLE = 1;
	public final static int DISABLE = 0;

	public final static String ROOT_SYSCATE_NAME ="所有分类";
	public final static String ROOT_SYSCATE_NO ="000";
	public final static long ROOT_CATEGORY=0;
	
	
	public final static int NULL_VALUE = -99;
	public final static long NULL_LONG = -99;
	public final static int ZERO_VALUE = 0;
	public final static String NULL_STRING = "";
	
	
	public final static String INDEX_PATH = BASE_UPLOAD_PATH+SEPARATOR+"index"; //索引文件目录
	public final static String JS_UPLOAD_PATH = BASE_UPLOAD_PATH+SEPARATOR+"static"+SEPARATOR+"js"; //js存放目录
	public final static String PAGE_UPLOAD_PATH = BASE_UPLOAD_PATH+SEPARATOR+"page"; //商品图片上传目录
	public final static String FILE_UPLOAD_PATH = BASE_UPLOAD_PATH+SEPARATOR+"images"+SEPARATOR+"product"; //商品图片上传目录
	public final static String FILE_POINT_UPLOAD_PATH = BASE_UPLOAD_PATH +SEPARATOR+ "images"+SEPARATOR+"point"; //商品图片上传目录
	public final static String OTHER_UPLOAD_PATH = BASE_UPLOAD_PATH +SEPARATOR+"images"+SEPARATOR+"other"; //其他图片上传目录
	public final static String ACTIVE_UPLOAD_PATH = BASE_UPLOAD_PATH +SEPARATOR+ "images"+SEPARATOR+"active"; //活动图片上传目录
	public final static String IMPORT_UPLOAD_PATH=BASE_UPLOAD_PATH+SEPARATOR+"import"; //
	
	public final static String IMG_UPLOAD_PATH = BASE_UPLOAD_PATH +SEPARATOR+"images"; 
	public final static String VOD_UPLOAD_PATH = BASE_UPLOAD_PATH+SEPARATOR+"vod"; //视频存放目录 
	public final static String BRAND_UPLOAD_PATH = BASE_UPLOAD_PATH + SEPARATOR+"images"+SEPARATOR+"brand";         
	public final static String LOGO_UPLOAD_PATH = BASE_UPLOAD_PATH +SEPARATOR+"static"+SEPARATOR+"images"; //js存放目录
	public final static String LOGO_IMG="logo.jpg";
	public final static String AD_PATH   = BASE_UPLOAD_PATH + SEPARATOR+"adimage";
	public final static String DM_PATH   = BASE_UPLOAD_PATH + "\\userfiles\\dm";
	public final static String ICP_UPLOAD_PATH=BASE_UPLOAD_PATH+SEPARATOR+"icp"; //js存放目录
	public final static String ICP_FAIL="ICP.cer";
	public final static String IMG_MANAGE_UPLOAD_PATH=BASE_UPLOAD_PATH+SEPARATOR+"images"+SEPARATOR+"imgmanage"; //
	/** 商品原图片信息 **/
	
	public final static String PRODUCT_SRC_IMG="gallery";
	public final static String PRODUCT_SRC_IMG_ROOT="_src";
	public final static String PRODUCT_MICRO_IMG_ROOT="_micro";   //商品微图片前缀
	public final static int PRODUCT_MICRO_IMG_WIDTH=100;    //商品微图片宽度大小
	public final static int PRODUCT_MICRO_IMG_HEIGHT=100;   //商品微图片高度大小
	public final static String PRODUCT_SMALL_IMG_ROOT="_small";   //商品小图片前缀
	public final static int PRODUCT_SMALL_IMG_WIDTH=160;    //商品小图片宽度大小
	public final static int PRODUCT_SMALL_IMG_HEIGHT=160;   //商品小图片高度大小
	public final static String PRODUCT_MEDIUM_IMG_ROOT="_medium";   //商品中图片前缀
	public final static int PRODUCT_MEDIUM_IMG_WIDTH=310;    //商品中图片宽度大小
	public final static int PRODUCT_MEDIUM_IMG_HEIGHT=310;   //商品中图片高度大小
	
	public final static String LINKSLOGO_UPLOAD_PATH = BASE_UPLOAD_PATH + SEPARATOR+"images"+SEPARATOR+"links";  
	public final static String LINKSLOGO_IMG_UPLOAD_PATH="/images/links/"; 
	public final static String BRAND_IMG_UPLOAD_PATH="/images/brand/"; 
	public final static String PATH_PRODUCT_IMAGES="/images/product/";
	public final static String PATH_POINT_IMAGES="/images/point/";
	//文件存放策略
	public final static int MAX_FILE = 100;
	public static String getProductFolder(long productId){
		return (int)(productId / (ConstVar.MAX_FILE * ConstVar.MAX_FILE)) +"/" + (int)(productId / ConstVar.MAX_FILE) + "/" + productId; 
	}
	public static String getPointFolder(long giftId){
		
		return (int)(giftId / (ConstVar.MAX_FILE * ConstVar.MAX_FILE)) +"/" + (int)(giftId / ConstVar.MAX_FILE) + "/" + giftId; 
	}
	public final static String MAIL_LOG_FILE_NAME="mail.log";
	//邮件的标题
	public final static String PASS_DEALER_MAIL_SUBJECT = "通过网站经销商资格申请";
	public final static String FORGOT_MAIL_SUBJECT = "惠美惠:会员密码变更";
	public final static String FORGOT_PASSPORT_SUBJECT = "惠美惠:会员忘记密码";
	public final static int STATIS_RECENTLY_YEARS= 6;//在销售统计中最多查询最近6年的数据
	/**获取年份数据**/
	public static List getYearList(){
		List list = new ArrayList();
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		for(int i =year ; i>year -ConstVar.STATIS_RECENTLY_YEARS;i--){
			list.add(i);
		}
		return list;
	}
	public final static String DEPT_LIST_NAME = "deptList";
	
	public static String getFileNo() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("/yyyyMMdd/");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	
	
	
	//公司编码
	//每天初始订单号
	public final static long INITIAL_ORDER_NO=101;
	//====基本设置
	public static final int DEPT_ENCODING = 11; //公司部门编码
	public static final int EMP_ENCODING = 12; //公司员工编码
	public static final int CUS_ENCODING = 13; //公司客户编码
	public static final int DEPOT_ENCODING = 14; //公司仓库编码
	public static final int PRO_ENCODING = 15;  //公司货品编码
	
	//====采购设置
	public static final int STOCK_ORDER_ENCODING = 21; //采购订单编码
	public static final int STOCK_CHECK_ENCODING = 22; //采购进货编码
	public static final int STOCK_UNTREAD_ENCODING = 23; //采购退货编码
	public static final int STOCK_PAY_ENCODING = 24; //公司付款编码
	
	//====销售设置
	public static final int SELL_ORDER_ENCODING = 31; //销售签单编码
	public static final int SELL_SEGOODS_ENCODING = 32; //销售出库编码
	public static final int SELL_UNTREAD_ENCODING = 33; //销售退货编码
	public static final int SELL_CHECK_ENCODING = 34; //公司收款编码
	public static final int SELL_MALL_ENCODING = 35; //卖场销售编码

	
	//===库存设置
	public static final int STORAGE_CHECK_ENCODING = 43; //库存盘点编码
	public static final int STORAGE_TRUSS_ENCODING = 44; //库存拆装编码
	public static final int STORAGE_LOF_ENCODING = 45; //库存损溢编码
	public static final String getOrderType(int orderType){
		String str = ConstVar.NULL_STRING;
		switch(orderType){
		//====采购设置
		case STOCK_ORDER_ENCODING:
			str="采购订单编码";
			break;
		case STOCK_CHECK_ENCODING:
			str="采购进货编码";
			break;
		case STOCK_UNTREAD_ENCODING:
			str="采购退货编码";
			break;
		case STOCK_PAY_ENCODING:
			str="公司付款编码";
			break;
		//====销售设置

		case SELL_ORDER_ENCODING:
			str="销售签单编码";
			break;
		case SELL_SEGOODS_ENCODING:
			str="销售出库编码";
			break;
		case SELL_UNTREAD_ENCODING:
			str="销售退货编码";
			break;
		case SELL_CHECK_ENCODING:
			str="公司收款编码";
			break;
		case SELL_MALL_ENCODING:
			str="卖场销售编码";
			break;
		//====库存设置
		case STORAGE_CHECK_ENCODING:
			str="库存盘点编码";
			break;
		case STORAGE_TRUSS_ENCODING:
			str="库存拆装编码";
			break;
		case STORAGE_LOF_ENCODING:
			str="库存损溢编码";
			break;
		}
		return str;
	}
	public static final String KQ_USER_NAME1= "10021479801";
	public static final String KQ_HOST_NAME1="114.80.202.162";
	public static final String KQ_KEY_NAME1= "E8GRH7GH2U9SMMIA";
	
	public static final String KQ_USER_NAME2= "10021479804";
	public static final String KQ_HOST_NAME2="114.80.202.162";
	public static final String KQ_KEY_NAME2= "8BIXSXYAZN426255";
	
	
	public static final String  FW_USER_NO= "0210043230";//fa网新号2012-04-10
	
	//public static final String  FW_USER_NO= "0210043195";//fa网新号
	//public static final String  FW_USER_NO= "0210043021";
	public static final String  FW_KEY= "fineex_taier";
	public static final String  FW_URL= "http://www.fineex.com/interfaceTaier/Public/Receive.aspx";
	public static final String FW_ERROR_EMAIL = "95700855@qq.com";
	
	public static final Map<String,String> FW_STATUS_MAP = new HashMap<String,String>(){{
		put("S01","非法的XML格式");
		put("S02","非法的数字签名");
		put("S03","非法的会员号");
		put("S04","非法的通知类型");
		put("S05","非法的通知内空");
		put("S07","系统异常，请重试");
		put("R16","收件人拒收（未验货）");
		put("R17","收件人拒收（验货，货不对款）");
		put("R18","收件人拒付或仅愿意部分支付");
		put("R19","收件人拒收（因拖寄物品破损）");
		put("R20","收件人拒收（代收货款价格不对）");
		put("R21","超时无法投递");
		put("R22","托寄物品丢失");
		put("R23","无法联系上收件人");
		put("R24","错误收件人联系方式及地址");
		put("R99","其他原因");
		put("B11","不能进行操作，当前状态：订单取消或者关闭");
		put("B12","不能进行操作，订单已发货");
	}};
	
	public final static String PAGE_ETAO_PATH = BASE_UPLOAD_PATH+SEPARATOR+"etao";
	public final static String PAGE_ETAO_ITEMS_PATH = PAGE_ETAO_PATH + SEPARATOR +"item";
		
	/** 默认ID **/
	public static final Long DEFAULT_ID = -99L;
	public static final long DEPOT_ID_DEFAULT = 1;
}
