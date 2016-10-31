package com.lfb.law.sync.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lfb.law.controller.model.SyncDataAdapter;
import com.lfb.law.sync.model.PracticeProgressSyncAllItem;

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
	
	@Results
	@Select("SELECT chapter_id, law_id, qid, type FROM practice_progress WHERE userid = #{userid}")
	public List<PracticeProgressSyncAllItem> get(@Param("userid") String userid);
	
	@Insert("REPLACE INTO practice_progress (userid, chapter_id, qid, law_id, type) VALUES(#{userid}, #{chapter_id}, #{qid}, #{law_id}, #{type})")
	public void addItem(PracticeProgressSyncAllItem item);
	
	@Update("UPDATE practice_progress set qid = #{qid} WHERE userid = #{userid} AND chapter_id = #{chapter_id} AND law_id = #{law_id} AND type = #{type}")
	public void updateItem(PracticeProgressSyncAllItem item);
	
	@Delete("DELETE FROM practice_progress WHERE userid = #{userid} AND chapter_id = #{chapter_id} AND law_id = #{law_id} AND type = #{type}")
	public void deleteItem(PracticeProgressSyncAllItem item);
}
