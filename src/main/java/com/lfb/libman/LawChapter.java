package com.lfb.libman;

public class LawChapter {

	public Integer getLawid() {
		return lawid;
	}
	public void setLawid(Integer lawid) {
		this.lawid = lawid;
	}
	public Integer getChapterid() {
		return chapterid;
	}
	public void setChapterid(Integer chapterid) {
		this.chapterid = chapterid;
	}
	public String getLawname() {
		return lawname;
	}
	public void setLawname(String lawname) {
		this.lawname = lawname;
	}
	public String getChaptername() {
		return chaptername;
	}
	public void setChaptername(String chaptername) {
		this.chaptername = chaptername;
	}
	
	private Integer lawid;
	private Integer chapterid;
	private String lawname;
	private String chaptername;
	
}
