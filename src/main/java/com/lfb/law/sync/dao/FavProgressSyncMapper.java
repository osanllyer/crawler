package com.lfb.law.sync.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.lfb.law.controller.model.FavProgressSyncData;
import com.lfb.law.controller.model.SyncDataAdapter;

/**
 * 处理收藏的练习进度
 * @author osanllyer
 *
 */
@Mapper
public interface FavProgressSyncMapper extends SyncMapper {
	
	@Insert("REPLACE INTO favorite_progress (user, qid, add_at) VALUES(#{userid}, #{item.qid}, #{add_at})")
	public void add(SyncDataAdapter data);
	
	@Delete("DELETE from favorite_progress where qid=#{item.qid} and user=#{userid}")
	public void delete(SyncDataAdapter data);
	
	@Results
	@Select("SELECT qid FROM favorite_progress WHERE user=#{userid}")
	public List<FavProgressSyncData> get(SyncDataAdapter data);
}
