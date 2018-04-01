package com.website.travel.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="likes")
public class Like {
	

	@Id
	int ID;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="UserID")
	User user;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PostID")
	Post post;
	
	int status;
	

	public Like(User user, Post post, int count) {
		super();
		this.user = user;
		this.post = post;
		this.status = count;
	}
	public Like(){
		
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
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int count) {
		this.status = count;
	}
	
	
	
}
