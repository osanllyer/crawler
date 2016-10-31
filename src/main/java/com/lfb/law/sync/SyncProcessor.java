package com.lfb.law.sync;

import java.util.List;

import com.lfb.law.controller.model.SyncDataAdapter;
import com.lfb.law.controller.model.SyncItem;

public interface SyncProcessor {
	
	 <T extends SyncItem> List<T> process(SyncDataAdapter data);

}
