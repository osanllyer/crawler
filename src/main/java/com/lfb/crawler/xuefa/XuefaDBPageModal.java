package com.lfb.crawler.xuefa;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

public class XuefaDBPageModal implements PageModelPipeline<XuefaQuestionsCrawler>{

	Logger logger = LoggerFactory.getLogger(XuefaDBPageModal.class);

	@Resource
	XuefaQuestionDao dao;
	
	@Override
	public void process(XuefaQuestionsCrawler t, Task task) {
		dao.add(t);
	}

}
