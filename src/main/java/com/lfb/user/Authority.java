package com.lfb.user;

/**
 * 用户权限设置
 * @author osanllyer
 *
 */
public class Authority {
	
	public static final String ROLE_USER = "user";
	public static final String ROLE_VIP = "vip";

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
	private String username;
	private String authority;
}
