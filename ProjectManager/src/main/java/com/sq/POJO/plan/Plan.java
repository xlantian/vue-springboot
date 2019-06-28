package com.sq.POJO.plan;

public class Plan {
	
	private int id;
	private String name;
	private String filler;
	private String content;
	private int type;
	private String checker;
	private String checkadvice;
	private int checkdecision;
	private String excute;
	
	public Plan() {
		super();
	}
	public Plan(int id, String name, String filler, String content, int type, String checker, String checkadvice,
			int checkdecision, String excute) {
		super();
		this.id = id;
		this.name = name;
		this.filler = filler;
		this.content = content;
		this.type = type;
		this.checker = checker;
		this.checkadvice = checkadvice;
		this.checkdecision = checkdecision;
		this.excute = excute;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFiller() {
		return filler;
	}
	public void setFiller(String filler) {
		this.filler = filler;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
	public String getCheckadvice() {
		return checkadvice;
	}
	public void setCheckadvice(String checkadvice) {
		this.checkadvice = checkadvice;
	}
	public int getCheckdecision() {
		return checkdecision;
	}
	public void setCheckdecision(int checkdecision) {
		this.checkdecision = checkdecision;
	}
	public String getExcute() {
		return excute;
	}
	public void setExcute(String excute) {
		this.excute = excute;
	}
}
