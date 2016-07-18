package com.lfb.sms;

/**
 * 用来保存验证码和手机号的对应关系，利用一个CocurrentHashMap
 * @author osanllyer
 *
 */
public interface SmsContainer {
	/**
	 * 检验短信验证码是否正确
	 * @return
	 */
	public boolean check(String phone, String code);
	
	/**
	 * 生成验证码
	 * @return 4位数字短信验证码
	 */
	public String genValidateCode();
	
	/**
	 * 保存验证码到容器
	 */
	public void save(String phone, String code);
	
}
