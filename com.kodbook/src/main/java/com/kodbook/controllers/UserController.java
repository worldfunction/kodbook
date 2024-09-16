package com.kodbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kodbook.entities.User;
import com.kodbook.services.UserService;

@Controller
public class UserController {
	@Autowired
	UserService service;
	
	@PostMapping("/signUp")
	public String addUser(@ModelAttribute User user) {
		//user exits?
		String username = user.getUsername();
		String email = user.getEmail();
		boolean status = service.userExits(username,email);
		if(status == false) {
			service.addUser(user);
			
		}
		return "index";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String username, 
			@RequestParam String password) {
		boolean status = service.validateUser(username,password);
			if(status == true) {
				return "home";
			}
			else {
				return "index";
			}
		}
	@PostMapping("/updateProfile")
	public String updateProfile(@RequestParam String dob, @RequestParam String gender,
	@RequestParam String city, @RequestParam String bio,
	@RequestParam String college, @RequestParam String linkedIn,
	@RequestParam String gitHub, @RequestParam MultipartFile photo) {
	System.out.println(dob +" :" + bio);
	return "myProfile";
	}
}

