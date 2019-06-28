package com.sq.POJO.plan;

public class Recode {
	
	private int id;
	private int pid;//所属计划id
	private String content;
	
	public Recode() {
		super();
	}
	public Recode(int id, int pid, String content) {
		super();
		this.id = id;
		this.pid = pid;
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
