package com.lfb.law.sync;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lfb.law.sync.dao.PracticeProgressSyncMapper;
import com.lfb.law.sync.model.PracticeProgressSyncAllItem;
import com.lfb.law.sync.model.SyncAllDataAdapter;

@Component
public class PracticeProgressSyncAllProcesser {

	@Autowired
	private PracticeProgressSyncMapper mapper;
	
	public List<PracticeProgressSyncAllItem> process(SyncAllDataAdapter<PracticeProgressSyncAllItem> adapter){
		
		String userId = adapter.getUserid();
		
		if(adapter.getTobeAdd().isEmpty() && adapter.getTobeDelete().isEmpty() && adapter.getTobeUpdate().isEmpty()){
			
			return mapper.get(userId);
			
		}
		
		//插入
		List<PracticeProgressSyncAllItem> add = adapter.getTobeAdd();
		
		for(PracticeProgressSyncAllItem item : add){
			item.setUserid(userId);
			mapper.addItem(item);
		}
		
		List<PracticeProgressSyncAllItem> update = adapter.getTobeUpdate();
		for(PracticeProgressSyncAllItem item : update){
			item.setUserid(userId);			
			mapper.updateItem(item);
		}		
		
		List<PracticeProgressSyncAllItem> del = adapter.getTobeDelete();
		for(PracticeProgressSyncAllItem item : del){
			item.setUserid(userId);
			mapper.deleteItem(item);
		}		
		return null;
	}
	
}
