package com.lfb.sms;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * 默认的短信验证码容器，使用concurrenthashmap保存
 * @author osanllyer
 *
 */
public class DefaultSmsContainer implements SmsContainer {

	@Autowired
	DefaultValidateCodeGenerator codeGen;
	
	Cache<String, String> cache =   CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(100000000).build();
	
	@Override
	public boolean check(String phone, String code) {
		Object obj = cache.getIfPresent(phone);
		if(obj != null){
			return ((String)obj).equalsIgnoreCase(code);
		}
		return false;
	}

	@Override
	public String genValidateCode() {
		return codeGen.gen(null);
	}

	@Override
	public void save(String phone, String code) {
		cache.put(phone, code);
	}

}
