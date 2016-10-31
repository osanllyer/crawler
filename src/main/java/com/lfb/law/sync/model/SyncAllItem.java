package com.lfb.law.sync.model;

import java.util.List;
import java.util.Map;

public interface SyncAllItem<T> {

	public void valueOf(Map map);
	public List<T> valueOf(List<Map> mapList);
	
}
