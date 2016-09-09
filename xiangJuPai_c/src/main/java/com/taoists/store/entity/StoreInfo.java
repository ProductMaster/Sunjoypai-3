package com.taoists.store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.taoists.common.Const;
import com.taoists.common.orm.entity.BaseEntity;
import com.taoists.common.util.UploadHelper;
@Entity
@Table(name="store_info")
public class StoreInfo  extends BaseEntity{
	// fields
	@Column(name="store_no")
	private java.lang.String storeNo = Const.NULL_VALUE_STRING;
	@Column(name="store_name")
	private String storeName = Const.NULL_VALUE_STRING;
	@Column(name="mobile")
	private String mobile = Const.NULL_VALUE_STRING;
	@Column(name="province_name")
	private String province_name = Const.NULL_VALUE_STRING;
	@Column(name="city_name")
	private java.lang.String cityName = Const.NULL_VALUE_STRING;
	@Column(name="country_name")
	private java.lang.String countryName = Const.NULL_VALUE_STRING;
	@Column(name = "addr")
	private String addr = Const.NULL_VALUE_STRING;
	
	@Column(name="page_count")
	private int pageCount = Const.ZERO_VALUE_INT;  //访问量
	
	
	@Column(name="img_url")
	private String imgUrl = Const.NULL_VALUE_STRING;  //图片
	@Column(name="promotion")
	private String promotion = Const.NULL_VALUE_STRING;  //促销信息
	@Column(name="major")
	private String major = Const.NULL_VALUE_STRING;  //主营
	@Column(name="opening")
	private java.lang.String opening = Const.NULL_VALUE_STRING;  //
	@Column(name="grade")
	private java.lang.String grade = Const.NULL_VALUE_STRING;
	@Column(name = "memo")
	private String memo = Const.NULL_VALUE_STRING;
	@Column(name = "status")
	private int status = Const.APPLICATION_NEW; 
	
	@Column(name = "cate_id")
	private long cateId = Const.NULL_VALUE_LONG;
	
	@Column(name = "user_id")
	private long userId = Const.NULL_VALUE_LONG;		//所属用户
	
	@Column(name = "coord_x")
	private double x;              //地址x坐标
	@Column(name = "coord_y")
	private double y;              //地址y坐标
	@Column(name = "photoPath")
	private String photoPath ="";   // 图片路径
	
	@Transient
	private double distance = Const.NULL_VALUE_DOUBLE;	
	
	@Transient
	private String path = Const.NULL_VALUE_STRING;   //图片路径
	

	public String getPath() {
		return String.valueOf(UploadHelper.mmNum(this.getId(),5000))+"/";
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
	public java.lang.String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(java.lang.String storeNo) {
		this.storeNo = storeNo;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getProvince_name() {
		return province_name;
	}
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}
	public java.lang.String getCityName() {
		return cityName;
	}
	public void setCityName(java.lang.String cityName) {
		this.cityName = cityName;
	}
	public java.lang.String getCountryName() {
		return countryName;
	}
	public void setCountryName(java.lang.String countryName) {
		this.countryName = countryName;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPromotion() {
		return promotion;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	public java.lang.String getOpening() {
		return opening;
	}
	public void setOpening(java.lang.String opening) {
		this.opening = opening;
	}
	public java.lang.String getGrade() {
		return grade;
	}
	public void setGrade(java.lang.String grade) {
		this.grade = grade;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public long getCateId() {
		return cateId;
	}
	public void setCateId(long cateId) {
		this.cateId = cateId;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public String getPhotoPath() {
		return photoPath;
	}

}
