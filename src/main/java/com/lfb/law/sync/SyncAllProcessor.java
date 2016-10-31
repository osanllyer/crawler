package com.lfb.law.sync;

import com.lfb.law.sync.model.SyncAllData;

/**
 * 处理一次同步所有内容的同步方式
 * @author osanllyer
 *
 */
public interface SyncAllProcessor {

	public void processor(SyncAllData data);
	
}
