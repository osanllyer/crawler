package com.lfb.libman;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.lfb.configure.SecondaryMapper;

@SecondaryMapper
@Mapper
public interface SqliteMapper {

	//更新sqlite的question
	@Update("UPDATE question_answer SET chapter_id = #{chapter_id}, question = #{question}, a=#{a}, b=#{b}, c=#{c}, d=#{d}, answer=#{answer}, analysis=#{analysis} WHERE id = #{id}")
	public void updateQuestions(Question q);
	
	/**
	 * 
	 */
	@Delete("DELETE FROM question_answer")
	public void delete();
	
	@Insert("INSERT INTO question_answer(id, type, chapter_id, question, a, b, c, d, answer, analysis, paper, published_at, real_seq, last_modified) VALUES (#{id}, #{type}, #{chapter_id}, #{question}, #{a}, #{b}, #{c}, #{d}, #{answer}, #{analysis}, #{paper}, #{published_at}, #{real_seq}, #{last_modified_sqlite})")
	public void insert(Question q);
}
