package com.lfb.configure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.lfb.interceptor.AuthInterceptor;

@Configuration
public class WebMvcConfigure extends WebMvcConfigurerAdapter {

	@Autowired
	AuthInterceptor authInterceptor;
	
	/**
	 * 将用户id或者其他请求转为User对象
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
		argumentResolvers.add(new UserArgumentResolver());
	}
	
	/**
	 * 增加用户登录记分拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor).addPathPatterns("/user/auth/**");
	}
}
