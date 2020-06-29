package com.Ewallet.wallet.output;

import lombok.Getter;
import lombok.Setter;


public class ApiOutput {
	
	private Boolean success=true;
	private Object response;
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
	
	
}
