package com.lfb.crawler.xuefa;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Files;

public class Exporter {

	@Autowired
	XuefaQuestionDao dao;
	
	String filePath = "/Users/osanllyer/lawdata/xuefa/";
		
	/**
	 * export all
	 */
	public void run(){
		List<XuefaQuestionsCrawler> lists = dao.get();
		Map<String, List<XuefaQuestionsCrawler>> questionMap = Maps.newHashMap();
		for(XuefaQuestionsCrawler item : lists){
			File output;
			try {
				if(item.getLaw() != null){
					String outputFile = filePath + item.getLaw() + "_" + item.getChapter();
					output = new File(outputFile);
					Files.append(item.toString(), output, Charsets.UTF_8);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
			}
		}
	}
	
}
