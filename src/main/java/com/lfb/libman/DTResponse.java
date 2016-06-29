package com.lfb.libman;

import java.util.List;

public class DTResponse {

	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public Integer getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public List<Question> getData() {
		return data;
	}
	public void setData(List<Question> data) {
		this.data = data;
	}
	
	
	private Integer draw;
	private Integer recordsTotal;
	private List<Question> data;
}
