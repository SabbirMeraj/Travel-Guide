package com.website.travel.Model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="users")
public class User {

	
	public User(){
		
	}
	
	public User(String username, int age, String email, String password) {
		super();
		this.username = username;
		this.age = age;
		this.email = email;
		this.password = password;
	}
	@Id
	int ID;
	String username;
	int age;
	String email;
	String password;
	String bio;
	String interest;

	@OneToMany(mappedBy="user",cascade= CascadeType.ALL,fetch=FetchType.EAGER)
	Set <Subscriber> subscriber;
	
	
	@OneToMany(mappedBy="user",cascade= CascadeType.ALL,fetch=FetchType.EAGER)
	Set <Post> post;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public Set<Subscriber> getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Set<Subscriber> subscriber) {
		this.subscriber = subscriber;
	}

	public Set<Post> getPost() {
		return post;
	}

	public void setPost(Set<Post> post) {
		this.post = post;
	}
}
