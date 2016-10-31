package com.lfb.law.sync;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.lfb.law.sync.dao.PracticeStatSyncMapper;
import com.lfb.law.sync.model.PracticeStatSyncAllItem;
import com.lfb.law.sync.model.SyncAllDataAdapter;

@Component
public class PracticeStatSyncAllProcessor {
	
	@Autowired
	PracticeStatSyncMapper mapper;
	

	public List<PracticeStatSyncAllItem> process(SyncAllDataAdapter<PracticeStatSyncAllItem> adapter){
		String userId = adapter.getUserid();
		
		if(adapter.getTobeAdd().isEmpty() && adapter.getTobeDelete().isEmpty() && adapter.getTobeUpdate().isEmpty()){
			return mapper.get(userId);
		}
		
		List<PracticeStatSyncAllItem> toAdd = adapter.getTobeAdd();
		for(PracticeStatSyncAllItem item : toAdd){
			item.setUserId(userId);
			mapper.addItem(item);
		}
		
		List<PracticeStatSyncAllItem> toUpadte = adapter.getTobeUpdate();
		for(PracticeStatSyncAllItem item : toUpadte){
			item.setUserId(userId);
			mapper.updateItem(item);
		}

//		List<PracticeStatSyncAllItem> toDelete = adapter.getTobeDelete();
//		for(PracticeStatSyncAllItem item : toDelete){
//			item.setUserId(userId);
//			mapper.deleteItem(item);
//		}		
		
		return Lists.newArrayList();
	}
	
}
