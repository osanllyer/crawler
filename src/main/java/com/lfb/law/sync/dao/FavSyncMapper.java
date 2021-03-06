package com.lfb.law.sync.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import com.lfb.law.controller.model.SyncDataAdapter;

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
}
