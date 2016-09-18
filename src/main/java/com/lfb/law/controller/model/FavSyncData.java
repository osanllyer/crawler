package com.lfb.law.controller.model;

import java.util.Map;


/**
 * 收藏数据
 * @author osanllyer
 *
 */
public class FavSyncData extends SyncDataAdapter {

	private Integer qid;
	
	public FavSyncData(){
		this.setType(SyncType.FAV);
	}
	
	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}
	
	/**
	 * 从map构建syncdata
	 * @param mapData
	 * @return
	 */
	public static FavSyncData valueOf(Map mapData){
		
		FavSyncData data = new FavSyncData();
		
		String action = mapData.get("action").toString();
		String add_at = mapData.get("add_at").toString();
		String type = mapData.get("type").toString();
		String userId = mapData.get("userid").toString();
		Integer qid = Integer.valueOf(mapData.get("qid").toString());
		
		data.setAction(SyncAction.valueOf(action.trim().toUpperCase()));
		data.setAdd_at(add_at);
		data.setType(SyncType.valueOf(type.trim().toUpperCase()));
		data.setUserid(userId);
		data.setQid(qid);
		
		return data;
	}
	
}
