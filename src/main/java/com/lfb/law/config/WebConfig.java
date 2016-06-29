package com.lfb.law.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

import com.lfb.law.interceptor.QueryParamInterceptor;
import com.lfb.law.resolver.QueryArgumentResolver;

//@Configuration
//@EnableWebMvc
//public class WebConfig extends WebMvcConfigurerAdapter {
public class WebConfig {
////	@Override
//	  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//	        argumentResolvers.add(resolver());
//	  }
//
//	
////	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(interceptor()).addPathPatterns("/qs");
//	}
//	
//	/**
//	 * jsonp support for responsebody method, only affcet LawController.class
//	 * @author lifenbo
//	 *
//	 */
////	@ControllerAdvice
////	private static class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
////	    public JsonpAdvice() {
////	        super("callback");
////	    }
////	    
////	    @ExceptionHandler
////	    @ResponseBody
////	    public Object handleExcetion(Exception e){
////	    	return e.getMessage();
////	    }
////	}
//
//	
////	@Override
//	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
////		exceptionResolvers.add(new ApiExceptionResolver());
//	}
//	
//	public QueryArgumentResolver resolver(){
//		return new QueryArgumentResolver();
//	}
//	
//	public QueryParamInterceptor interceptor(){
//		return new QueryParamInterceptor();
//	}
//	
//	/**
//	 * global validator
//	 */
////	@Override
//	public Validator getValidator() {
//		return null;
//	}
//	
}
