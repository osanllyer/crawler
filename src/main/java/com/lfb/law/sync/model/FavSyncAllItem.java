package com.lfb.law.sync.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;


public class FavSyncAllItem implements SyncAllItem<FavSyncAllItem>{

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


	private Integer qid;
	private Date add_at;
	
	public Date getAdd_at() {
		return add_at;
	}

	public void setAdd_at(Date add_at) {
		this.add_at = add_at;
	}

	@Override
	public void valueOf(Map map) {
		
		if(map == null){
			return;
		}
		
		String qid = String.valueOf(map.get("qid"));
		String timestamp = String.valueOf(map.get("last_modified"));
		
		try {
			setQid(Integer.valueOf(qid));
			setAdd_at(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timestamp));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<FavSyncAllItem> valueOf(List<Map> mapList) {
		
		List<FavSyncAllItem> list = Lists.newArrayList();
		
		if(mapList == null){
			return null;
		}
		
		for(Map map : mapList){
			FavSyncAllItem item = new FavSyncAllItem();
			item.valueOf(map);
			list.add(item);
		}
		
		return list;
	}

	
}
