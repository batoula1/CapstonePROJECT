package com.open.ink.configs;


import com.open.ink.entities.User;
import com.open.ink.exceptions.ResourceNotFoundException;
import com.open.ink.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImple implements UserDetailsService{

	
	@Autowired
	private UserRepo userRepo;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("UserName", "userName",username ));

		CustomUserDetails userDetails = new CustomUserDetails(user);
		
		return userDetails;
	}

}
