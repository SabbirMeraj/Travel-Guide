package com.website.travel.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.travel.Model.Comment;
import com.website.travel.Repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	CommentRepository cr;
	
	public List<Comment> findByPostId(int PostID){
		return cr.findByPostId(PostID);
	}
	
	public void save(Comment c){
		cr.save(c);
	}
}
