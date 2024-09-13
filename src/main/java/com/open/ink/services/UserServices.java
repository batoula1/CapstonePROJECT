package com.open.ink.services;


import com.open.ink.payloads.UserDto;
import com.open.ink.payloads.UserDtoSecure;

import java.util.List;

public interface UserServices {

	//register New User
	UserDtoSecure registerUser(UserDto user);
	
	
	//adding new user
	UserDto addUser(UserDto user);
	
	//Update user
	UserDto updateUser(UserDto user, int userId);
	
	//delete user
	void deleteUser(int userId);
	
	//get user by id
	UserDto getUserById(int userId);
	
	UserDto getUserByEmail(String email);
	
	
	//get all user
	List<UserDto> getAllUser();
	
}
