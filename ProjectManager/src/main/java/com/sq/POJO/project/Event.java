package com.sq.POJO.project;

public class Event {
	
	private int id;
	private int pid;
	private String content;
	private String date;
	private String recorder;
	
	public Event(int id, int pid, String content, String date, String recorder) {
		super();
		this.id = id;
		this.pid = pid;
		this.content = content;
		this.date = date;
		this.recorder = recorder;
	}

	public Event() {
		super();
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRecorder() {
		return recorder;
	}
	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}
}
