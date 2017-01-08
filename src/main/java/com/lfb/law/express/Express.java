package com.lfb.law.express;

import java.util.Date;

/**
 * express
 * @author osanllyer
 *
 */
public class Express {

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public Date getPublished() {
		return published;
	}
	public void setPublished(Date published) {
		this.published = published;
	}	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	
	public Integer getRead() {
		return read;
	}
	public void setRead(Integer read) {
		this.read = read;
	}	
	
	private String title;
	private String desc;
	private String content;
	private String author;
	private Date published;
	private Integer id;
	private Integer read;
	
}
