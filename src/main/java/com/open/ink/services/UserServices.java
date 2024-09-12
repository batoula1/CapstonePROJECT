package com.open.ink.services;

import java.util.List;

import com.open.ink.payloads.UserDto;
import com.open.ink.payloads.UserDtoSecure;

public interface UserServices {

	//register New User
	UserDtoSecure registerUser(UserDto user);
	
	
	//adding new user
	UserDto addUser(UserDto user);
	

	UserDto getUserByEmail(String email);
	

}
