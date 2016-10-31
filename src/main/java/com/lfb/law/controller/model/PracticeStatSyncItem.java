package com.lfb.law.controller.model;

import java.util.Map;


/**
 * 收藏数据
 * @author osanllyer
 *
 */
public class PracticeStatSyncItem implements SyncItem{

	private Integer qid;
	private Integer correct_num;
	private Integer error_num;
		
	
	public Integer getCorrect_num() {
		return correct_num;
	}

	public void setCorrect_num(Integer correct_num) {
		this.correct_num = correct_num;
	}

	public Integer getError_num() {
		return error_num;
	}

	public void setError_num(Integer error_num) {
		this.error_num = error_num;
	}

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	
	@Override
	public SyncItem valueOf(Map mapData) {
		String qid = mapData.get("qid").toString();
		Integer correct_num = Integer.valueOf(mapData.get("correct_num").toString());
		Integer error_num = Integer.valueOf(mapData.get("error_num").toString());
		PracticeStatSyncItem item = new PracticeStatSyncItem();
		item.setQid(Integer.valueOf(qid));
		item.setCorrect_num(correct_num);
		item.setError_num(error_num);
		return item;
	}
	
}
