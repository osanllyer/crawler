package com.lfb.comments;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CommenMapper {

	/**
	 * 插入到评论表
	 * @param c
	 */
	@Insert("INSERT INTO comments (user, content, qid, parent) VALUES (#{user}, #{content}, #{qid}, #{parent})")
	public void insert(Comment c);
	
	/**
	 * 更新一下支持数量
	 * @param c
	 */
	@Update("update comments c left join user_support u on (c.id = u.comment_id and c.user = u.user) set support = support + 1 where c.id = #{id} and c.user = #{user} and u.comment_id is null ;")
	public void updateSupport(@Param("id")Integer id, @Param("user")String user);
	
	@Update("REPLACE INTO user_support(USER, COMMENT_ID) VALUES(#{user}, #{commentId})")
	public void updateSupportRelation(@Param("user")String user, @Param("commentId")Integer commentId);
	
	/**
	 * 更新审核结果
	 */
	@Update("UPDATE comments SET checked = #{chekced} WHERE id = #{id}")
	public void updaetChecked(Comment c);
	
	/**
	 * 获取一个问题的评论列表
	 * @param qid
	 * @param from
	 * @param size
	 * @return
	 */
	@Results
	@Select("SELECT c1.*, count(c2.parent) AS childs, u.avatar, u.nickname FROM comments c1 LEFT JOIN comments c2 ON (c1.id = c2.parent) JOIN users u ON(c1.user = u.username) WHERE c1.checked = 1 AND c1.qid = #{qid} AND c1.parent is NULL GROUP BY c1.id ORDER BY c1.last_modified DESC LIMIT #{from}, #{size}")
	public List<Comment> getComments(@Param("qid")Integer qid, @Param("from")Integer from, @Param("size")Integer size);

	/**
	 * 获取comment和它的回复
	 * @param commentId
	 * @param from
	 * @param size
	 * @return
	 */
	@Results
	@Select("SELECT c.*, u.avatar, u.nickname FROM comments c, users u WHERE c.user = u.username AND parent = #{commentId} AND checked = 1 ORDER BY last_modified DESC LIMIT #{from}, #{size}")
	public List<Comment> getCommentAndReply(@Param("commentId")Integer commentId, @Param("from")Integer from,
			@Param("size")Integer size);
}
