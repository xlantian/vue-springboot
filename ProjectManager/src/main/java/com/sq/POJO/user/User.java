package com.sq.POJO.user;

public class User {
	
	private String name;
	private String number;
	private String pwd;
	private String npwd;
	private int id;
	
	public User() {
		super();
	}
	public User(String name, String number, String pwd,String npwd, int id) {
		super();
		this.name = name;
		this.number = number;
		this.pwd = pwd;
		this.npwd = npwd;
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getNpwd() {
		return npwd;
	}
	public void setNpwd(String npwd) {
		this.npwd = npwd;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
