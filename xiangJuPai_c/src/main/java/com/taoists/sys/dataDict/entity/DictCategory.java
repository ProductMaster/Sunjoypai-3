package com.taoists.sys.dataDict.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.taoists.common.Const;
import com.taoists.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-30
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sys_dict_category")
public class DictCategory extends BaseEntity {

	public static final long DICT_CATEGORY_ARTICLE_CATEGORY = 1;

	private String code = Const.NULL_VALUE_STRING;
	private String title;
	private String descr = Const.NULL_VALUE_STRING;
	private String url = Const.NULL_VALUE_STRING;
	private Long companyId = Const.NULL_VALUE_LONG;

	public DictCategory() {

	}

	public DictCategory(Long id) {
		setId(id);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

}
