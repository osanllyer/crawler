package com.lfb.rank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 计分器
 * @author osanllyer
 *
 */
@Component
public class Scorer {

	public static enum ScoreType{
		LOGIN(1, "login"),
		ERROR_QUESTION(2, "eq"),
		CORRECT_QUESTION(3, "cq");
		
		private int value;
		private String desc;

		private ScoreType(int value, String desc){
			this.value = value;
			this.desc = desc;
		}
	}
	
	@Autowired
	RankMapper rankMapper;
	
	/**
	 * 每次登录的分数
	 * @param user
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void loginScore(String user){
		Integer score = calcLoginScore(user);
		if(score != 0){
			rankMapper.scoreLogin(user, score);
			rankMapper.scoreScoreHistory(user, score, ScoreType.LOGIN);
		}
		return;
	}
	
	/**
	 * 每次登录得分计算方法
	 * @return
	 */
	private Integer calcLoginScore(String user){
		//判断今天是否已经登录过，如果登录过，返回0，没有登录过，返回5
		Integer score = rankMapper.getScoreHistory(user, ScoreType.LOGIN);
		return score != null ? 5 : 0;
	}
	
	
}
