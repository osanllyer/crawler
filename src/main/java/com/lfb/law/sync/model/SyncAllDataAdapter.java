package com.lfb.law.sync.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lfb.law.controller.model.SyncData.SyncType;

/**
 * 同步所有数据的同步方式
 * @author osanllyer
 *
 */
public class SyncAllDataAdapter<T> implements SyncAllData {

	private SyncType type;
	private String userid;

	public List<T> getTobeAdd() {
		return tobeAdd;
	}

	public void setTobeAdd(List<T> tobeAdd) {
		this.tobeAdd = tobeAdd;
	}

	public List<T> getTobeDelete() {
		return tobeDelete;
	}

	public void setTobeDelete(List<T> tobeDelete) {
		this.tobeDelete = tobeDelete;
	}

	public List<T> getTobeUpdate() {
		return tobeUpdate;
	}

	public void setTobeUpdate(List<T> tobeUpdate) {
		this.tobeUpdate = tobeUpdate;
	}

	private List<T> tobeAdd = new ArrayList<T>();
	private List<T> tobeDelete = new ArrayList<T>();
	private List<T> tobeUpdate = new ArrayList<T>();

	public void addtoAdd(T t){
		tobeAdd.add(t);
	}
	
	public void addtoDelete(T t){
		tobeDelete.add(t);
	}
	
	public void addtoUpdate(T t){
		tobeUpdate.add(t);
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}	
	
	public SyncType getType() {
		return type;
	}

	public void setType(SyncType type) {
		this.type = type;
	}
	
	public static <U extends SyncAllItem> SyncAllDataAdapter valueOf(Map map, Class<U> clazz){
		SyncType type = SyncType.valueOf(map.get("type").toString().trim().toUpperCase());
		String userId = map.get("userid").toString().trim();
		SyncAllDataAdapter<U> data = new SyncAllDataAdapter<U>();
		data.setType(type);
		data.setUserid(userId);
		Map<String, List<Map>> actionMap = (Map<String, List<Map>>)map.get("item");
		
		if(actionMap == null){
			return data;
		}
		
		List<Map> addList = (List<Map>) (actionMap.get("add"));
		
		if(addList == null){
			return data;
		}
		
		for(Map m : addList){
			U uItem;
			try {
				uItem = clazz.newInstance();
				uItem.valueOf(m);
				data.addtoAdd(uItem);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		List<Map> deleteList = (List<Map>) (actionMap.get("delete"));
		
		for(Map m : deleteList){
			U uItem;
			try {
				uItem = clazz.newInstance();
				uItem.valueOf(m);
				data.addtoDelete(uItem);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		List<Map> updateList = (List<Map>) (actionMap.get("update"));
		
		for(Map m : updateList){
			U uItem;
			try {
				uItem = clazz.newInstance();
				uItem.valueOf(m);
				data.addtoUpdate(uItem);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}		
		
		return data;
	}
}
