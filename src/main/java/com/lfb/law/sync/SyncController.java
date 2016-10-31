package com.lfb.law.sync;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.lfb.law.controller.model.ErrorProgressSyncData;
import com.lfb.law.controller.model.ErrorSyncData;
import com.lfb.law.controller.model.FavProgressSyncData;
import com.lfb.law.controller.model.PracticeProgressSyncItem;
import com.lfb.law.controller.model.RealProgressSyncData;
import com.lfb.law.controller.model.SyncData.SyncType;
import com.lfb.law.controller.model.SyncDataAdapter;
import com.lfb.law.sync.model.FavSyncAllItem;
import com.lfb.law.sync.model.PracticeProgressSyncAllItem;
import com.lfb.law.sync.model.PracticeStatSyncAllItem;
import com.lfb.law.sync.model.SyncAllDataAdapter;

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
		
		@Autowired
		FavSyncAllProcessor favSyncAllProcessor;
	
		@Autowired
		PracticeProgressSyncAllProcesser practiceProgressProcesser;
		
		@Autowired
		PracticeStatSyncAllProcessor statProcessor;
		
		@RequestMapping(method=RequestMethod.GET)
		public void syncFromServer(){
			
		}
		
		/**
		 * 第二版采用新的同步方法
		 * @param syncRequest
		 */
		@RequestMapping(method=RequestMethod.POST, params="version=2")
		public ResponseEntity<List> syncToServerV2(@RequestBody Map syncRequest){
			List<Map> datas = (List<Map>)syncRequest.get("syncDataList");
			
			if(datas == null){
				return null;
			}
			
			Map data = datas.get(0);
		
			SyncType type = SyncType.valueOf(data.get("type").toString().trim().toUpperCase());
			
			switch(type){
			case FAV:
				List<FavSyncAllItem> favResponse = favSyncAllProcessor.process(SyncAllDataAdapter.<FavSyncAllItem>valueOf(data, FavSyncAllItem.class));
				return new ResponseEntity(favResponse, HttpStatus.OK);
			
			case PRACTICE_PROGRESS:
				List<PracticeProgressSyncAllItem> practiceResponse = practiceProgressProcesser.process(SyncAllDataAdapter.<PracticeProgressSyncAllItem>valueOf(data, PracticeProgressSyncAllItem.class));
				return new ResponseEntity(practiceResponse, HttpStatus.OK);				
			
			case PRACTICE_STAT:
				List<PracticeStatSyncAllItem> statResponse = statProcessor.process(SyncAllDataAdapter.<PracticeStatSyncAllItem>valueOf(data, PracticeStatSyncAllItem.class));
				return new ResponseEntity(statResponse, HttpStatus.OK);								
				
			default:
				return new ResponseEntity(Lists.newArrayList(), HttpStatus.OK);
			}
			
		}
		
		/**
		 * 只适合实时同步
		 * @param syncRequest
		 */
		@RequestMapping(method=RequestMethod.POST, params="version=1")
		public ResponseEntity<List> syncToServer(@RequestBody Map syncRequest){
			List<Map> datas = (List<Map>)syncRequest.get("syncDataList");
			
			if(datas == null){
				return null;
			}
			
			//[{action=ALL, type=FAV, userid=13011111111, item=[]}]
			List list = Lists.newArrayList();
			
			for(Map data : datas){
				
				SyncType type = SyncType.valueOf(data.get("type").toString().trim().toUpperCase());
				switch(type){
//				case FAV:
//					//是收藏记录
//					commonSyncProcessor.process(SyncDataAdapter.<FavSyncData>valueOf(data, FavSyncData.class));
//					break;
				case FAV_PROGRESS:
					list = commonSyncProcessor.process(SyncDataAdapter.<FavProgressSyncData>valueOf(data, FavProgressSyncData.class));
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
//				case PRACTICE_STAT:
//					commonSyncProcessor.process(SyncDataAdapter.<PracticeStatSyncItem>valueOf(data, PracticeStatSyncItem.class));
//					break;
//				case EVENT_SOURCE:
//					commonSyncProcessor.process(SyncDataAdapter.<EventSourceSyncItem>valueOf(data, EventSourceSyncItem.class));
//					break;					
				default:
					break;
				}
				
			}
			
			return new ResponseEntity<List>(list, HttpStatus.OK);
			
		}
}
