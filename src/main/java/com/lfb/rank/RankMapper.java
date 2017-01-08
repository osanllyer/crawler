package com.lfb.rank;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lfb.rank.Scorer.ScoreType;


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

	
	@Update("UPDATE user_rank SET score = score + #{score} WHERE user = #{username}")
	public void scoreLogin(@Param("username")String username, @Param("score")Integer score);

	/**
	 * 获取用户得分纪录
	 * @param user
	 * @param stype
	 * @return
	 */
	@Select("SELECT 1 FROM rank_history WHERE user = #{user} AND stype = #{stype} AND date(last_modified) = curdate()")
	public Integer getScoreHistory(@Param("user")String user, @Param("stype")ScoreType stype);

	/**
	 * 记录用户得分记录
	 * @param user
	 * @param score
	 * @param stype
	 */
	@Insert("REPLACE INTO score_history(user, score, stype) VALUES (#{user}, #{score}, #{stype})")
	public void scoreScoreHistory(String user, Integer score, ScoreType stype);
}
