package com.lfb.law.sync.model;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

public class PracticeStatSyncAllItem implements SyncAllItem<PracticeStatSyncAllItem>{

	private Integer qid;
	private Integer error_num;
	private Integer correct_num;
	private String userId;
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public Integer getError_num() {
		return error_num;
	}

	public void setError_num(Integer error_num) {
		this.error_num = error_num;
	}

	public Integer getCorrect_num() {
		return correct_num;
	}

	public void setCorrect_num(Integer correct_num) {
		this.correct_num = correct_num;
	}

	
	@Override
	public void valueOf(Map map) {
		this.qid = Integer.valueOf(map.get("qid").toString());
		this.correct_num = Integer.valueOf(map.get("correct_num").toString());
		this.error_num = Integer.valueOf(map.get("error_num").toString());
	}

	@Override
	public List<PracticeStatSyncAllItem> valueOf(List<Map> mapList) {
		List<PracticeStatSyncAllItem> list = Lists.newArrayList();
		
		for(Map map : mapList){
			PracticeStatSyncAllItem item = new PracticeStatSyncAllItem();
			item.valueOf(map);
			list.add(item);
		}
		
		return list;
	}
}
