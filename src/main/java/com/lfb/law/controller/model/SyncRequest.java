package com.lfb.law.controller.model;

import java.util.List;

/**
 * 用户同步请求
 * @author osanllyer
 *
 */
public class SyncRequest {

	private List<SyncData> syncDataList;

	public List<SyncData> getSyncDataList() {
		return syncDataList;
	}

	public void setSyncDataList(List<SyncData> syncDataList) {
		this.syncDataList = syncDataList;
	}
	
}
