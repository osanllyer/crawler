package com.lfb.law.sync.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import com.lfb.law.controller.model.SyncDataAdapter;

/**
 * 处理收藏的练习进度
 * @author osanllyer
 *
 */
public interface PracticeProgressSyncMapper extends SyncMapper {

	@Insert("REPLACE INTO practice_progress (userid, chapter_id, qid, law_id, type, add_at) VALUES(#{userid}, #{item.chapter_id}, #{item.qid}, #{item.law_id}, #{item.type}, #{add_at})")
	public void add(SyncDataAdapter data);
	
	@Delete("DELETE from practice_progress where userid=#{userid} and chapter_id = #{item.chapter_id} and type = #{item.type}")
	public void delete(SyncDataAdapter data);
	
}
