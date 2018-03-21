package com.website.travel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.website.travel.Model.User;
import com.website.travel.Service.PostService;
import com.website.travel.Service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService us;
	
	@Autowired
	PostService ps;
	
	@GetMapping("/")
	public String login(HttpServletRequest request){
		request.setAttribute("Option", "LOGIN");
		return "index";
	}
	
	@RequestMapping("/registration")
	public String registration(HttpServletRequest request){
		request.setAttribute("Option", "REGISTRATION");
		return "index";
	}
	
	
	@PostMapping("/register")
	public String register(@ModelAttribute User user, BindingResult bindingResult,HttpServletRequest request){
		us.saveUser(user);
		request.setAttribute("Option", "LOGIN");
		return "index";
	}
	
	
	@PostMapping("/login")
	public String loggedin(@ModelAttribute User user, BindingResult bindingResult,HttpServletRequest request,HttpSession session){
	
		
		User u=us.findByEmailAndPassword(user.getEmail(), user.getPassword());
		if(u!=null){
			
			session.setAttribute("logged-user", u.getID());
			request.setAttribute("posts", ps.showPost(u.getID()));
			request.setAttribute("Option", "HOME");
			return "home";
		}
		
		else{
			request.setAttribute("Option", "LOGIN");
			request.setAttribute("Error", "Invalid email or password");
			return "index";
		}
	}
	
	@GetMapping("/login")
	public String getLoginPage(HttpServletRequest request){
		request.setAttribute("posts", ps.showPost((Integer) request.getSession().getAttribute("logged-user")));
		request.setAttribute("Option", "HOME");
		return "home";
	}
	
	@GetMapping("/info")
	public String profile(@RequestParam int ID,HttpServletRequest request){
		request.setAttribute("Option", "INFO");
		request.setAttribute("user", us.findUser(ID));
		return "home";
	}
	
	@GetMapping("/edit-info")
	public String editInfo(@RequestParam int ID,HttpServletRequest request){
		request.setAttribute("Option", "EDIT-INFO");
		request.setAttribute("user", us.findUser(ID));
		return "home";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute User user, BindingResult bindingResult,HttpServletRequest request){
		us.saveUser(user);
		request.setAttribute("Option", "INFO");
		return "home";
	}
	
	@GetMapping("/suggestion")
	public String suggestion(@RequestParam int ID, HttpServletRequest request){
		request.setAttribute("users", us.showUser(ID));
		request.setAttribute("Option","SUGGESTION");
		return "home";
	}
	
	
}