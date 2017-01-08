package com.lfb.law.controller.model;

/**
 * 同步的单个数据，例如一个收藏列表
 * @author osanllyer
 *
 */
public interface SyncData {
	
	public static enum SyncType{
		FAV("FAV", 1),
		FAV_PROGRESS("FAV_PROGRESS", 2),
		REAL_PROGRESS("REALPROGRESS",3),
		ERROR_PROGRESS("ERROR_PROGRESS", 4),
		PRACTICE_PROGRESS("PRACTICE_PROGRESS", 5),
		ERRORS("ERRORS", 6),
		PRACTICE_STAT("PRACTICE_STAT", 7),
		EVENT_SOURCE("EVENT_SOURCE", 8),
		BOOKMARK("BOOKMARK", 9);
		
		private String type;
		private int idx;
		
		private SyncType(String type, int idx){
			this.type = type;
			this.idx = idx;
		}
		
		public String getType() {
			return type;
		}

		public int getIdx() {
			return idx;
		}
	}
	
	public static enum SyncAction{
		ADD("ADD", 1),
		DELETE("DELETE", 2),
		GET("GET", 3);
		
		private String action;
		private Integer idx;
		
		private SyncAction(String action, Integer idx){
			this.action = action;
			this.idx = idx;
		}
		
		public String getAction() {
			return action;
		}

		public Integer getIdx() {
			return idx;
		}
	}
	
	public SyncType getType();
	public SyncAction getAction();
	public String getAdd_at();
	public String getUserid();
}
