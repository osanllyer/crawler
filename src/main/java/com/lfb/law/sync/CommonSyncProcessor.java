package com.lfb.law.sync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lfb.law.controller.model.SyncData.SyncAction;
import com.lfb.law.controller.model.SyncData.SyncType;
import com.lfb.law.controller.model.SyncDataAdapter;
import com.lfb.law.sync.dao.ErrorProgressSyncMapper;
import com.lfb.law.sync.dao.ErrorsSyncMapper;
import com.lfb.law.sync.dao.FavProgressSyncMapper;
import com.lfb.law.sync.dao.FavSyncMapper;
import com.lfb.law.sync.dao.PracticeProgressSyncMapper;
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
	
	
	@Override
	public void process(SyncDataAdapter data) {
		
		SyncMapper mapper = null;
		
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
		default:
			break;
		}
		
	}

}
