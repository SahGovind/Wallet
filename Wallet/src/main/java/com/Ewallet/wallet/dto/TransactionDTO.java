package com.Ewallet.wallet.dto;

import java.util.Date;



public class TransactionDTO {

	private TransactionStatus transactionStatus;
	private String mobileNumber;
	private String recieverMobNo;
	private Double txnAmount;
	private String txnId;

	private Date create_date;

	// private Date update_date;

	public Date getCreate_date() {
		return create_date;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Double getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(Double txnAmount) {
		this.txnAmount = txnAmount;
	}

	public String getRecieverMobNo() {
		return recieverMobNo;
	}

	public void setRecieverMobNo(String recieverMobNo) {
		this.recieverMobNo = recieverMobNo;
	}



}
