package com.ashish.sns.dto;

import com.amazonaws.services.sns.model.PublishResult;

public class ResponseBean {
	private String code;
	private String response;
	private PublishResult result;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public PublishResult getResult() {
		return result;
	}
	public void setResult(PublishResult result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "ResponseBean [code=" + code + ", response=" + response + ", result=" + result + "]";
	}
}
