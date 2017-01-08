package com.lfb.comments;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Comment {

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public Integer getSupport() {
		return support;
	}
	public void setSupport(Integer support) {
		this.support = support;
	}
	public Integer getParent() {
		return parent;
	}
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	public Date getLast_modified() {
		return last_modified;
	}
	public void setLast_modified(Date last_modified) {
		this.last_modified = last_modified;
	}
	
	public Integer getChilds() {
		return childs;
	}
	public void setChilds(Integer childs) {
		this.childs = childs;
	}	
	
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}	

	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}	
	
	@Min(1)
	private Integer id;

	private Integer qid;
	
	private String user;
	
	private String content;
	
	private Boolean checked;
	
	@Min(0)
	private Integer support;
	private Integer parent;
	private Date last_modified;
	private Integer childs;
	private String avatar;
	private String nickname;
}
