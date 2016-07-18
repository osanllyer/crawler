package com.lfb.sms;


/**
 * 验证码生成器
 * @author osanllyer
 *
 */
public interface ValidateCodeGenerator {

	public String gen(Object... params);
	
}
