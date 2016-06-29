package com.lfb.law.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lfb.crawler.dao.Law;
import com.lfb.law.LawQuery;
import com.lfb.law.LawSolrRepo;
import com.lfb.law.resolver.QueryParam;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LawController {

	@Autowired
	LawSolrRepo repo;
	
	@InitBinder("LawQuery")
	protected void bind(WebDataBinder binder){
		binder.addValidators(new LawValidator());
	}
	
	@RequestMapping(value="qs", method=RequestMethod.GET)
	@ResponseBody
	public Object getSuggestion(
			 @Valid @QueryParam LawQuery query,
			 @RequestParam("callback") String callback
			){
		
		Pageable page = new SolrPageRequest(0,10);
		
		HighlightPage<Law> pages = repo.findByTitle(query.getKeyword(), page);
		
		return pages;
	}
	
}
