package com.open.ink.controllers;

import java.util.List;

import com.open.ink.payloads.ApiResponse;
import com.open.ink.payloads.BlogDto;
import com.open.ink.payloads.CommentDto;
import com.open.ink.payloads.UserDto;
import com.open.ink.services.CommentServices;
import com.open.ink.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CommentControllers {

	@Autowired
	private CommentServices commentServices;
	
	@Autowired
	private UserServices userServices;
	
	@PostMapping("/blog/{blogId}/comment")
	public ResponseEntity<?> addComment(
//			@Valid @RequestBody CommentDto comment
			@RequestParam("content") String content,
			@PathVariable int blogId			
			){
		
		CommentDto comment = new CommentDto();
		comment.setContent(content);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
		System.out.println(authentication.getName());
		UserDto user = userServices.getUserByEmail(authentication.getName());
		comment.setUser(user);		
		
		CommentDto addedComment = commentServices.addComment(comment, blogId);		
		return ResponseEntity.ok(addedComment);
	}
	
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<?> deleteComment(@PathVariable int commentId){
		commentServices.deleleComment(commentId);
		ApiResponse response  = new ApiResponse("CommentDeletation", true, "Comment Deleted");
		
		return ResponseEntity.ok(response);
		
	}
	
	
	@GetMapping("/comment/blog/{blogId}")
	public ResponseEntity<?> getCommentsByBlog(@PathVariable int blogId){
		BlogDto blogDto = new BlogDto();
		blogDto.setId(blogId);
		
		List<CommentDto> commentsForBlog = commentServices.getCommentsForBlog(blogDto);
		
		return ResponseEntity.ok(commentsForBlog);
	}
	
	
}
