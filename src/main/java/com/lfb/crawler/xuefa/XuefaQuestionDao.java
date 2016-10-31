package com.lfb.crawler.xuefa;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface XuefaQuestionDao {

	@Insert("insert into xuefa_question(xuefaid, type, question, a, b, c, d, answer, analysis, law, chapter, url) values(#{xuefaId}, #{type}, #{question}, #{a}, #{b}, #{c}, #{d}, #{answer}, #{analysis}, #{law}, #{chapter}, #{url})")
	public void add(XuefaQuestionsCrawler xuefa);
	
	@Results
	@Select("select * from xuefa_question")
	public List<XuefaQuestionsCrawler> get();
	
	@Results
	@Select("select * from xuefa_question order by rand() limit 100")
	public List<XuefaQuestionsCrawler> getTest();	
	
	@Results
	@Select("SELECT distinct law FROM xuefa_question")
	public List<String> loadLaw();
	
	@Update("UPDATE xuefa_question SET question = #{question} WHERE xuefaid = #{xuefaId}")
	public void updateQuestion(XuefaQuestionsCrawler q);
	
	@Update("UPDATE xuefa_question SET answer = #{answer} WHERE xuefaid = #{xuefaId}")
	public void updateAnswer(XuefaQuestionsCrawler q);
	
	@Update("UPDATE xuefa_question SET analysis = #{analysis} WHERE xuefaid = #{xuefaId}")
	public void updateAnalysis(XuefaQuestionsCrawler q);
	
	@Update("UPDATE xuefa_question SET chapter = #{chapter} WHERE xuefaid = #{xuefaId}")
	public void updateChapter(XuefaQuestionsCrawler q);
	
	@Update("UPDATE xuefa_question SET law = #{law} WHERE xuefaid = #{xuefaId}")
	public void updateLaw(XuefaQuestionsCrawler q);
	
	@Results
	@Select("select name FROM law_chapter")
	public List<String> lawChapter();
	
	@Results
	@Select("select name FROM xuefa_chapter")
	public List<String> xuefaChapter();	

}
