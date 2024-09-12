package com.open.ink.repositories;


import java.util.Optional;

import com.open.ink.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);
	
	//User findByEmail(String email);
}
