package com.example.thuvienCLC.model;

import java.sql.Date;

public class Book {
	private int id;
	private String title;
	private String author;
	private String category;
	private String description;
	private Date publishdate;
	private int numberofpage;
	private String cover;
	private int sold;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getPublishdate() {
		return publishdate;
	}
	public void setPublishdate(Date publishdate) {
		this.publishdate = publishdate;
	}
	public int getNumberofpage() {
		return numberofpage;
	}
	public void setNumberofpage(int numberofpage) {
		this.numberofpage = numberofpage;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	public Book() {

		// TODO Auto-generated constructor stub
	}
	public Book(int id, String title, String author, String category, String description, Date publishdate,
			int numberofpage, String cover, int sold) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
		this.description = description;
		this.publishdate = publishdate;
		this.numberofpage = numberofpage;
		this.cover = cover;
		this.sold = sold;
	}
	
	
	}
	
	
	
	


