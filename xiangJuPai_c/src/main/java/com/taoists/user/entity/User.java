package com.taoists.user.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import com.taoists.common.Const;
import com.taoists.common.orm.entity.BaseEntity;
import com.taoists.common.util.DateUtils;
import com.taoists.sys.dataDict.entity.DataDict;

/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-12-6 上午09:19:34  
 *  
 */
@SuppressWarnings("serial")
@Entity
@Table(name="users")
public class User extends BaseEntity {

	// fields
	@Column(name="user_no")
	private String userNo = Const.NULL_VALUE_STRING;
	@Column(name = "user_name")
	private String userName = Const.NULL_VALUE_STRING;
	@Column(name = "passwd")
	private String pwd = Const.NULL_VALUE_STRING;
	@Column(name = "user_sex")
	private int sex = Const.SEX_UNKNOW;
	@Column(name = "user_email")
	private String email  = Const.NULL_VALUE_STRING;
	@Column(name = "user_mobile")
	private java.lang.String mobile = Const.NULL_VALUE_STRING;
	@Column(name = "spare_mobile")
	private java.lang.String spareMobile = Const.NULL_VALUE_STRING;
	@Column(name = "user_point")
	private long point = Const.ZERO_VALUE_LONG;
	@Column(name="user_balance")
	private BigDecimal balance=new BigDecimal(0.00D);
	@Column(name = "user_birthday")
	private Long birthday=Const.NULL_VALUE_LONG;
	@Column(name = "status")
	private int status=Const.YES;
	@Column(name = "first_buy_time")
	private Long firstBuyTime=Const.NULL_VALUE_LONG;
	@Column(name = "first_buy_amount")
	private BigDecimal firstBuyAmount=new BigDecimal(0.00D);
	@Column(name = "last_buy_time")
	private Long lastBuyTime=Const.NULL_VALUE_LONG;
	@Column(name = "last_buy_amount")
	private BigDecimal lastBuyAmount=new BigDecimal(0.00D);
	@Column(name = "total_order_count")
	private int totalOrderCount = Const.ZERO_VALUE_INT;
	@Column(name = "new_order_count")
	private int newOrderCount = Const.ZERO_VALUE_INT;        //代付款订单数
	@Column(name = "confirm_order_count")
	private int confirmOrderCount = Const.ZERO_VALUE_INT;    //待发货数
	@Column(name = "send_order_count")
	private int sendOrderCount = Const.ZERO_VALUE_INT;       //待收货
	@Column(name = "receive_order_count")
	private int receiveOrderCount = Const.ZERO_VALUE_INT;	  //已完成
	@Column(name = "total_order_amount")
	private BigDecimal totalOrderAmount=new BigDecimal(0.00D);
	@Column(name = "total_commission_amount")
	private BigDecimal totalCommissionAmount=new BigDecimal(0.00D);
	@Column(name = "orderback_count")
	private int orderbackCount = Const.ZERO_VALUE_INT;
	@Column(name = "orderback_amount")
	private BigDecimal orderbackAmount=new BigDecimal(0.00D);
	@Column(name = "last_call_time")
	private Long lastCallTime=Const.NULL_VALUE_LONG;
	@Column(name = "media_id")
	private long mediaId = Const.NULL_VALUE_LONG;
	@Column(name = "media_no")
	private String mediaNo = Const.NULL_VALUE_STRING;
	@Column(name= "media_name")
	private String mediaName = Const.NULL_VALUE_STRING;
	@Column(name = "adzone_id")
	private long adzoneId = Const.NULL_VALUE_LONG;
	@Column(name = "memo")
	private String memo = Const.NULL_VALUE_STRING;
	@Column(name = "wx_open_id")
	private String wxOpenId = Const.NULL_VALUE_STRING;
	@Column(name = "nickname")
	private String nickname = Const.NULL_VALUE_STRING;
	@Column(name = "logo")
	private String logo = Const.NULL_VALUE_STRING;;
	@Column(name = "addr")
	private String addr = Const.NULL_VALUE_STRING;;
	@Column(name = "qrcode")
	private String qrcode = "";
	@Column(name = "subordinate_num")
	private int subordinateNum = Const.ZERO_VALUE_INT;
	@Column(name = "service_id")
	private Long serviceId=Const.NULL_VALUE_LONG;
	@Column(name = "referee2_id")
	private Long referee2Id=Const.NULL_VALUE_LONG;
	@Column(name = "referee3_id")
	private Long referee3Id=Const.NULL_VALUE_LONG;
	
	@Column(name = "alipay_account")
	private String alipayAccount = Const.NULL_VALUE_STRING;
	@Column(name = "alipay_name")
	private String alipayName = Const.NULL_VALUE_STRING;
	
	@Column(name = "bank_account")
    private String bankAccount = Const.NULL_VALUE_STRING;
    @Column(name = "bank_name")
    private String bankName = Const.NULL_VALUE_STRING;
    @Column(name = "bank_account_name")
    private String bankAccountName = Const.NULL_VALUE_STRING;
	
	@Column(name = "user_type")
	private int userType = Const.ZERO_VALUE_INT;
	@Column(name = "user_level")
	private int level = Const.ZERO_VALUE_INT;
	
    @Column(name = "head_pic_url")
    private String picUrl = Const.NULL_VALUE_STRING;
	@Column(name = "photoPath")
	private String photoPath = Const.NULL_VALUE_STRING;   // 图片路径
	
