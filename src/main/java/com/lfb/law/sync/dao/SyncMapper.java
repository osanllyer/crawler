package com.lfb.law.sync.dao;

import com.lfb.law.controller.model.SyncDataAdapter;


/**
 * 同步mapper基类
 * @author osanllyer
 *
 */
public interface SyncMapper {
	
	public void add(SyncDataAdapter data);
	public void delete(SyncDataAdapter data);
}
