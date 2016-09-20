package com.lfb.law.controller.model;

import java.util.Map;


/**
 * 收藏数据
 * @author osanllyer
 *
 */
public class FavSyncData implements SyncItem{

	private Integer qid;
	
	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	@Override
	public SyncItem valueOf(Map mapData) {
		String qid = mapData.get("qid").toString();
		FavSyncData item = new FavSyncData();
		item.setQid(Integer.valueOf(qid));
		return item;
	}
	
}
