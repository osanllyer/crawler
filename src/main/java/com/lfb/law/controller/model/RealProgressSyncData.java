package com.lfb.law.controller.model;

import java.util.Map;

/**
 * 真题进度备份
 * @author osanllyer
 *
 */
public class RealProgressSyncData implements SyncItem{

	private Integer year;
	private Integer exampaper;
	private Integer qid;
	
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getExampaper() {
		return exampaper;
	}
	public void setExampaper(Integer exampaper) {
		this.exampaper = exampaper;
	}
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}
	
	@Override
	public SyncItem valueOf(Map mapData) {
		RealProgressSyncData data = new RealProgressSyncData();
		data.setExampaper(Integer.valueOf(mapData.get("exampaper").toString()));
		data.setQid(Integer.valueOf(mapData.get("qid").toString()));
		data.setYear(Integer.valueOf(mapData.get("year").toString()));
		return data;
	}
	
}
