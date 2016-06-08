package com.lfb.law.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.google.common.base.Strings;
import com.lfb.law.LawQuery;
import com.lfb.law.exception.ApiError;
import com.lfb.law.exception.ApiException;

public class LawValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == LawQuery.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		LawQuery q = (LawQuery) target;
		if (Strings.isNullOrEmpty(q.getKeyword())){
			throw new ApiException(ApiError.INVALID_KEYOWRD);
		}
	}

}
