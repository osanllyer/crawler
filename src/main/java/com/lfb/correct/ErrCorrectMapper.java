package com.lfb.correct;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ErrCorrectMapper {

	@Insert("INSERT INTO correct_answer(user, law_id, chapter_id, qid, analysis, answer) VALUES(#{user}, #{law_id}, #{chapter_id}, #{qid}, #{analysis}, #{answer})")
	public void insert(ErrCorrectBody ecb);
	
}
