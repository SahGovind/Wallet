package com.Ewallet.wallet.dto;



public class WalletDTO {

	private Double current_balance;
	private String mobile_no;
	private Double amountToAdd;
	
	
	public Double getAmountToAdd() {
		return amountToAdd;
	}

	public void setAmountToAdd(Double amountToAdd) {
		this.amountToAdd = amountToAdd;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public Double getCurrent_balance() {
		return current_balance;
	}

	public void setCurrent_balance(Double current_balance) {
		this.current_balance = current_balance;
	}

}
