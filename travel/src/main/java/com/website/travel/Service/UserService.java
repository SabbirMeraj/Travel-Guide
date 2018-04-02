package com.website.travel.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.travel.Model.User;
import com.website.travel.Repository.UserRepository;

@Service
public class UserService {

	
	@Autowired
	UserRepository ur;
	
	public void saveUser(User user){
		ur.save(user);
	
	}
	
	public User findUser(int ID){
		return ur.findOne(ID);
	}
	
	public User findByEmailAndPassword(String email,String password){
		return ur.findByEmailAndPassword(email, password);
	}
	
	public List<User> showUser(int ID){
		List <User> users=new ArrayList<User>();
		for(User u:ur.findAll(ID)){
			users.add(u);
			
		}
		
		return users;
	}

	
	/*
	public void saveUser(String fullName,String userName, int age, String email, String password){
		User u=new User(fullName,userName,age,email,password);
		ur.save(u);
	}*/
}
