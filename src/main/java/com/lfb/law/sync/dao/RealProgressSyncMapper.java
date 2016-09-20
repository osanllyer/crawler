package com.lfb.law.sync.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import com.lfb.law.controller.model.SyncDataAdapter;

/**
 * 处理收藏的练习进度
 * @author osanllyer
 *
 */
public interface RealProgressSyncMapper extends SyncMapper {

	@Insert("REPLACE INTO real_progress (userid, year, exampaper, qid, add_at) VALUES(#{userid}, #{item.year}, #{item.exampaper}, #{item.qid}, #{add_at})")
	public void add(SyncDataAdapter data);
	
	@Delete("DELETE from real_progress where qid=#{item.qid} and user=#{userid}")
	public void delete(SyncDataAdapter data);
	
}
