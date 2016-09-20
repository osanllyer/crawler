package com.lfb.law.sync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lfb.law.controller.model.FavSyncData;
import com.lfb.law.controller.model.SyncData.SyncAction;
import com.lfb.law.controller.model.SyncDataAdapter;
import com.lfb.law.sync.dao.FavSyncMapper;

@Component
public class FavSyncProcessor implements SyncProcessor {

	@Autowired
	FavSyncMapper mapper;
	
	@Override
	public void process(SyncDataAdapter data) {
		
		SyncAction sa = data.getAction();
		switch(sa){
		case ADD:
			mapper.add(data);
			break;
		case DELETE:
			mapper.delete(data);
			break;
		default:
			break;
		}
		
	}

}
