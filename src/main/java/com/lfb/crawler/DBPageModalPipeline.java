package com.lfb.crawler;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lfb.crawler.dao.Law;
import com.lfb.crawler.dao.LawDao;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/**
 * save to mysql
 * @author lifenbo
 *
 */
public class DBPageModalPipeline implements PageModelPipeline{

	Logger logger = LoggerFactory.getLogger(DBPageModalPipeline.class);
	private LawDao dao = new LawDao();
	
	public void process(Object t, Task task) {
		
		if (t instanceof LawCrawler){
			
			LawCrawler obj = (LawCrawler)t;
			
			Law law = new Law();
			
			law.setContent(obj.getContent());
			law.setDocNum(obj.getDocnum());
			law.setPublishDate(Date.valueOf(obj.getPublishDate()));
			law.setPublisher(obj.getPublisher());
			law.setValidSince(Date.valueOf(obj.getValidSince()));
			law.setTitle(obj.getTitle());
			
			dao.insert(law);
		}
		
	}
	
	
}
