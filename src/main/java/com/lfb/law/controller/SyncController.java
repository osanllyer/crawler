package com.lfb.law.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lfb.law.controller.model.FavSyncData;
import com.lfb.law.controller.model.SyncData.SyncType;
import com.lfb.law.sync.FavSyncProcessor;

/**
 * 管理用户的数据同步
 * @author osanllyer
 *
 */
@RestController
@RequestMapping(value="/sync", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SyncController {

		@Autowired
		FavSyncProcessor favSyncProcessor;
	
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
					favSyncProcessor.process(FavSyncData.valueOf(data));
					break;
				case FAVPROGRESS:
					break;
				}
				
			}
			
		}
}
