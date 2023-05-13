package com.KoreaIT.jjh.exam.vo;

import lombok.Getter;

public class ResultData {
	@Getter
	private String resultCode;
	@Getter
	private String msg;
	@Getter
	private Object data;
	@Getter
	private String dataName;

	
	public static ResultData from(String resultCode, String msg) {
		
		
		return from(resultCode, msg, null, null);
	}
	
	public static ResultData from(String resultCode, String msg, String dataName, Object data) {
		ResultData rd = new ResultData();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.dataName = dataName;
		rd.data = data;

		return rd;
	}
	
	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}

	public boolean isFail() {
		return isSuccess() == false;
	}
}