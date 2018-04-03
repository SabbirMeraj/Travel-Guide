package com.website.travel.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="post")
public class Post {

	
	@Id
	int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="UserID")
	User user;
	String title;
	int cost;
	String body;
	String place;
	
	
	@OneToMany(mappedBy="post",cascade= CascadeType.ALL,fetch=FetchType.EAGER)
	List<Like> like;
	
	@OneToMany(mappedBy="post",cascade= CascadeType.ALL,fetch=FetchType.EAGER)
	Set <Comment> comment;
	

	public Post(){
		
	}
	
	public Post(User user, String title, int cost, String body, String place) {
		super();
		this.user = user;
		this.title = title;
		this.cost = cost;
		this.body = body;
		this.place=place;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<Like> getLike() {
		return like;
	}

	public void setLike(List<Like> like) {
		this.like = like;
	}

	public Set<Comment> getComment() {
		return comment;
	}

	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}

	


}
