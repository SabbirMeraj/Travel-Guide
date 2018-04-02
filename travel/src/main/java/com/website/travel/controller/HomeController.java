package com.website.travel.controller;

import java.util.ArrayList;

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
import com.website.travel.Service.LikeService;
import com.website.travel.Service.PostService;
import com.website.travel.Service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService us;
	
	@Autowired
	PostService ps;
	
	
	@Autowired
	LikeService ls;
	
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
			request.setAttribute("loggedUser", us.findUser(u.getID()));
			request.setAttribute("posts", ps.showPost(u.getID()));
			
			System.out.println(u.getID());
			request.setAttribute("likes", ls.findByUserID(u.getID()));
			request.setAttribute("Page", "TIMELINE");
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
		request.setAttribute("loggedUser", us.findUser((Integer) request.getSession().getAttribute("logged-user")));
		request.setAttribute("posts", ps.showPost((Integer) request.getSession().getAttribute("logged-user")));
		request.setAttribute("likes", ls.findByUserID((Integer) request.getSession().getAttribute("logged-user")));
		request.setAttribute("Page", "TIMELINE");
		request.setAttribute("Option", "HOME");
		return "home";
	}
	
	@GetMapping("/info")
	public String profile(@RequestParam int ID,HttpServletRequest request){
		request.setAttribute("loggedUser", us.findUser((Integer) request.getSession().getAttribute("logged-user")));
		request.setAttribute("Page", "TIMELINE");
		request.setAttribute("Option", "INFO");
		request.setAttribute("user", us.findUser(ID));
		return "home";
	}
	
	@GetMapping("/edit-info")
	public String editInfo(@RequestParam int ID,HttpServletRequest request){
		request.setAttribute("loggedUser", us.findUser((Integer) request.getSession().getAttribute("logged-user")));
		request.setAttribute("Page", "TIMELINE");
		request.setAttribute("Option", "EDIT-INFO");
		request.setAttribute("user", us.findUser(ID));
		return "home";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute User user, BindingResult bindingResult,HttpServletRequest request){
		us.saveUser(user);
		request.setAttribute("loggedUser", us.findUser((Integer) request.getSession().getAttribute("logged-user")));
		request.setAttribute("Page", "TIMELINE");
		request.setAttribute("Option", "INFO");
		return "home";
	}
	
	@GetMapping("/suggestion")
	public String suggestion(@RequestParam int ID, HttpServletRequest request){
		request.setAttribute("users", us.showUser(ID));
		request.setAttribute("posts", ps.showHitPost(ID));
		request.setAttribute("Page", "SUGGESTION");
		request.setAttribute("Option","SUGGESTION");
		return "home";
	}
	
	@GetMapping("/profile")
	public String getProfilePage(HttpServletRequest request){
		request.setAttribute("loggedUser", us.findUser((Integer) request.getSession().getAttribute("logged-user")));
		request.setAttribute("posts", ps.showOwnPost((Integer) request.getSession().getAttribute("logged-user")));
		request.setAttribute("likes", ls.findByUserID((Integer) request.getSession().getAttribute("logged-user")));
		request.setAttribute("Page", "TIMELINE");
		request.setAttribute("Option", "HOME");
		return "home";
	}
	
	@PostMapping("/search")
	public String search(@RequestParam String option, @RequestParam String searchText,HttpServletRequest request){
		
		if(option.equals("Place")){
			request.setAttribute("posts", ps.findByPlace(searchText));
			
		}
		else if(option.equals("Cost")){
			int cost,start,end;
			cost=Integer.parseInt(searchText);
			start=cost-500;
			end=cost+500;
			request.setAttribute("posts", ps.findByCost(start, end));
			
		}
		else if(option.equals("People")){
			request.setAttribute("posts", ps.findByUserID(searchText));
		}
		
		
		request.setAttribute("loggedUser", us.findUser((Integer) request.getSession().getAttribute("logged-user")));
		request.setAttribute("Page", "SEARCHRESULT");
		return "home";
	}
}