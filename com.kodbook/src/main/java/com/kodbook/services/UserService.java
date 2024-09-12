package com.kodbook.services;

import com.kodbook.entities.User;

public interface UserService {

	boolean userExits(String username, String email);

	void addUser(User user);

	boolean validateUser(String username, String password);

}
