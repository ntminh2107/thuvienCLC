package com.example.thuvienCLC.model;

public class User {
	private int id;
	private String name;
	private String email;
	private String username;
	private String password;
	private String role;
	private Boolean login;
	
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Boolean getLogin() {
		return login;
	}
	public void setLogin(Boolean login) {
		this.login = login;
	}
	public User(int id, String name, String email, String username, String password, String role, Boolean login) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
		this.login = login;
	}
	
public User() {	
	this.id = -1;
	this.name = "";
	this.email = "";
	this.username = "";
	this.password = "";
	this.role = "";
	this.login = false;
	}
	
	}
