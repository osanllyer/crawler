package com.lfb.download;
/**
 * 下载
 */
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/download", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DownloadController {

	@Autowired
	MapperDownloadItem mapper;
	
	@Cacheable
	@ModelAttribute("total")
	public Integer total(){
		return mapper.getTotal();
	}
	
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
//        binder.addValidators(new MyValidator());
    }

	@RequestMapping(value="/items")
	public List<DownloadItem> getDownloadItems(@RequestParam("start") int start, @RequestParam("limit") int limit){
		//其实应该用argumentresolver来处理
		if(start < 0){
			start = 0;
		}
		if(limit == 0){
			limit = 10;
		}
		return mapper.getDownloadItems(start, limit);
	}
}
