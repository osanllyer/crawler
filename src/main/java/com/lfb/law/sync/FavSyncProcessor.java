package com.lfb.law.sync;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.lfb.law.controller.model.FavProgressSyncData;
import com.lfb.law.controller.model.SyncData.SyncAction;
import com.lfb.law.controller.model.SyncDataAdapter;
import com.lfb.law.sync.dao.FavSyncMapper;

@Component
public class FavSyncProcessor implements SyncProcessor {

	@Autowired
	FavSyncMapper mapper;
	
	@Override
	public List<FavProgressSyncData> process(SyncDataAdapter data) {
		
		List<FavProgressSyncData> list = Lists.newArrayList();
		
		SyncAction sa = data.getAction();
		switch(sa){
		case ADD:
			mapper.add(data);
			break;
		case DELETE:
			mapper.delete(data);
			break;
		case GET:
			list = mapper.get(data);
		default:
			break;
		}
		
		return list;
	}

}
