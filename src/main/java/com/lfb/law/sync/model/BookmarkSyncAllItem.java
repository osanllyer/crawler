package com.lfb.law.sync.model;

import java.util.List;
import java.util.Map;

/**
 * 用来同步书签
 * 书签表共有以下字段：cid, seg_id, description, position
 * @author osanllyer
 *
 */
public class BookmarkSyncAllItem implements SyncAllItem<BookmarkSyncAllItem>{

	private String userId;
	private Integer cid;
	private Integer seg_id;
	private String description;
	private String position;
	
	@Override
	public void valueOf(Map map) {
		if (map == null) return;
		String cid = String.valueOf(map.get("cid"));
		String seg_id = String.valueOf(map.get("seg_id"));
		String description = String.valueOf(map.get("description"));
		String position = String.valueOf(map.get("position"));
		this.setCid(Integer.valueOf(cid));
		this.setSeg_id(Integer.valueOf(seg_id));
		this.setDescription(description);
		this.setPosition(position);
	}

	@Override
	public List<BookmarkSyncAllItem> valueOf(List<Map> mapList) {
		return null;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getSeg_id() {
		return seg_id;
	}

	public void setSeg_id(Integer seg_id) {
		this.seg_id = seg_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}	
	
}
