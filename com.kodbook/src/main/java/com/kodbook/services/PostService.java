package com.kodbook.services;

import java.util.List;

import com.kodbook.entities.Post;

public interface PostService {

	void createPost(Post post);
	
	List<Post> getAllPosts();

	static List<Post> fetchAllPosts() {
		
		return null;
	}

	Post getPost(Long id);

	void updatePost(Post post);
}