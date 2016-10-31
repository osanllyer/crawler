package com.lfb.crawler.xuefa;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class QuestionFormater {

	public static final String FIELD_LAW = "law";
	public static final String FIELD_QUESTION = "question";
	public static final String FIELD_CHAPTER = "chapter";
	public static final String FIELD_A = "a";
	public static final String FIELD_B = "b";
	public static final String FIELD_C = "c";
	public static final String FIELD_D = "d";
	public static final String FIELD_ANSWER = "answer";
	public static final String FIELD_ANALYSIS = "analysis";
	
	private Logger logger = LoggerFactory.getLogger(QuestionFormater.class);
	
	private Map<String, List<LawFilter>> filterListMap = Maps.newHashMap();
	
	@Autowired
	private XuefaQuestionDao dao;
	
	@Autowired
	private ChapterMapper chapterMapper;
	
	public QuestionFormater(){
		init();
	}
	
	/**
	 * 
	 * @param field 字段名称
	 * @param filter 过滤器
	 */
	public  void registFilter(String field, LawFilter filter){
		List<LawFilter> list = filterListMap.get(field);
		if(list == null){
			list = Lists.newArrayList();
			filterListMap.put(field, list);
		}
		list.add(filter);
	}
	
	
	private void updateField(XuefaQuestionsCrawler q, String field, String content){
		if(field.equalsIgnoreCase(FIELD_QUESTION)){
			q.setQuestion(content);
			dao.updateQuestion(q);
		}
		if(field.equalsIgnoreCase(FIELD_ANSWER)){
			q.setAnswer(content);
			dao.updateAnswer(q);
		}
		if(field.equalsIgnoreCase(FIELD_ANALYSIS)){
			q.setAnalysis(content);
			dao.updateAnalysis(q);
		}
		if(field.equalsIgnoreCase(FIELD_LAW)){
			q.setLaw(content);
			dao.updateLaw(q);
		}
		if(field.equalsIgnoreCase(FIELD_CHAPTER)){
			q.setChapter(content);
			dao.updateChapter(q);
		}
	}

	private void applyFilter(XuefaQuestionsCrawler q, String field, String s, List<LawFilter> filterList){
		if(s == null){
			return;
		}
		String t = s;
		for(LawFilter filter : filterList){
			t = filter.filte(t);
		}
		if (!s.equalsIgnoreCase(t)){
			logger.info(s);
			logger.info("->");
			logger.info(t);
			logger.info("---------------------");

			updateField(q, field, t);
		}
	}
	
	private void formatQuestion(XuefaQuestionsCrawler q){
		
		if(filterListMap.get(FIELD_QUESTION) != null){
			applyFilter(q, FIELD_QUESTION, q.getQuestion(), filterListMap.get(FIELD_QUESTION));
		}
		if(filterListMap.get(FIELD_LAW) != null){
			applyFilter(q, FIELD_LAW, q.getLaw(), filterListMap.get(FIELD_LAW));
		}
		if(filterListMap.get(FIELD_CHAPTER) != null){
			applyFilter(q, FIELD_CHAPTER, q.getChapter(), filterListMap.get(FIELD_CHAPTER));
		}
		if(filterListMap.get(FIELD_ANSWER) != null){
			applyFilter(q, FIELD_ANSWER, q.getAnswer(), filterListMap.get(FIELD_ANSWER));
		}
		if(filterListMap.get(FIELD_ANALYSIS) != null){
			applyFilter(q, FIELD_ANALYSIS, q.getAnalysis(), filterListMap.get(FIELD_ANALYSIS));
		}
	}
	
	public void format(){
		List<XuefaQuestionsCrawler> questions = dao.get();
		for(XuefaQuestionsCrawler q : questions){
			formatQuestion(q);
		}
	}
	
	public void init(){
		initQuestionFilter();
		initAnalysisFilter();
		initAnswerFilter();
		initChapterFilter();
		initLawFilter();
	}
	
	private void initQuestionFilter(){
		//删除开头的题号
		LawFilter filter = new CommonRegxLawFilter("^\\d+\\.\\s*", "");
		registFilter(FIELD_QUESTION, filter); 
		//删除句子
		filter = new SentenceFilter();
		registFilter(FIELD_QUESTION, filter);
	}
	
	private void initAnalysisFilter(){
		//|学\法\网|
		LawFilter filter = new CommonRegxLawFilter("\\|学.?法.?网\\|?", "");
		registFilter(FIELD_ANALYSIS, filter);
		
		filter = new CommonRegxLawFilter("\\|xuefa\\.com\\|", "");
		registFilter(FIELD_ANALYSIS, filter);
		
		filter = new CommonRegxLawFilter("(\\[|［|【)学.?法.?网.*?(\\]|］|】)", "");
		registFilter(FIELD_ANALYSIS, filter);
		
		filter = new CommonRegxLawFilter("(\\[|【)学法提示(】|\\])", "");
		registFilter(FIELD_ANALYSIS, filter);
		
		filter = new CommonRegxLawFilter("\\/学 法 网 xuefa\\.com\\/", "");
		registFilter(FIELD_ANALYSIS, filter);
		
		filter = new CommonRegxLawFilter("（学 法 网）", "");
		registFilter(FIELD_ANALYSIS, filter);
		//删除句子
		filter = new SentenceFilter();
		registFilter(FIELD_ANALYSIS, filter);
	}
	
	private void initAnswerFilter(){
		LawFilter filter = new CommonRegxLawFilter(",", "");
		registFilter(FIELD_ANSWER, filter); 
	}
	
	/**
	 * 需要将law替换为库中
	 */
	private void initChapterFilter(){
		LawFilter filter = new ChapterMapper();
		registFilter(FIELD_CHAPTER, filter);
	}
	
	/**
	 * 需要将chapter替换为库中
	 */	
	private void initLawFilter(){
		CommonMapFilter filter = new CommonMapFilter();
		filter.addMap("社会主义法治理念", "中国特色社会主义法治理论");
		filter.addMap("司法制度和法律职业道德", "司法制度与法律职业道德");
		filter.addMap("刑事诉讼法【最新更新】", "刑事诉讼法");
		filter.addMap("民诉与仲裁【更新中】", "民事诉讼法与仲裁制度");
		registFilter(FIELD_LAW, filter);
	}
}
