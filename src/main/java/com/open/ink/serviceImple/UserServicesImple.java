package com.open.ink.serviceImple;

import java.util.ArrayList;
import java.util.List;

import com.open.ink.configs.AppConstants;
import com.open.ink.entities.Role;
import com.open.ink.entities.User;
import com.open.ink.payloads.UserDto;
import com.open.ink.payloads.UserDtoSecure;
import com.open.ink.repositories.RoleRepo;
import com.open.ink.repositories.UserRepo;
import com.open.ink.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserServicesImple implements UserServices {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder passEncoder;
	
	
	
	//REGISTER USER
	@Override
	public UserDtoSecure registerUser(UserDto userDto) {
		User user = this.userDtoToUser(userDto);
		
		Role role = roleRepo.findById(AppConstants.ROLE_NORMAL).get();
		
		user.getRoles().add(role);		
		user.setPassword(passEncoder.encode(user.getPassword()));
		
		User addedUser= userRepo.save(user);
		
		return userToUserDtoSecure(addedUser);
	}
	
	
	
	//adding new User
	@Override
	public UserDto addUser(UserDto userDto) {
		User user = this.userDtoToUser(userDto);		
		User addedUser = userRepo.save(user);					
		return userToUserDto(addedUser);
	}

	

	
	
	//get user by email
	public UserDto getUserByEmail(String email) {
		User findByEmail = null;
		
		try {
			findByEmail = userRepo.findByEmail(email).get();			
		}catch (Exception e) {
			if(findByEmail == null) {
				UserDto userDto = null;
				return userDto;
			}
		}	
		
		return this.userToUserDto(findByEmail);
	}




	//user to userDto
	public UserDto userToUserDto(User user) {

		UserDto userDto = new UserDto();

		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		userDto.setImage(user.getImage());
		userDto.setRoles(user.getRoles());

		return userDto;
	}


	//user DTo to User
	public User userDtoToUser(UserDto userDto) {
		User user = new User();

		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		user.setImage(userDto.getImage());
		user.setRoles(userDto.getRoles());

		return user;
	}


	//user to user dto secure
	public UserDtoSecure userToUserDtoSecure(User user) {
		UserDtoSecure userDtoSecure = new UserDtoSecure();

		userDtoSecure.setId(user.getId());
		userDtoSecure.setName(user.getName());
		userDtoSecure.setAbout(user.getAbout());
		userDtoSecure.setImage(user.getImage());
		userDtoSecure.setRoles(user.getRoles());

		return userDtoSecure;
	}

	//user to user dto secure
	public User userDtoSecureToUser(UserDtoSecure userDtoSecure) {

		User user = new User();

		user.setId(userDtoSecure.getId());
		user.setName(userDtoSecure.getName());
		user.setAbout(userDtoSecure.getAbout());
		user.setImage(userDtoSecure.getImage());
		user.setRoles(userDtoSecure.getRoles());

		return user;
	}



}
