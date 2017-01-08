package com.lfb.crawler.xuefa;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

public class XuefaMaterialDBPageModal implements PageModelPipeline<XuefaMaterialCrawler>{

	Logger logger = LoggerFactory.getLogger(XuefaMaterialDBPageModal.class);

	@Resource
	XuefaMaterialDao dao;
	
	@Override
	public void process(XuefaMaterialCrawler t, Task task) {
		dao.add(t);
	}

}
