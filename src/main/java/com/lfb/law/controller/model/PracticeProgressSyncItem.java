package com.lfb.law.controller.model;

import java.util.Map;

/**
 * 练习进度
 * @author osanllyer
 *
 */
public class PracticeProgressSyncItem implements SyncItem {

	private Integer chapter_id;
	private Integer qid;
	private Integer type;
	private Integer law_id;
	
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
	public SyncItem valueOf(Map mapData) {
		PracticeProgressSyncItem item = new PracticeProgressSyncItem();
		item.setChapter_id(Integer.valueOf(mapData.get("chapter_id").toString()));
		item.setLaw_id(Integer.valueOf(mapData.get("law_id").toString()));
		item.setQid(Integer.valueOf(mapData.get("qid").toString()));
		item.setType(Integer.valueOf(mapData.get("type").toString()));
		return item;
	}
}
