package com.website.travel.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;

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
import org.springframework.web.multipart.MultipartFile;

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
	
	@GetMapping("/login-page")
	public String loginPage(HttpServletRequest request){
		request.setAttribute("Option", "LOGIN-PAGE");
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
	
	
	@PostMapping("/login-page")
	public String loggedin(@ModelAttribute User user, BindingResult bindingResult,HttpServletRequest request,HttpSession session) throws UnsupportedEncodingException{
	
		
		User u=us.findByEmailAndPassword(user.getEmail(), user.getPassword());
		if(u!=null){
			  
			
			
			session.setAttribute("logged-user", u.getID());
			request.setAttribute("loggedUser", us.findUser(u.getID()));
			
			request.setAttribute("posts", ps.showPost(u.getID()));
			
			System.out.println(u.getID());
			request.setAttribute("likes", ls.findByUserID(u.getID()));
			
			if(u.getImage()!=null){
				byte[] decoded=Base64.getEncoder().encode(u.getImage());
				String base64Encoded = new String(decoded, "UTF-8");
				session.setAttribute("profilePic", base64Encoded);
			}
		
			
			request.setAttribute("Page", "TIMELINE");
			request.setAttribute("Option", "HOME");
			return "home";
		}
		
		else{
			request.setAttribute("Option", "LOGIN-PAGE");
			request.setAttribute("Error", "Invalid email or password");
			return "index";
		}
	}
	
	@GetMapping("/login")
	public String getLoginPage(HttpServletRequest request){
		if(request.getSession().getAttribute("logged-user")!=null){
			request.setAttribute("loggedUser", us.findUser((Integer) request.getSession().getAttribute("logged-user")));
			request.setAttribute("posts", ps.showPost((Integer) request.getSession().getAttribute("logged-user")));
			request.setAttribute("likes", ls.findByUserID((Integer) request.getSession().getAttribute("logged-user")));
			request.setAttribute("Page", "TIMELINE");
			request.setAttribute("Option", "HOME");
			return "home";
		}
		else{
			request.setAttribute("Option", "LOGIN");
			return "index";
		}
		
	}
	
	@GetMapping("/info")
	public String profile(@RequestParam int ID,HttpServletRequest request){
		if(request.getSession().getAttribute("logged-user")!=null){
			request.setAttribute("loggedUser", us.findUser((Integer) request.getSession().getAttribute("logged-user")));
			request.setAttribute("Page", "TIMELINE");
			request.setAttribute("Option", "INFO");
			request.setAttribute("user", us.findUser(ID));
			return "home";
		}
		else{
			request.setAttribute("Option", "LOGIN");
			return "index";
		}
	}
	
	@GetMapping("/edit-info")
	public String editInfo(@RequestParam int ID,HttpServletRequest request){
		if(request.getSession().getAttribute("logged-user")!=null){
			request.setAttribute("loggedUser", us.findUser((Integer) request.getSession().getAttribute("logged-user")));
			request.setAttribute("Page", "TIMELINE");
			request.setAttribute("Option", "EDIT-INFO");
			request.setAttribute("user", us.findUser(ID));
			return "home";
		}
		else{
			request.setAttribute("Option", "LOGIN");
			return "index";
		}
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
		if(request.getSession().getAttribute("logged-user")!=null){
			request.setAttribute("users", us.showUser(ID));
			request.setAttribute("posts", ps.showHitPost(ID));
			request.setAttribute("Page", "SUGGESTION");
			request.setAttribute("Option","SUGGESTION");
			return "home";
		}
		else{
			request.setAttribute("Option", "LOGIN");
			return "index";
		}
	}
	
	@GetMapping("/profile")
	public String getProfilePage(HttpServletRequest request){
		
		if(request.getSession().getAttribute("logged-user")!=null){
			request.setAttribute("loggedUser", us.findUser((Integer) request.getSession().getAttribute("logged-user")));
			request.setAttribute("posts", ps.showOwnPost((Integer) request.getSession().getAttribute("logged-user")));
			request.setAttribute("likes", ls.findByUserID((Integer) request.getSession().getAttribute("logged-user")));
			request.setAttribute("Page", "TIMELINE");
			request.setAttribute("Option", "HOME");
			return "home";
		}
		else{
			request.setAttribute("Option", "LOGIN");
			return "index";
		}
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
	
	
	@GetMapping("/uploadPic")
	public String upload(@RequestParam int ID,HttpServletRequest request){
		if(request.getSession().getAttribute("logged-user")!=null){
			request.setAttribute("loggedUser", us.findUser((Integer) request.getSession().getAttribute("logged-user")));
			request.setAttribute("Page", "TIMELINE");
			request.setAttribute("Option", "UPLOAD");
			
			request.setAttribute("user", us.findUser(ID));
			return "home";
		}
		else{
			request.setAttribute("Option", "LOGIN");
			return "index";
		}
	}
	
	
	@PostMapping("/upload")
	public String uploadProfilePic(@ModelAttribute User user, BindingResult bindingResult, @RequestParam("file") MultipartFile file,   HttpServletRequest request,HttpSession session) throws IOException{
		byte [] bytes=file.getBytes();
		user.setImage(bytes);
		us.saveUser(user);
		request.setAttribute("loggedUser", us.findUser((Integer) request.getSession().getAttribute("logged-user")));
		request.setAttribute("Page", "TIMELINE");
		request.setAttribute("Option", "INFO");
		byte[] decoded=Base64.getEncoder().encode(user.getImage());
		String base64Encoded = new String(decoded, "UTF-8");
		session.setAttribute("profilePic", base64Encoded);
		return "home";
	}
	
	
	@GetMapping("/logout")
	public String logouT(HttpServletRequest request,HttpSession session){
		session.invalidate();
		request.setAttribute("Option", "LOGIN");
		System.out.println(request.getSession().getAttribute("logged-user"));
		return "index";
	}
}