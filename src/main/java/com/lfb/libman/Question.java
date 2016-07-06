package com.lfb.libman;

import java.sql.Timestamp;

public class Question {

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	public String getChapter_id() {
		return chapter_id;
	}
	public void setChapter_id(String chapter_id) {
		this.chapter_id = chapter_id;
	}
	public String getPublished_at() {
		return published_at;
	}
	public void setPublished_at(String published_at) {
		this.published_at = published_at;
	}
	public Timestamp getLast_modified() {
		return last_modified;
	}
	public void setLast_modified(Timestamp last_modified) {
		this.last_modified = last_modified;
	}
	public Integer getReal_seq() {
		return real_seq;
	}
	public void setReal_seq(Integer real_seq) {
		this.real_seq = real_seq;
	}
	public Integer getPaper() {
		return paper;
	}
	public void setPaper(Integer paper) {
		this.paper = paper;
	}
	
	public String getLast_modified_sqlite() {
		return last_modified_sqlite;
	}
	public void setLast_modified_sqlite(String last_modified_sqlite) {
		this.last_modified_sqlite = last_modified_sqlite;
	}	
	
	public Integer getLaw_id() {
		return law_id;
	}
	public void setLaw_id(Integer law_id) {
		this.law_id = law_id;
	}	
	
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}	
	
	private Integer id;
	private Integer type;
	private String question;
	private String a;
	private String b;
	private String c;
	private String d;
	private String answer;
	private String analysis;
	private String chapter_id;
	private String published_at;
	private Timestamp last_modified;
	private String last_modified_sqlite;
	private String point;
	private Integer real_seq;
	private Integer paper;
	private Integer law_id;

}
