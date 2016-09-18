package com.lfb.libman;

/**
 * 模拟考试成绩统计表格
 * @author osanllyer
 *
 */
public class ExampaerStat {
	public int getPaperid() {
		return paperid;
	}
	public void setPaperid(int paperid) {
		this.paperid = paperid;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public int getPeople() {
		return people;
	}
	public void setPeople(int people) {
		this.people = people;
	}
	private int paperid;
	private int score;
	private int difficulty;
	private int people;
}
