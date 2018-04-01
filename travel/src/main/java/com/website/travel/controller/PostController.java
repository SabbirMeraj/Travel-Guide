package com.website.travel.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.website.travel.Model.Post;
import com.website.travel.Model.User;
import com.website.travel.Service.LikeService;
import com.website.travel.Service.PostService;


@Controller
public class PostController {

	@Autowired
	PostService ps;
	
	@Autowired
	LikeService ls;
	
	
	@PostMapping("/post")
	public String post(@RequestParam int UserID, @RequestParam String title, @RequestParam int cost, @RequestParam String body,HttpServletRequest request){
		User user=new User();
		user.setID(UserID);
		Post post=new Post(user,title,cost,body);
		ps.save(post);		
		request.setAttribute("posts", ps.showOwnPost((Integer) request.getSession().getAttribute("logged-user")));
		request.setAttribute("likes", ls.findByUserID((Integer) request.getSession().getAttribute("logged-user")));
		request.setAttribute("Option", "HOME");
		return "home";
	}
	
	@GetMapping("/read-more")
	public String readMore(@RequestParam int PostID,HttpServletRequest request){
		request.setAttribute("post",ps.findByPostID(PostID));
		request.setAttribute("like", ls.findByPostID(PostID));
		request.setAttribute("Page", "READMORE");
		//request.setAttribute("Option", "POST");
		return "home";
	}
	
	
}
