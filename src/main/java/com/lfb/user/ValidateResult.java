package com.lfb.user;

/**
 * 用户验证结果
 * @author osanllyer
 *
 */
public class ValidateResult {

	public static enum CHECKRESULT {
		correct,
		error
	};
	
	public CHECKRESULT getResult() {
		return result;
	}
	public void setResult(CHECKRESULT result) {
		this.result = result;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	private  CHECKRESULT result;
	private String username;
	private String password;
}
