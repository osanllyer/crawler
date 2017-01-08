package com.lfb.exception;

import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.collect.Maps;

@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 处理参数错误, 用内置的更合适，留着当作例子把
	 * @param exception
	 * @return
	 */
	@Deprecated
	@ExceptionHandler({MyArgumentException.class})
	@ResponseBody
	public Map<String, String> argumentException(MyArgumentException exception){
		Map<String, String > map = Maps.newHashMap();
		map.put("reason", exception.getMessage());
		return map;
	}
	
	/**
	 * JSR 303 validation error
	 * @param e
	 * @return
	 */
	 @ExceptionHandler(value = ConstraintViolationException.class)
	 @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	 @ResponseBody
	 public String handleResourceNotFoundException(ConstraintViolationException e) {
	      Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
	      StringBuilder strBuilder = new StringBuilder();
	      for (ConstraintViolation<?> violation : violations ) {
	           strBuilder.append(violation.getMessage() + "\n");
	      }
	      return strBuilder.toString();
	 }
	 
	/**
	 * JSR 303 参数验证错误
	 * @param e
	 * @return
	 */
	 @ExceptionHandler(value =  MethodArgumentNotValidException.class )
	 @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	 @ResponseBody
	 public String handleResourceNotFoundException3(MethodArgumentNotValidException e) {
		 FieldError error = e.getBindingResult().getFieldError();
	     return error.getField() + ":" + error.getDefaultMessage();
	 }		 
}
