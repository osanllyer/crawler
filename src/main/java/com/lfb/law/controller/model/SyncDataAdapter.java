package com.lfb.law.controller.model;

import java.util.Map;

/**
 * 默认syncdata
 * @author osanllyer
 *
 */
public class SyncDataAdapter<T> implements SyncData{

	private String add_at;
	private String userid;
	/**
	 * 增删改s
	 * */
	private SyncAction action;
	/**
	 * 标志是什么操作，比如收藏备份或者练习备份
	 */
	private SyncType type;

	private T item;
	
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
	
	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}	
	
	/**
	 * 从map构建syncdata
	 * @param mapData
	 * @return
	 */
	public static <U extends SyncItem> SyncDataAdapter valueOf(Map mapData, Class<U> clazz){
		
		SyncDataAdapter<U> data = new SyncDataAdapter<U>();
		
		String action = mapData.get("action").toString();
		String add_at = mapData.get("add_at").toString();
		String type = mapData.get("type").toString();
		String userid = mapData.get("userid").toString();
		
		Map itemMap = (Map)mapData.get("item");
		U uItem;
		U item = null;
		try {
			uItem = clazz.newInstance();
			item = (U) uItem.valueOf(itemMap);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		data.setAction(SyncAction.valueOf(action));
		data.setAdd_at(add_at);
		data.setType(SyncType.valueOf(type));
		data.setUserid(userid);
		data.setItem(item);
		
		return data;
	}
	
}
