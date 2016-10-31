package com.lfb.law.controller.model;

import java.util.Map;


/**
 * 收藏数据
 * @author osanllyer
 *
 */
public class EventSourceSyncItem implements SyncItem{

	private Integer qid;
	private boolean correct;
	
	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public boolean getCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}	
	
	@Override
	public SyncItem valueOf(Map mapData) {
		String qid = mapData.get("qid").toString();
		Boolean correct = Boolean.valueOf(mapData.get("correct").toString());
		EventSourceSyncItem item = new EventSourceSyncItem();
		item.setQid(Integer.valueOf(qid));
		item.setCorrect(correct);
		return item;
	}
	
}
