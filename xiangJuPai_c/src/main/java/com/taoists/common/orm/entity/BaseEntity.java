package com.taoists.common.orm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.taoists.common.Const;
import com.taoists.common.util.DateUtils;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-28
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	@Id
	@GeneratedValue(generator = "identity")
	@GenericGenerator(name = "identity", strategy = "identity")
	@Column(name = "id")
	private long id;
	
	@Transient
	private int recordStatus = RECORD_STATUS_UNLIMITED;
	
	// ConstVar
	//记录状态
	public final static int RECORD_STATUS_DISABLE   = -1;
	public final static int RECORD_STATUS_UNLIMITED = 0;
	public final static int RECORD_STATUS_READONLY  = 1;

	//存活状态
	public static final int ACTIVE_STATUS_NEW = 0;
	public static final int ACTIVE_STATUS_COMPLETE = 1;
	public static final int ACTIVE_STATUS_DELETE = -1;
	
	public int getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(int recordStatus) {
		this.recordStatus = recordStatus;
	}
	@Column(updatable = false, name = "create_date")
	private long createDate = Const.NULL_VALUE_LONG;
	@Column(updatable = false, name = "last_modify_date")
	private long lastModifyDate = Const.NULL_VALUE_LONG;
	@Column(updatable = false, name = "create_dates")
	private String createDateStr = Const.NULL_VALUE_STRING;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}	
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	    this.createDateStr = DateUtils.long2LongString(createDate);
	}
	public long getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(long lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public String getCreateDateStr() {
		if (createDate != Const.NULL_VALUE_LONG) {
			return DateUtils.long2LongString(createDate);
		} else {
			return Const.NULL_VALUE_DISPLAY;
		}
	}
	public String getLastModifyDateStr() {
		if (lastModifyDate != Const.NULL_VALUE_LONG) {
			return DateUtils.long2ShortString(lastModifyDate);
		} else {
			return Const.NULL_VALUE_DISPLAY;
		}
	}
}
