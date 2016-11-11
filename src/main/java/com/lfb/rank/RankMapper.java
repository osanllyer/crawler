package com.lfb.rank;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface RankMapper {

	/*
	 * 获取某个用户的排名和得分
	 * */
	@Select("SELECT @rowno:=@rowno+1 as rank, score from user_rank,(select @rowno:=0) t WHERE user = #{username}")
	public Map userRank(@Param("username")String username);
	
	
	/**
	 * 获取排名列表
	 */
	@Results
	@Select("SELECT user, score, avatar, nickname FROM user_rank ur, users u WHERE ur.user = u.username ORDER BY score DESC LIMIT #{size}")
	public List<Map> topRank( @Param("size")int size);
}
