package com.taoists.store.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.taoists.common.Const;
import com.taoists.common.orm.entity.BaseEntity;
import com.taoists.common.util.UploadHelper;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2015年7月17日 下午5:19:35 
 * 类说明 
 */
@Entity
@Table(name="catalog")
public class Catalog extends BaseEntity {

    public static final long CATALOG_ZERO_TRY = 1L;           // 新订单
    public static final long CATALOG_1500YUAN = 2L;           // 1500元
    
	@Column(name="catalog_name")
	private String catalogName="";
	@Column(name="catalog_no")
	private String catalogNo="";
	@Column(name="catalog_spec")
	private String catalogSpec="";
	@Column(name="catalog_unit")
	private String catalogunit="";
	@Column(name="catalog_title")
	private String catalogTitle="";
	@Column(name="catalog_type")
	private int catalogType;
	@Column(name="catalog_points")
	private int catalogPoints;
	@Column(name="catalog_barcode")
	private String catalogBarcode="";
	@Column(name="catalog_desc")
	private String catalogDesc="";
	@Column(name="available_qty")
	private int availableQty;
	@Column(name = "sale_status")
	private int saleStatus=Const.YES;
	@Column(name = "normal_price")
	private java.math.BigDecimal normalPrice=new BigDecimal(0);
	@Column(name = "sale_price")
	private java.math.BigDecimal salePrice=new BigDecimal(0);

	@Column(name="catalog_pic_url")
	private String picUrl="";
	@Column(name="is_show")
	private int isShow = Const.YES;
	
	@Column(name="store_id")
	private long storeId;
	
	// many to one
/*	@ManyToOne
	@JoinColumn(name="category_id")
	private SysCategory category;*/
	@Transient
	private String path = Const.NULL_VALUE_STRING;   //图片路径
	
	
	public int getIsShow() {
		return isShow;
	}
	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}
	public String getPath() {
		return String.valueOf(UploadHelper.mmNum(this.getId(),5000))+"/";
	}
	public void setPath(String path) {
		this.path = path;
	}
	public long getStoreId() {
		return storeId;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	public String getCatalogSpec() {
		return catalogSpec;
	}
	public void setCatalogSpec(String catalogSpec) {
		this.catalogSpec = catalogSpec;
	}
	public String getCatalogunit() {
		return catalogunit;
	}
	public void setCatalogunit(String catalogunit) {
		this.catalogunit = catalogunit;
	}
	public String getCatalogTitle() {
		return catalogTitle;
	}
	public void setCatalogTitle(String catalogTitle) {
		this.catalogTitle = catalogTitle;
	}
	public int getCatalogType() {
		return catalogType;
	}
	public void setCatalogType(int catalogType) {
		this.catalogType = catalogType;
	}
	public int getCatalogPoints() {
		return catalogPoints;
	}
	public void setCatalogPoints(int catalogPoints) {
		this.catalogPoints = catalogPoints;
	}
	public String getCatalogBarcode() {
		return catalogBarcode;
	}
	public void setCatalogBarcode(String catalogBarcode) {
		this.catalogBarcode = catalogBarcode;
	}
	public String getCatalogDesc() {
		return catalogDesc;
	}
	public void setCatalogDesc(String catalogDesc) {
		this.catalogDesc = catalogDesc;
	}
	public int getAvailableQty() {
		return availableQty;
	}
	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}
	public int getSaleStatus() {
		return saleStatus;
	}
	public void setSaleStatus(int saleStatus) {
		this.saleStatus = saleStatus;
	}
	public java.math.BigDecimal getNormalPrice() {
		return normalPrice;
	}
	public void setNormalPrice(java.math.BigDecimal normalPrice) {
		this.normalPrice = normalPrice;
	}
	public java.math.BigDecimal getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(java.math.BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	/** 统计数据类型 **/
	public static final int UP_STATUS = 1;
	public static final int DOWN_STATUS  = 0;
	public   String getStatusStr(){
		String retVal = "";
		switch(this.saleStatus)
		{
			case UP_STATUS:
				retVal = "上架";
				break;
			case DOWN_STATUS:
				retVal = "下架";
				break;
		}
		return retVal;
	}
	
	
}
