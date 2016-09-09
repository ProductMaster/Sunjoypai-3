package com.taoists.sys.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.taoists.common.orm.entity.BaseEntity;

@Entity
@Table(name = "sys_bank")
public class SysBank extends BaseEntity {
	private static final long serialVersionUID = 1L;

	public static final int BANK_KQ = -1;// 快钱


	// fields
	@Column(name = "bank_name")
	private java.lang.String bankName;
	@Column(name = "memo")
	private java.lang.String memo;
	@Column(name = "order_seed")
	private int orderSeed;
	@Column(name = "status")
	private int status;

	
	public SysBank() {
		super();
	}


	public java.lang.String getBankName() {
		return bankName;
	}

	public void setBankName(java.lang.String bankName) {
		this.bankName = bankName;
	}

	public java.lang.String getMemo() {
		return memo;
	}

	public void setMemo(java.lang.String memo) {
		this.memo = memo;
	}

	public int getOrderSeed() {
		return orderSeed;
	}

	public void setOrderSeed(int orderSeed) {
		this.orderSeed = orderSeed;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}