package com.lfb.law.sync;

import com.lfb.law.controller.model.SyncDataAdapter;

public interface SyncProcessor {
	
	void process(SyncDataAdapter data);

}
