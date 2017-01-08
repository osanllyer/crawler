package com.lfb.correct;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ErrCorrectBodyValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == ErrCorrectBody.class;
	}

	@Override
	public void validate(Object target, Errors errors) {

		ErrCorrectBody body = (ErrCorrectBody)target;
		
		if(body.getAnswer() == null || body.getAnswer().isEmpty()){
			errors.rejectValue("answer", "answer is empty");
		}
		
		if(body.getAnalysis() == null || body.getAnalysis().isEmpty()){
			errors.rejectValue("analysis", "analysis is empty");
		}
		
		if(body.getUser() == null || body.getUser().isEmpty()){
			errors.rejectValue("user", "user is empty");
		}		
		
	}

}