    @ManyToOne
    @JoinColumn(name = "referee_id")
    private User referee;
    
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public java.lang.String getMobile() {
		return mobile;
	}
	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}
	public java.lang.String getSpareMobile() {
		return spareMobile;
	}
	public void setSpareMobile(java.lang.String spareMobile) {
		this.spareMobile = spareMobile;
	}
	public long getPoint() {
		return point;
	}
	public void setPoint(long point) {
		this.point = point;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Long getBirthday() {
		return birthday;
	}
	public String getBirthdayStr(){
		if (birthday != Const.NULL_VALUE_LONG) {
			return DateUtils.long2ShortString(birthday);
		} else {
			return Const.NULL_VALUE_DISPLAY;
		}
	}
	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Long getFirstBuyTime() {
		return firstBuyTime;
	}
	public String getFirstBuyTimeStr() {
		if (firstBuyTime != Const.NULL_VALUE_LONG) {
			return DateUtils.long2ShortString(firstBuyTime);
		} else {
			return Const.NULL_VALUE_DISPLAY;
		}
	}
	public void setFirstBuyTime(Long firstBuyTime) {
		this.firstBuyTime = firstBuyTime;
	}
	public BigDecimal getFirstBuyAmount() {
		return firstBuyAmount;
	}
	public void setFirstBuyAmount(BigDecimal firstBuyAmount) {
		this.firstBuyAmount = firstBuyAmount;
	}
	public Long getLastBuyTime() {
		return lastBuyTime;
	}
	public String getLastBuyTimeStr() {
		if (lastBuyTime != Const.NULL_VALUE_LONG) {
			return DateUtils.long2ShortString(lastBuyTime);
		} else {
			return Const.NULL_VALUE_DISPLAY;
		}
	}	
	public void setLastBuyTime(Long lastBuyTime) {
		this.lastBuyTime = lastBuyTime;
	}
	public BigDecimal getLastBuyAmount() {
		return lastBuyAmount;
	}
	public void setLastBuyAmount(BigDecimal lastBuyAmount) {
		this.lastBuyAmount = lastBuyAmount;
	}
	public int getTotalOrderCount() {
		return totalOrderCount;
	}
	public void setTotalOrderCount(int totalOrderCount) {
		this.totalOrderCount = totalOrderCount;
	}
	
	
	public int getNewOrderCount() {
		return newOrderCount;
	}
	public void setNewOrderCount(int newOrderCount) {
		this.newOrderCount = newOrderCount;
	}
	public int getConfirmOrderCount() {
		return confirmOrderCount;
	}
	public void setConfirmOrderCount(int confirmOrderCount) {
		this.confirmOrderCount = confirmOrderCount;
	}
	public int getSendOrderCount() {
		return sendOrderCount;
	}
	public void setSendOrderCount(int sendOrderCount) {
		this.sendOrderCount = sendOrderCount;
	}
	public int getReceiveOrderCount() {
		return receiveOrderCount;
	}
	public void setReceiveOrderCount(int receiveOrderCount) {
		this.receiveOrderCount = receiveOrderCount;
	}
	public BigDecimal getTotalOrderAmount() {
		return totalOrderAmount;
	}
	public void setTotalOrderAmount(BigDecimal totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}	
	public BigDecimal getTotalCommissionAmount() {
		return totalCommissionAmount;
	}
	public void setTotalCommissionAmount(BigDecimal totalCommissionAmount) {
		this.totalCommissionAmount = totalCommissionAmount;
	}
	public int getOrderbackCount() {
		return orderbackCount;
	}
	public void setOrderbackCount(int orderbackCount) {
		this.orderbackCount = orderbackCount;
	}
	public BigDecimal getOrderbackAmount() {
		return orderbackAmount;
	}
	public void setOrderbackAmount(BigDecimal orderbackAmount) {
		this.orderbackAmount = orderbackAmount;
	}
	public Long getLastCallTime() {
		return lastCallTime;
	}
	public void setLastCallTime(Long lastCallTime) {
		this.lastCallTime = lastCallTime;
	}
	public long getMediaId() {
		return mediaId;
	}
	public void setMediaId(long mediaId) {
		this.mediaId = mediaId;
	}
	public String getMediaNo() {
		return mediaNo;
	}
	public void setMediaNo(String mediaNo) {
		this.mediaNo = mediaNo;
	}
	public String getMediaName() {
		return mediaName;
	}
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	public long getAdzoneId() {
		return adzoneId;
	}
	public void setAdzoneId(long adzoneId) {
		this.adzoneId = adzoneId;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getWxOpenId() {
		return wxOpenId;
	}
	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}
	
	public String getSexPic(){
		return Const.getSexPic(sex);
	}	
	
	public String getStatusPic(){
		return Const.getAccountActivePic(status);
	}
	public String getMemoPic(){
		return Const.getMemoPic(memo);
	}
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getLogo() {
    	return logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
    public User getReferee() {
        return referee;
    }
    public void setReferee(User referee) {
        this.referee = referee;
    }
    public String getQrcode() {
        return qrcode;
    }
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }
    public int getSubordinateNum() {
        return subordinateNum;
    }
    public void setSubordinateNum(int subordinateNum) {
        this.subordinateNum = subordinateNum;
    }
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}	
	public Long getServiceId() {
        return serviceId;
    }
    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
    public Long getReferee2Id() {
		return referee2Id;
	}
	public void setReferee2Id(Long referee2Id) {
		this.referee2Id = referee2Id;
	}
	public Long getReferee3Id() {
		return referee3Id;
	}
	public void setReferee3Id(Long referee3Id) {
		this.referee3Id = referee3Id;
	}	
    public String getAlipayAccount() {
		return alipayAccount;
	}
	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}
	public String getAlipayName() {
		return alipayName;
	}
	public void setAlipayName(String alipayName) {
		this.alipayName = alipayName;
	}
    public String getBankAccount() {
        return bankAccount;
    }
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public String getBankAccountName() {
        return bankAccountName;
    }
    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}	
}
