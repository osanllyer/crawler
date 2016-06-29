package com.lfb.law.exception;

/**
 * server errors definition
 * @author lifenbo
 *
 */
public enum ApiError {

	/**
	 * ***************************************search ************************
	 */
	INVALID_KEYOWRD(100001, "keyword is error");
	
	

	private ApiError(int code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	private int code;
	private String desc;
}
