package com.sq.POJO.project;

public class Project {
	
	private int id;
	private String content;
	private String filler;
	private String iner;
	private int state;
	private int priority;
	private String name;
	private String chance;
	private String excute;
	private String checker;
	
	public Project() {
		super();
	}
	public Project(int id, String iner, String content, String filler, int state, int priority, String name, String chance,
			String excute, String checker) {
		super();
		this.id = id;
		this.iner = iner;
		this.content = content;
		this.filler = filler;
		this.state = state;
		this.priority = priority;
		this.name = name;
		this.chance = chance;
		this.excute = excute;
		this.checker = checker;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFiller() {
		return filler;
	}
	public void setFiller(String filler) {
		this.filler = filler;
	}
	public String getIner() {
		return iner;
	}
	public void setIner(String iner) {
		this.iner = iner;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChance() {
		return chance;
	}
	public void setChance(String chance) {
		this.chance = chance;
	}
	public String getExcute() {
		return excute;
	}
	public void setExcute(String excute) {
		this.excute = excute;
	}
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
}
