package com.lfb.law.sync.dao;

import java.util.List;

import com.lfb.law.controller.model.SyncDataAdapter;
import com.lfb.law.controller.model.SyncItem;


/**
 * 同步mapper基类
 * @author osanllyer
 *
 */
public interface SyncMapper {
	
	public void add(SyncDataAdapter data);
	public void delete(SyncDataAdapter data);
	public <T extends SyncItem> List<T> get(SyncDataAdapter data);
}
