package com.lfb.correct;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Validated
public class ErrCorrectController {

	@Autowired
	private ErrCorrectMapper mapper;
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		//留着备用把
//		binder.addValidators(new ErrCorrectBodyValidator());
	}
	
	
	@RequestMapping(path="/correct", consumes="application/json")
	public ResponseEntity correct(@Valid @RequestBody ErrCorrectBody errCorrectBody){
		mapper.insert(errCorrectBody);
		return new ResponseEntity(HttpStatus.OK);
	}
	
}
