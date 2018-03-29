package com.website.travel.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.travel.Model.Post;
import com.website.travel.Repository.PostRepository;
import com.website.travel.Repository.SubscriberRepository;

@Service
public class PostService {

	@Autowired
	PostRepository pr;
	
	@Autowired 
	SubscriberRepository sr;
	
	public void save(Post p){
		pr.save(p);
	}
	
	public List<Post>showPost(int UserID){
		return pr.findAll(UserID);
	}
	/*
	public List<Post> showPost(int UserID){
		List<Integer> subscriberIdList=sr.findByUserID(UserID);
		List<Post> postList=new ArrayList<Post>();
		List<Post> p;
		for(int i=0;i<subscriberIdList.size();i++){
			
			//System.out.println((subscriberIdList.get(i)));
			//System.out.println(""+pr.findByUserID(subscriberIdList.get(i)).getBody());
			p=pr.findByUserID(subscriberIdList.get(i));
			
			if(p!=null){
				for()
				postList.add(p);
			}
			
		}
		
		return postList;
	}
	
	*/
	
	public Post findByPostID(int PostID){
		return pr.findById(PostID);
	}
}
