package com.lfb.law.sync.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.lfb.law.controller.model.SyncDataAdapter;
import com.lfb.law.sync.model.BookmarkSyncAllItem;


@Mapper
public interface BookmarkSyncMapper extends SyncMapper{

	@Insert("INSERT INTO bookmark(user, cid, seg_id, description, position, add_at) VALUES(#{userid}, #{cid}, #{seg_id}, #{description}, #{position}, #{add_at})")
	public void add(SyncDataAdapter data);
	
	@Delete("DELETE FROM bookmark WHERE user = #{userid} AND cid = #{cid} AND seg_id = #{seg_id}")
	public void delete(SyncDataAdapter data);
	
	@Results
	@Select("SELECT * FROM bookmark WHERE user = #{userid}")
	public List<BookmarkSyncAllItem> get(@Param("userid")String userid);
	
}
