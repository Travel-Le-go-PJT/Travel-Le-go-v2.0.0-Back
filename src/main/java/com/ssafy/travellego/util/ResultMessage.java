package com.ssafy.travellego.util;

public class ResultMessage {

	public String result;
	

	public ResultMessage() {
		super();
		this.result = null;
	}


	public String getResult() {
		return result;
	}


	public void setResultSuccess() {
		this.result = "SUCCESS";
	}

	public void setResultFail() {
		this.result = "FAIL";
	}
	
	
}
