package com.website.travel.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {

	
	

	@Id
	int id;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="UserID")
	User user;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PostID")
	Post post;

	String comment;
	
	public Comment(){
		
	}
	
	public Comment(User user, Post post, String comment) {
		super();
		this.user = user;
		this.post = post;
		this.comment = comment;
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

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
