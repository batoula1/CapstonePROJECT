package com.open.ink.repositories;


import com.open.ink.entities.Blog;
import com.open.ink.entities.Category;
import com.open.ink.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepo extends JpaRepository<Blog, Integer>{
	
	List<Blog> findByUser(User user);
	
	List<Blog> findByCategory(Category category);

	Page<Blog> findByUser(User user, Pageable page);
	
	Page<Blog> findByCategory(Category category,  Pageable page);
	
	Page<Blog> findByBlogTitleContaining(String search_query, Pageable page);

}
