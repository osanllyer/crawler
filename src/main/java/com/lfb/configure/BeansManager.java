package com.lfb.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lfb.sms.DefaultSmsContainer;
import com.lfb.sms.DefaultValidateCodeGenerator;
import com.lfb.sms.SmsContainer;
import com.lfb.sms.ValidateCodeGenerator;


@Configuration
public class BeansManager {

	@Bean
	public DefaultValidateCodeGenerator defaultValidateCodeGenerator(){
		return new DefaultValidateCodeGenerator();
	}
	
	@Bean
	public SmsContainer defaultSmsContainer(){
		return new DefaultSmsContainer();
	}
	
}
