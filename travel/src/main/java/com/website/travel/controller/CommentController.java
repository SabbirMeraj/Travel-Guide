package com.website.travel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.website.travel.Model.Comment;
import com.website.travel.Model.Post;
import com.website.travel.Model.User;
import com.website.travel.Service.CommentService;
import com.website.travel.Service.LikeService;
import com.website.travel.Service.PostService;
import com.website.travel.Service.UserService;

@Controller
public class CommentController {

	
	@Autowired
	CommentService cs;
	
	@Autowired
	PostService ps;
	
	@Autowired
	LikeService ls;
	
	
	@Autowired
	UserService us;
	
	@GetMapping("/comment")
	public String seeComment(@RequestParam int PostID,HttpServletRequest request){
		request.setAttribute("post",ps.findByPostID(PostID));
		request.setAttribute("like", ls.findByPostID(PostID));
		request.setAttribute("comments", cs.findByPostId(PostID));
		request.setAttribute("Option", "COMMENTS");
		return "home";
	}
	
	@PostMapping("/add-comment")
	public String post(@RequestParam int UserID, @RequestParam int PostID, @RequestParam String comment,HttpServletRequest request){
		User user=new User();
		user.setID(UserID);
		Post post=new Post();
		post.setId(PostID);
		Comment c=new Comment(user,post,comment);
		cs.save(c);		
		/*
		request.setAttribute("post",ps.findByPostID(PostID));
		request.setAttribute("like", ls.findByPostID(PostID));
		request.setAttribute("comments", cs.findByPostId(PostID));
		request.setAttribute("Page", "READMORE");
		//request.setAttribute("Option", "POST");
		 
		*/
		request.setAttribute("loggedUser", us.findUser((Integer) request.getSession().getAttribute("logged-user")));
		request.setAttribute("posts", ps.showPost((Integer) request.getSession().getAttribute("logged-user")));
		request.setAttribute("likes", ls.findByUserID((Integer) request.getSession().getAttribute("logged-user")));
		request.setAttribute("comments", cs.findByPostId(PostID));
		request.setAttribute("Page", "TIMELINE");
		request.setAttribute("Option", "HOME");
		return "home";
		
	}
}
