package com.taoists.appoint.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.taoists.common.Const;
import com.taoists.common.orm.entity.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name="mobile_application")
public class MobileApplication  extends BaseEntity{

	// fields
	@Column(name="mobile")
	private String mobile = Const.NULL_VALUE_STRING;
	@Column(name="source_page")
	private String sourcePage = Const.NULL_VALUE_STRING;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSourcePage() {
        return sourcePage;
    }

    public void setSourcePage(String sourcePage) {
        this.sourcePage = sourcePage;
    }
	
	


}
