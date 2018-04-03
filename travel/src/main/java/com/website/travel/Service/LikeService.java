package com.website.travel.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.travel.Model.Like;
import com.website.travel.Repository.LikeRepository;

@Service
public class LikeService {

	@Autowired
	LikeRepository lr;
	
	public void save(Like like){
		lr.save(like);
	}
	
	public Like findByUserIDAndPostID(int UserID,int PostID){
		return lr.findByUserIDAndPostId(UserID, PostID);
	}
	
	public Integer findByPostID(int PostID){
		return lr.findByPostId(PostID);
	}
	public List<Like> findByUserID(int UserID){
		return lr.findByUserID(UserID);
	}
}
