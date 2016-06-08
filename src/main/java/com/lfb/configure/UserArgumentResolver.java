package com.lfb.configure;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.lfb.user.User;
import com.lfb.user.UserParam;

/**
 * 将参数转为user类
 * 支持使用UserParam注解的参数
 * @author osanllyer
 *
 */
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterAnnotation(UserParam.class) != null;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		
		String avatar = webRequest.getParameter("avatar");
		String nickname = webRequest.getParameter("nickname");
		String gender = webRequest.getParameter("gender");
		String province = webRequest.getParameter("province");
		String address = webRequest.getParameter("address");
		String id = webRequest.getParameter("id");
		
		User user = new User();
		user.setAvatar(avatar);
		user.setNickname(nickname);
		user.setId(id);
		user.setGender(gender!=null ? Integer.valueOf(gender) : -1);
		user.setProvince(province);
		user.setAddress(address);

		return user;
	}
}
