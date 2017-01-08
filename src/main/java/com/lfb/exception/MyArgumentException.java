package com.lfb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 参数错误
 * @author osanllyer
 *
 */
@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class MyArgumentException extends RuntimeException{

	public MyArgumentException(String message){
		super(message);
	}
	
}
