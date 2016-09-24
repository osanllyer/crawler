package com.lfb.law.express;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * 获取今日速递
 */
@RestController
@RequestMapping(value="/express",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ExpressController {

	@Autowired
	ExpressMapper expressMapper;
	
	/**
	 * 获取列表
	 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ResponseEntity getExpresses(@RequestParam("from")int from, @RequestParam("size")int size){
		
		List<Express> expressList = expressMapper.getExpressList(from, size);
		return new ResponseEntity(expressList, HttpStatus.OK);
	}
	
	/**
	 * 获取单条
	 */
	@RequestMapping(value = "/id", method = RequestMethod.GET)
	public ResponseEntity getExpress(@RequestParam("id")int id){
	
		Express express = expressMapper.getExpress(id);
		return new ResponseEntity(express, HttpStatus.OK);
	}
	
}
