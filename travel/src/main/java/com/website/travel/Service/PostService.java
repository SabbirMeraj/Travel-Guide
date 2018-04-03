package com.website.travel.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.travel.Model.Post;
import com.website.travel.Repository.LikeRepository;
import com.website.travel.Repository.PostRepository;
import com.website.travel.Repository.SubscriberRepository;

@Service
public class PostService {

	@Autowired
	PostRepository pr;
	
	@Autowired 
	SubscriberRepository sr;
	
	@Autowired 
	LikeRepository lr;
	
	public void save(Post p){
		pr.save(p);
	}
	
	public List<Post>showPost(int UserID){
		return pr.findAll(UserID);
	}
	
	public List<Post>showHitPost(int UserID){
		List <Post> posts=new ArrayList<Post>();
		List <Post> temp=new ArrayList<Post>();
		temp=(List<Post>) pr.findAll();
		
		for(int i=0;i<10;i++){
			posts.add(temp.get(i));
		}
		return posts;
	}
	
	public List<Post>showOwnPost(int UserID){
		return pr.findByUserID(UserID);
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
	
	public List<Post> findByPlace(String Place){
		return pr.findByPlace(Place);
	}
	
	public List<Post> findByCost(int start, int end){
		return pr.findByCost(start, end);
	}
	public List<Post> findByUserID(String UserName){
		return pr.findByUserID(UserName);
	}
	
	
}
