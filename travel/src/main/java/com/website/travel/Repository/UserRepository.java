package com.website.travel.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.website.travel.Model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	
	public User findByEmailAndPassword(String email,String password);
	 
	
	@Query(value = "select * from users where ID NOT IN (select SubscriberID from subscriber where UserID=?1 AND Status=1) AND ID!=?1", nativeQuery = true)
	 public List<User> findAll(int ID);
	
	
	
	/*
	 @Query(value = "select * from users where ID!= ?1", nativeQuery = true)
	 public List<User> findAll(int ID);
	 
	 */
	 
}
