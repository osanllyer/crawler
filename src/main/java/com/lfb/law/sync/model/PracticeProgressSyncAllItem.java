package com.lfb.law.sync.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

public class PracticeProgressSyncAllItem implements SyncAllItem<PracticeProgressSyncAllItem>{

	private Integer chapter_id;
	private Integer qid;
	private Integer type;
	private Integer law_id;
	private String userid;
	private Date add_at;
	
	public Date getAdd_at() {
		return add_at;
	}

	public void setAdd_at(Date add_at) {
		this.add_at = add_at;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	
	public Integer getChapter_id() {
		return chapter_id;
	}

	public void setChapter_id(Integer chapter_id) {
		this.chapter_id = chapter_id;
	}

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLaw_id() {
		return law_id;
	}

	public void setLaw_id(Integer law_id) {
		this.law_id = law_id;
	}
	
	@Override
	public void valueOf(Map map) {
		this.chapter_id = Integer.valueOf(map.get("chapter_id").toString());
		this.law_id = Integer.valueOf(map.get("law_id").toString());
		this.type = Integer.valueOf(map.get("type").toString());
		this.qid = Integer.valueOf(map.get("question_id").toString());
		try {
			this.add_at = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map.get("last_modified").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<PracticeProgressSyncAllItem> valueOf(List<Map> mapList) {
		List<PracticeProgressSyncAllItem> list = Lists.newArrayList();
		
		for(Map map : mapList){
			PracticeProgressSyncAllItem item = new PracticeProgressSyncAllItem();
			item.valueOf(map);
			list.add(item);
		}
		
		return list;
	}
}
