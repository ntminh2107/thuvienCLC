package com.example.thuvienCLC.model;

public class Order {
private int id;
private int userid;
private int bookid;
private String bookname;
private int quantity;
private int status;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public int getBookid() {
	return bookid;
}
public void setBookid(int bookid) {
	this.bookid = bookid;
}
public String getBookname() {
	return bookname;
}
public void setBookname(String bookname) {
	this.bookname = bookname;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public Order() {

	// TODO Auto-generated constructor stub
}
public Order(int id, int userid, int bookid, String bookname, int quantity, int status) {
	super();
	this.id = id;
	this.userid = userid;
	this.bookid = bookid;
	this.bookname = bookname;
	this.quantity = quantity;
	this.status = status;
}


}
