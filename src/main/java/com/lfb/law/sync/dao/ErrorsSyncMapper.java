package com.lfb.law.sync.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.lfb.law.controller.model.SyncDataAdapter;

@Mapper
public interface ErrorsSyncMapper extends SyncMapper{
	
	@Insert("REPLACE INTO errors (user, qid, add_at) VALUES(#{userid}, #{item.qid}, #{add_at})")
	public void add(SyncDataAdapter data);
	
	@Delete("DELETE from errors where qid=#{item.qid} and user=#{userid}")
	public void delete(SyncDataAdapter data);
}
