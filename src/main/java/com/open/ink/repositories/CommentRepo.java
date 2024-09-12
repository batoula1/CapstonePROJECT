package com.open.ink.repositories;

import java.util.List;

import com.open.ink.entities.Blog;
import com.open.ink.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

	List<Comment> findByBlog(Blog blog);
	
}
