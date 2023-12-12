package com.example.thuvienCLC.model;

public class Rating {
private int id;
private String username;
private int bookid;
private int userid;
public Rating(int id, String username, int bookid, int userid, int rating, String comment) {
	super();
	this.id = id;
	this.username = username;
	this.bookid = bookid;
	this.userid = userid;
	this.rating = rating;
	this.comment = comment;
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
private int rating;
private String comment;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public int getBookid() {
	return bookid;
}
public void setBookid(int bookid) {
	this.bookid = bookid;
}
public int getRating() {
	return rating;
}
public void setRating(int rating) {
	this.rating = rating;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public Rating() {
	
	// TODO Auto-generated constructor stub
}

}





