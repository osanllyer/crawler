package com.lfb.law.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lfb.law.controller.model.ErrorProgressSyncData;
import com.lfb.law.controller.model.ErrorSyncData;
import com.lfb.law.controller.model.FavProgressSyncData;
import com.lfb.law.controller.model.FavSyncData;
import com.lfb.law.controller.model.PracticeProgressSyncItem;
import com.lfb.law.controller.model.RealProgressSyncData;
import com.lfb.law.controller.model.SyncData.SyncType;
import com.lfb.law.controller.model.SyncDataAdapter;
import com.lfb.law.sync.CommonSyncProcessor;

/**
 * 管理用户的数据同步
 * @author osanllyer
 *
 */
@RestController
@RequestMapping(value="/sync", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SyncController {

		@Autowired
		CommonSyncProcessor commonSyncProcessor;
	
		@RequestMapping(method=RequestMethod.GET)
		public void syncFromServer(){
			
		}
		
		@RequestMapping(method=RequestMethod.POST)
		public void syncToServer(@RequestBody Map syncRequest){
			
			List<Map> datas = (List<Map>)syncRequest.get("syncDataList");
			
			for(Map data : datas){
				
				SyncType type = SyncType.valueOf(data.get("type").toString().trim().toUpperCase());
				switch(type){
				case FAV:
					//是收藏记录
					commonSyncProcessor.process(SyncDataAdapter.<FavSyncData>valueOf(data, FavSyncData.class));
					break;
				case FAV_PROGRESS:
					commonSyncProcessor.process(SyncDataAdapter.<FavProgressSyncData>valueOf(data, FavProgressSyncData.class));
					break;
				case ERRORS:
					commonSyncProcessor.process(SyncDataAdapter.<ErrorSyncData>valueOf(data, ErrorSyncData.class));
					break;
				case ERROR_PROGRESS:
					commonSyncProcessor.process(SyncDataAdapter.<ErrorProgressSyncData>valueOf(data, ErrorProgressSyncData.class));
					break;
				case PRACTICE_PROGRESS:
					commonSyncProcessor.process(SyncDataAdapter.<PracticeProgressSyncItem>valueOf(data, PracticeProgressSyncItem.class));
					break;
				case REAL_PROGRESS:
					commonSyncProcessor.process(SyncDataAdapter.<RealProgressSyncData>valueOf(data, RealProgressSyncData.class));
					break;
				default:
					break;
				}
				
			}
			
		}
}
