package com.lfb.law.sync.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import com.lfb.law.controller.model.FavProgressSyncData;
import com.lfb.law.controller.model.FavSyncData;

/**
 * 
 * @author osanllyer
 *
 */
public interface FavSyncMapper {
	
	@Insert("INSERT OR IGNORE INTO favorites (user, qid, add_at) VALUES(#{userid}, #{qid}, #{add_at})")
	public void add(FavSyncData data);
	
	@Delete("delete from favorites where qid=#{qid} and user=#{userid}")
	public void delete(FavSyncData data);

	@Insert("")
	public void addProgress(FavProgressSyncData data);
	
	@Delete("")
	public void deleteProgress(FavProgressSyncData data);
}
