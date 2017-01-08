package com.lfb.law.sync;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lfb.law.sync.dao.BookmarkSyncMapper;
import com.lfb.law.sync.model.BookmarkSyncAllItem;
import com.lfb.law.sync.model.SyncAllDataAdapter;

public class BookmarkSyncAllProcessor {

	@Autowired
	BookmarkSyncMapper mapper;
	
	public List<BookmarkSyncAllItem> process(SyncAllDataAdapter<BookmarkSyncAllItem> adapter){
		String userId = adapter.getUserid();
		
		if(adapter.getTobeAdd().isEmpty() && adapter.getTobeDelete().isEmpty() && adapter.getTobeUpdate().isEmpty()){
			return mapper.get(userId);
		}
		
		return null;
	}
	
}
