package com.website.travel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.website.travel.Model.Subscriber;
import com.website.travel.Model.User;
import com.website.travel.Service.SubscriberService;

@Controller
public class SubscriberController {
	
	@Autowired
	SubscriberService ss;
	
	@PostMapping("/subscribe")
	public String subscribe(@RequestParam int UserID, @RequestParam int SubscriberID,HttpServletRequest request){
		Subscriber s=ss.findByUserIdAndSubscriberId(UserID, SubscriberID);
		if(s!=null){
			s.setStatus(1);
			ss.save(s);
		}
		
		else{
			User user=new User();
			user.setID(UserID);
			Subscriber subscriber=new Subscriber(user, SubscriberID, 1);
			ss.save(subscriber);
		}
		
		request.setAttribute("Option","SUGGESTION");
		return "home";
	}
	
	@PostMapping("/unsubscribe")
	public String unsubscribe(@RequestParam int UserID, @RequestParam int SubscriberID,HttpServletRequest request){
		Subscriber s=ss.findByUserIdAndSubscriberId(UserID, SubscriberID);
		/*User user=new User();
		user.setID(UserID);
		Subscriber subscriber=new Subscriber(user, SubscriberID, 0);
		ss.save(subscriber);*/
		
		s.setStatus(0);
		ss.save(s);
		request.setAttribute("Option","SUGGESTION");
		return "home";
	}
	
	@GetMapping("/subscription")
	public String subscription(@RequestParam int ID, HttpServletRequest request){
		request.setAttribute("Option", "SUBSCRIPTION");
		request.setAttribute("subscribers", ss.findByUserID(ID));
		return "home";
	}
}
