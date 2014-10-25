package cn.edu.xjtu.manage.business;

import java.util.Date;

public class Sample {
	int ID;
	String name;
	String path;
	String type;
	long filesize;
	String md5;
	String analysis;
	Date firsttime;
	Date lasttime;
	String remark;
	
	
	public Sample(int iD, String name, String path, String type, long filesize,
			String md5, String analysis, Date firsttime, Date lasttime,
			String remark) {
		super();
		ID = iD;
		this.name = name;
		this.path = path;
		this.type = type;
		this.filesize = filesize;
		this.md5 = md5;
		this.analysis = analysis;
		this.firsttime = firsttime;
		this.lasttime = lasttime;
		this.remark = remark;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	public Date getFirsttime() {
		return firsttime;
	}
	public void setFirsttime(Date firsttime) {
		this.firsttime = firsttime;
	}
	public Date getLasttime() {
		return lasttime;
	}
	public void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
