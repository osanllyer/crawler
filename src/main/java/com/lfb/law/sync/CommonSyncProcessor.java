package com.lfb.law.sync;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.lfb.law.controller.model.SyncData.SyncAction;
import com.lfb.law.controller.model.SyncData.SyncType;
import com.lfb.law.controller.model.SyncDataAdapter;
import com.lfb.law.controller.model.SyncItem;
import com.lfb.law.sync.dao.BookmarkSyncMapper;
import com.lfb.law.sync.dao.ErrorProgressSyncMapper;
import com.lfb.law.sync.dao.ErrorsSyncMapper;
import com.lfb.law.sync.dao.FavProgressSyncMapper;
import com.lfb.law.sync.dao.FavSyncMapper;
import com.lfb.law.sync.dao.PracticeEventSourceSyncMapper;
import com.lfb.law.sync.dao.PracticeProgressSyncMapper;
import com.lfb.law.sync.dao.PracticeStatSyncMapper;
import com.lfb.law.sync.dao.RealProgressSyncMapper;
import com.lfb.law.sync.dao.SyncMapper;

@Component
public class CommonSyncProcessor implements SyncProcessor {


	@Autowired
	FavSyncMapper favSyncMapper;
	
	@Autowired
	FavProgressSyncMapper favProgressSyncMapper;
	
	@Autowired
	RealProgressSyncMapper realProgressSyncMapper;
	
	@Autowired
	PracticeProgressSyncMapper practiceProgressSyncMapper;
	
	@Autowired
	ErrorProgressSyncMapper errorProgressSyncMapper;
	
	@Autowired
	ErrorsSyncMapper errorsSyncMapper;	
	
	@Autowired
	PracticeStatSyncMapper practiceStatSyncMapper;
	
	@Autowired
	PracticeEventSourceSyncMapper practiceEventSourceSyncMappper;
	
	@Autowired
	BookmarkSyncMapper bookmarkSyncMapper;
	
	
	@Override
	public List<SyncItem> process(SyncDataAdapter data) {
		
		SyncMapper mapper = null;
		
		List<SyncItem> resultList = Lists.newArrayList();
		
		SyncType type = data.getType();
		switch(type){
		case FAV:
			mapper = favSyncMapper;
			break;
		case FAV_PROGRESS:
			mapper = favProgressSyncMapper;
			break;
		case REAL_PROGRESS:
			mapper = realProgressSyncMapper;
			break;
		case ERROR_PROGRESS:
			mapper = errorProgressSyncMapper;
			break;
		case ERRORS:
			mapper = errorsSyncMapper;
			break;			
		case PRACTICE_PROGRESS:
			mapper = practiceProgressSyncMapper;
			break;
		case PRACTICE_STAT:
			mapper = practiceStatSyncMapper;
			break;
		case EVENT_SOURCE:
			mapper = practiceEventSourceSyncMappper;
			break;			

		default:
			break;
		}
		
		SyncAction sa = data.getAction();
		switch(sa){
		case ADD:
			mapper.add(data);
			break;
		case DELETE:
			mapper.delete(data);
			break;
		case GET:
			resultList = mapper.get(data);
			break;
		default:
			break;
		}
		
		return resultList;
	}

}
