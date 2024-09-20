package com.kodbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kodbook.entities.Post;
import com.kodbook.entities.User;
import com.kodbook.services.PostService;
import com.kodbook.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NavigationController {
	@Autowired
	UserService service;
	@Autowired
	PostService postService;
	@GetMapping("/")
	public String index() {
		return "index";
	}
	@GetMapping("/openSignUp")
	public String openSignUp() {
		return "signUp";
	}
	@GetMapping("/openCreatePost")
	public String openCreatePost(HttpSession session) {
		if(session.getAttribute("username") != null)
		return "createPost";
		else
		return "index";
	}
	@GetMapping("/goHome")
	public String login(Model model,HttpSession session)	{
			List<Post> allPosts = postService.fetchAllPosts();
			model.addAttribute("allPosts", allPosts);
			if(session.getAttribute("username") != null)
				return "home";
				else
			return "index";
			
	}
	@GetMapping("/openMyProfile")
	public String openMyProfile(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		User user = service.getUser(username);
		model.addAttribute("user", user);
		List<Post> myPosts = user.getPosts();
		model.addAttribute("myPosts", myPosts);
			if(session.getAttribute("username") != null)
			return "myProfile";
			else
		return "index";
	}
	
	@GetMapping("/openEditProfile")
	public String openEditProfile(HttpSession session) {

	if(session.getAttribute("username") != null)
	return "editProfile";
	else
	return "index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	session.invalidate();
	return "index";

	}
	
	 @GetMapping("/viewProfile/{username}")
	    public String viewUserProfile(@PathVariable String username, Model model) {
	        // Fetch user details based on the username
	        User user = service.findByUsername(username);

	        // Add the user details to the model
	        model.addAttribute("user", user);

	        // Return the profile page view
	        return "myProfile";  // This should map to the profile.html template
	    }
	
	
	
}