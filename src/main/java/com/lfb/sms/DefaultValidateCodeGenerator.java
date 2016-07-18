package com.lfb.sms;

import java.util.Random;

/**
 * 默认验证码生成器
 * @author osanllyer
 *
 */
public class DefaultValidateCodeGenerator implements ValidateCodeGenerator {

	@Override
	public String gen(Object... params) {
		Random r = new Random();
		int ri = r.nextInt(10000);
		String res = String.format("%04d", ri);
		return res;
	}

}
