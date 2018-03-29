package com.website.travel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.website.travel.Model.Like;
import com.website.travel.Model.Post;
import com.website.travel.Model.Subscriber;
import com.website.travel.Model.User;
import com.website.travel.Service.LikeService;
import com.website.travel.Service.PostService;


@Controller
public class LikeController {

	
	@Autowired
	LikeService ls;
	
	@Autowired
	PostService ps;
	
	@PostMapping("/like")
	public String like(@RequestParam int UserID, @RequestParam int PostID, HttpServletRequest request){
		
		
		
		Like l=ls.findByUserIDAndPostID(UserID, PostID);
		if(l!=null){
			l.setStatus(1);
			ls.save(l);
		}
		
		else{
			User user=new User();
			user.setID(UserID);
			Post post=new Post();
			post.setId(PostID);
			Like like=new Like(user,post,1);
			ls.save(like);
		}
		request.setAttribute("posts", ps.showPost((Integer) request.getSession().getAttribute("logged-user")));
		request.setAttribute("Option", "HOME");
		return "home";
	}
	
	
	@PostMapping("/unlike")
	public String unlike(@RequestParam int UserID, @RequestParam int PostID, HttpServletRequest request){
		
		
		
		Like l=ls.findByUserIDAndPostID(UserID, PostID);
		if(l!=null){
			l.setStatus(0);
			ls.save(l);
		}
		
		else{
			User user=new User();
			user.setID(UserID);
			Post post=new Post();
			post.setId(PostID);
			Like like=new Like(user,post,0);
			ls.save(like);
		}
		request.setAttribute("posts", ps.showPost((Integer) request.getSession().getAttribute("logged-user")));
		request.setAttribute("Option", "HOME");
		return "home";
	}
}
