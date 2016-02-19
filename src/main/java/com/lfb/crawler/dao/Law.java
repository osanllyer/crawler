package com.lfb.crawler.dao;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author lifenbo
 *
 */
public class Law{
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getContent() {
		return content;
	}
	public void setContent(List<String> content) {
		this.content = content;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public Date getValidSince() {
		return validSince;
	}
	public void setValidSince(Date validSince) {
		this.validSince = validSince;
	}
	public String getDocNum() {
		return docNum;
	}
	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	private String title;
	private List<String> content;
	private Date publishDate;
	private Date validSince;
	private String docNum;
	private String publisher;
	
}
