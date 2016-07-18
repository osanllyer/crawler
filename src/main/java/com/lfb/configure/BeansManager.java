package com.lfb.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lfb.sms.DefaultSmsContainer;
import com.lfb.sms.DefaultValidateCodeGenerator;
import com.lfb.sms.ValidateCodeGenerator;


@Configuration
public class BeansManager {

	@Bean
	public ValidateCodeGenerator defaultValidateCodeGenerator(){
		return new DefaultValidateCodeGenerator();
	}
	
	@Bean
	public DefaultSmsContainer defaultSmsContainer(){
		return new DefaultSmsContainer();
	}
	
}
