package com.lfb.law.sync.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lfb.law.controller.model.SyncDataAdapter;
import com.lfb.law.sync.model.FavSyncAllItem;

/**
 * 
 * @author osanllyer
 *
 */
public interface FavSyncMapper extends SyncMapper{
	
	@Insert("REPLACE INTO favorites (user, qid, add_at) VALUES(#{userid}, #{item.qid}, #{add_at})")
	public void add(SyncDataAdapter data);
	
	@Delete("DELETE from favorites where qid=#{item.qid} and user=#{userid}")
	public void delete(SyncDataAdapter data);
	
	@Results
	@Select("SELECT qid, add_at FROM favorites WHERE user = #{userid}")
	public List<FavSyncAllItem> getAll(@Param("userid")String userId);
	
	@Insert("INSERT INTO favorites(user, qid, add_at) VALUES (#{userId}, #{qid}, #{add_at})")
	public void addItem(FavSyncAllItem item);
	
	@Delete("DELETE FROM favorites WHERE user = #{userId} AND qid = #{qid}")
	public void deleteItem(FavSyncAllItem item);
	
	@Update("UPDATE favorites set add_at = #{add_at} WHERE user = #{userId} AND qid = #{qid}")
	public void updateItem(FavSyncAllItem item);
}
