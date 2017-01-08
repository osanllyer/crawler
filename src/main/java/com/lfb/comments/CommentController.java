package com.lfb.comments;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/commenturl", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CommentController {

	@Autowired
	private CommenMapper mapper;
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity insert(@RequestBody Comment c){
		mapper.insert(c);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	/**
	 * 更新支持度, 如果有重复的，则不更新
	 * @return
	 * @throws SQLException 
	 */
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor={SQLException.class})	
	public ResponseEntity updateSupport(@RequestBody Map map){
		Integer commentId = Integer.valueOf(map.get("comment_id").toString());
		String user = map.get("user").toString();
		mapper.updateSupport(commentId, user);
		mapper.updateSupportRelation(user, commentId);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	/**
	 * 获取评论列表
	 * @param qid
	 * @param from
	 * @param size
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity getComments(@RequestParam("qid")Integer qid, @RequestParam("from")Integer from, @RequestParam("size")Integer size){
		List<Comment> commentList = mapper.getComments(qid, from, size);
		return new ResponseEntity(commentList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{commentid}",method=RequestMethod.GET)
	public ResponseEntity getCommentAndReply(@PathVariable("commentid")Integer commentId, @RequestParam("from")Integer from, @RequestParam("size")Integer size){
		List<Comment> commentList = mapper.getCommentAndReply(commentId, from, size);
		return new ResponseEntity(commentList, HttpStatus.OK);
	}
}
