package com.lfb.law.sync.dao;

import org.apache.ibatis.annotations.Insert;

import com.lfb.law.controller.model.SyncDataAdapter;

/**
 * 处理收藏的练习进度
 * @author osanllyer
 *
 */
public interface PracticeEventSourceSyncMapper extends SyncMapper {
	
	@Insert("REPLACE INTO practice_eventsource (userid, qid, correct, add_at) VALUES(#{userid}, #{item.qid}, #{item.correct}, #{add_at})")
	public void add(SyncDataAdapter data);
}
