package com.lfb.libman;

/**
 * 题库的版本和更新记录
 * @author osanllyer
 *
 */
public class VersionLog {

	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	
	
	private String version;
	private String log;
	
}
