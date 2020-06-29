package com.Ewallet.wallet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.Ewallet.wallet.dto.TransactionStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRANSACTIION_ID")
	private Long txnId;
	private TransactionStatus transactionStatus;
	@Column(name = "TRANSACTION_AMOUNT")
	private Double transactionAmount;
	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber;
	@Column(name = "RECIEVER_MOB_NO")
	private String recieverMobNo;

	public Long getTxnId() {
		return txnId;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getRecieverMobNo() {
		return recieverMobNo;
	}

	public void setRecieverMobNo(String recieverMobNo) {
		this.recieverMobNo = recieverMobNo;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

}