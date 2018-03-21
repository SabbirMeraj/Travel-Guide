package com.website.travel.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="post")
public class Post {

	
	@Id
	int ID;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="UserID")
	User user;
	String title;
	int cost;
	String body;
	
	public Post(){
		
	}
	
	public Post(User user, String title, int cost, String body) {
		super();
		this.user = user;
		this.title = title;
		this.cost = cost;
		this.body = body;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
