package com.lfb.law.sync;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.lfb.law.sync.dao.FavSyncMapper;
import com.lfb.law.sync.model.FavSyncAllItem;
import com.lfb.law.sync.model.SyncAllDataAdapter;

@Component
public class FavSyncAllProcessor {

	@Autowired
	FavSyncMapper mapper;
	
	
	public List<FavSyncAllItem>  process(SyncAllDataAdapter<FavSyncAllItem> adapter){
		
		if(adapter.getTobeAdd().isEmpty() && adapter.getTobeDelete().isEmpty() && adapter.getTobeUpdate().isEmpty()){
			return fetch(adapter.getUserid());
		}

		//将新的数据插入数据库
		String userId = adapter.getUserid();
		List<FavSyncAllItem> tobeAdd = adapter.getTobeAdd();
		if(!tobeAdd.isEmpty()){
			for(FavSyncAllItem item : tobeAdd){
				item.setUserId(userId);
				mapper.addItem(item);
			}
		}

		List<FavSyncAllItem> tobeDelete = adapter.getTobeDelete();
		if(!tobeDelete.isEmpty()){
			for(FavSyncAllItem item : tobeDelete){
				item.setUserId(userId);				
				mapper.deleteItem(item);
			}
		}
		
		List<FavSyncAllItem> tobeUpdate = adapter.getTobeUpdate();
		if(!tobeUpdate.isEmpty()){
			for(FavSyncAllItem item : tobeUpdate){
				item.setUserId(userId);				
				mapper.updateItem(item);
			}
		}		
		
		return Lists.newArrayList();
	}
	
//	/**
//	 * 获取用户所有的数据
//	 * @param userId
//	 * @return
//	 */
//	private List<FavSyncAllItem> merge(String userId, List<FavSyncAllItem> clientItems){
//		List<FavSyncAllItem> itemList = mapper.getAll(userId);
//		boolean contain = false;
//		String action = "";
//		for(FavSyncAllItem localItem : itemList){
//			for (FavSyncAllItem clientItem : clientItems){
//				if(clientItem.getQid() == localItem.getQid()){
//					contain = true;
//					if(clientItem.getAdd_at().equals(localItem.getAdd_at())){
//						continue;
//					}else{
//						action = "update";
//					}
//				}
//			}
//		}
//		return itemList;
//	}
	
	private List<FavSyncAllItem> fetch(String userId){
		return mapper.getAll(userId);
	}
}
