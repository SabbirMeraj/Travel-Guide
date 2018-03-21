package com.website.travel.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.travel.Model.Subscriber;
import com.website.travel.Model.User;
import com.website.travel.Repository.SubscriberRepository;
import com.website.travel.Repository.UserRepository;


@Service
public class SubscriberService {

	@Autowired
	SubscriberRepository sr;
	
	@Autowired
	UserRepository ur;
	
	public void save(Subscriber subscriber){
		
		sr.save(subscriber);
	}
	
	public Subscriber findByUserIdAndSubscriberId(int UserID,int SubscriberID){
		return sr.findByUserIDAndSubscriberID(UserID, SubscriberID);
	}
	
	public List<User> findByUserID(int UserID){
		List <Subscriber> s= sr.findAll(UserID);
		List<User> u = new ArrayList<User>();
		for(int i=0;i<s.size();i++){
			u.add( ur.findOne(s.get(i).getSubscriberID()));
		}
		return u;
		
		
		
		
	}
}


