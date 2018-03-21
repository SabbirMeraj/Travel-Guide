package com.website.travel.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.website.travel.Model.Subscriber;
import com.website.travel.Model.User;


@Repository
public interface SubscriberRepository extends CrudRepository<Subscriber, Integer> {

	
	public Subscriber findByUserIDAndSubscriberID(int UserID,int SubscriberID);
/*
	@Query(value = "select username from subscriber,users where subscriber.UserID=?1 AND  subscriber.SubscriberID=users.ID", nativeQuery = true)
	public List<User> findAll(int UserID);
	
	*/
	
	
	@Query(value = "select * from subscriber where subscriber.UserID=?1 AND Status=1 ", nativeQuery = true)
	public List<Subscriber> findAll(int UserID);
	
	@Query(value = "select SubscriberID from subscriber where subscriber.UserID=?1 AND Status=1 ", nativeQuery = true)
	public List<Integer>findByUserID(int UserID);
}

