package com.lfb.law.sync.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.lfb.law.controller.model.ErrorProgressSyncData;
import com.lfb.law.controller.model.SyncDataAdapter;

/**
 * 处理收藏的练习进度
 * @author osanllyer
 *
 */
public interface ErrorProgressSyncMapper extends SyncMapper {
	
	@Insert("REPLACE INTO error_progress (user, qid, add_at) VALUES(#{userid}, #{item.qid}, #{add_at})")
	public void add(SyncDataAdapter data);
	
	@Delete("DELETE from error_progress where qid=#{item.qid} and user=#{userid}")
	public void delete(SyncDataAdapter data);
	
	@Results
	@Select("SELECT * FROM error_progress WHERE user = #{userid}")
	public List<ErrorProgressSyncData> get(SyncDataAdapter data);
}
