package com.lfb.law;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.Highlight;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.lfb.crawler.dao.Law;

public interface LawSolrRepo extends SolrCrudRepository<Law, String>{

	  @Highlight
	  HighlightPage<Law> findByTitle(String keyword, Pageable page);
	
}
