package com.lfb.law;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lfb.crawler.dao.Law;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LawController {

	@Autowired
	LawSolrRepo repo;
	
	@RequestMapping(value="qs", method=RequestMethod.GET)
	@ResponseBody
	public Object getSuggestion(
			 @RequestParam(value="q") String keyword,
			 @RequestParam("callback") String callback
			){
		
		Pageable page = new SolrPageRequest(0,10);
		
		HighlightPage<Law> pages = repo.findByTitle(keyword, page);
		
		return pages;
	}
	
}
