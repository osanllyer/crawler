package com.lfb.law;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
public class LawController {

	@Autowired
	LawService service;
	
	
	@RequestMapping(value="qs", method=RequestMethod.GET)
	public String getSuggestion(
			 @RequestParam(value="q") String keyword,
			 @RequestParam("callback") String callback,
			 @RequestParam("lang") int lan,
			 @RequestParam("_") String useless
			){
		
		return null;
	}
	
}
