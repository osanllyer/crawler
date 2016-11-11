package com.lfb.law.sync.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lfb.law.controller.model.SyncDataAdapter;
import com.lfb.law.sync.model.PracticeStatSyncAllItem;

/**
 * 处理收藏的练习进度
 * @author osanllyer
 *
 */
@Mapper
public interface PracticeStatSyncMapper extends SyncMapper {
	
	@Insert("REPLACE INTO practice_stat (userid, qid, error_num, correct_num) VALUES(#{userid}, #{item.qid}, #{item.error_num}, #{item.correct_num})")
	public void add(SyncDataAdapter data);
	
	@Delete("DELETE from practice_stat where qid=#{item.qid} and user=#{userid}")
	public void delete(SyncDataAdapter data);
	
	@Select("SELECT * FROM practice_stat WHERE userid = #{userid}")
	public List<PracticeStatSyncAllItem> get(@Param("userid")String userid);
	
	@Update("UPDATE practice_stat SET correct_num = #{correct_num}, error_num = #{error_num} WHERE userid = #{userId} AND qid = #{qid}")
	public void updateItem(PracticeStatSyncAllItem item);
	
	@Insert("INSERT INTO practice_stat(qid, correct_num, error_num, userid) VALUES(#{qid},#{correct_num},#{error_num}, #{userId})")
	public void addItem(PracticeStatSyncAllItem item);
	
}
