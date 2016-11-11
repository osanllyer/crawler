package com.lfb.rank;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RankController {
	
	@Autowired
	private RankMapper mapper;

	@RequestMapping("/rank")
	public ResponseEntity rank(@RequestParam(value="user", required=false)String user){
		Map map = mapper.userRank(user);
		return new ResponseEntity(map, HttpStatus.OK);
	}
	
	/**
	 * 返回top rank 的用户信息,size不能超过20
	 * @param size
	 * @return
	 */
	@RequestMapping("/top")
	public ResponseEntity topRank(@RequestParam("size")int size){
		List<Map> map = mapper.topRank(size);
		return new ResponseEntity(map, HttpStatus.OK);
	}
	
}
