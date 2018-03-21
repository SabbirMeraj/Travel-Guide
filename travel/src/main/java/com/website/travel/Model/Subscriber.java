
package com.website.travel.Model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="subscriber")
public class Subscriber {
	
	@Id
	int ID;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="UserID")
	User user;
	int subscriberID;
	int status;
	
	
	public Subscriber(){
		
	}
	public Subscriber(User user, int subscriberID, int status) {
		super();
		this.user = user;
		this.subscriberID = subscriberID;
		this.status = status;
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

	public int getSubscriberID() {
		return subscriberID;
	}

	public void setSubscriberID(int subscriberID) {
		this.subscriberID = subscriberID;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	

}