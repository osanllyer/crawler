package com.lfb.law.controller.model;

/**
 * 默认syncdata
 * @author osanllyer
 *
 */
public class SyncDataAdapter implements SyncData{

	private String add_at;
	private String userid;
	/**
	 * 增删改
	 * */
	private SyncAction action;
	/**
	 * 标志是什么操作，比如收藏备份或者练习备份
	 */
	private SyncType type;
	
	public SyncAction getAction() {
		return action;
	}

	public void setAction(SyncAction action) {
		this.action = action;
	}

	public SyncType getType() {
		return type;
	}

	public void setType(SyncType type) {
		this.type = type;
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAdd_at() {
		return add_at;
	}

	public void setAdd_at(String add_at) {
		this.add_at = add_at;
	}
	
}
