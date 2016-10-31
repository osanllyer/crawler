package com.lfb.crawler.xuefa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Entry {

	public static void main(String[] args){
		ApplicationContext appContext = new AnnotationConfigApplicationContext(XuefaAppConfig.class);
		Exporter exporter = appContext.getBean(Exporter.class);
		exporter.run();
//		QuestionFormater formater = appContext.getBean(QuestionFormater.class);
//		formater.format();
	}
	
}
