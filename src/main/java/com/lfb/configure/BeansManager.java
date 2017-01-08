package com.lfb.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lfb.sms.DefaultSmsContainer;
import com.lfb.sms.DefaultValidateCodeGenerator;
import com.lfb.sms.SmsContainer;


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
	
	/**
	 * 用来对请求对非对象参数进行验证
	 * @return
	 */
	 @Bean
	 public MethodValidationPostProcessor methodValidationPostProcessor() {
	      return new MethodValidationPostProcessor();
	 }	
	
	/**
	 * messageconverter
	 * @return
	 */
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
	 MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
	 ObjectMapper objectMapper = new ObjectMapper();
	 objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	 jsonConverter.setObjectMapper(objectMapper);
	 return jsonConverter;
	}
}
