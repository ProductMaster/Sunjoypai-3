package com.taoists.sys.dataDict.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.taoists.common.Const;
import com.taoists.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-30
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sys_data_dict")
public class DataDict extends BaseEntity {
	
	private String code = Const.NULL_VALUE_STRING;
	private String title;
	private String memo;
	private int  status;
	@Column(name = "defalut_value")
	private Integer defaultValue = 0;
	private int value = Const.NULL_VALUE_INT;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private DictCategory dictCategory;

	public DataDict() {

	}

	public DataDict(Long id) {
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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public DictCategory getDictCategory() {
		return dictCategory;
	}

	public void setDictCategory(DictCategory dictCategory) {
		this.dictCategory = dictCategory;
	}

	public Integer getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Integer defaultValue) {
		this.defaultValue = defaultValue;
	}

}
