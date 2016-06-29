package com.lfb.libman;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lfb.configure.PrimaryMapper;

@PrimaryMapper
@Mapper
public interface LibManMapper {

	/**
	 * 获取最新的日志记录，只返回一条
	 * @return
	 */
	@Select("SELECT * FROM lib_version_log ORDER BY last_modified DESC LIMIT 1")
	public VersionLog getVersionLog();
	
	@Results
	@Select("SELECT * FROM question_answer LIMIT #{start}, #{size}")
	public List<Question> questions(@Param("start") int start, @Param("size") int size);
	
	@Select("SELECT count(1) FROM question_answer")
	public Integer questionCount();
	
	@Select("SELECT l.id as lawid, l.name as lawname, c.id as chapterid, c.name as chaptername  FROM law l, law_chapter c where l.id = c.law_id")
	public List<LawChapter> lawChapters();
	
	@Update("UPDATE question_answer SET chapter_id = #{chapter_id}, question = #{question}, a=#{a}, b=#{b}, c=#{c}, d=#{d}, answer=#{answer}, analysis=#{analysis} WHERE id = #{id}")
	public void saveQuestion(Question question);
	
	/**
	 * 检查是否有更新
	 * @param updateTime 客户端的更新时间
	 * @return 更新数量和最新的时间
	 */
	@Select("SELECT count(1) as count, max(last_modified) as newestTime FROM question_answer WHERE last_modified > #{updateTime}")
	public Map checkUpdate(@Param("updateTime")Timestamp updateTime);
	
	@Results
	@Select("SELECT * FROM question_answer WHERE last_modified > #{updateTime}")
	public List<Question> getUpdate(@Param("updateTime")Timestamp updateTime);
	
	@Results
	@Select("SELECT l.name as law, count(1) as count  FROM question_answer qa, law_chapter c, law l where qa.chapter_id = c.id and c.law_id = l.id group by l.name; ")
	public List<Map> getQuestionStat();
	
	/**
	 * 检查app 版本，日志信息
	 * @return
	 */
	@Select("SELECT version, log, path FROM app_version ORDER BY last_modified DESC LIMIT 1")
	public Map appVersion();
}
