package com.lfb.download;

/**
 * 下载项目信息
 * @author osanllyer
 *
 */
public class DownloadItem {

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	
	private int id;		 //文件id
	private String path; //文件路径，包括文件名称
	private String type; //类型
	private String desc; //描述信息
	private String pic;  //图片
	
}
