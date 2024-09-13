package com.open.ink.serviceImple;

import java.util.ArrayList;
import java.util.List;

import com.open.ink.entities.Blog;
import com.open.ink.entities.Comment;
import com.open.ink.entities.User;
import com.open.ink.exceptions.ResourceNotFoundException;
import com.open.ink.payloads.BlogDto;
import com.open.ink.payloads.CommentDto;
import com.open.ink.repositories.BlogRepo;
import com.open.ink.repositories.CommentRepo;
import com.open.ink.repositories.UserRepo;
import com.open.ink.services.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CommentServicesImple implements CommentServices {

	@Autowired
	private BlogRepo blogRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Override
	public CommentDto addComment(CommentDto commentDto, int blogId) {
		
		Blog blog = blogRepo.findById(blogId).orElseThrow(()-> new ResourceNotFoundException("Blog", "blog id", blogId));
		
		User user = userRepo.findById(commentDto.getUser().getId()).orElseThrow(()-> new ResourceNotFoundException("User", "User id", commentDto.getUser().getId()));
		
		Comment comment = this.commentDtoToComment(commentDto);
		comment.setBlog(blog);
		comment.setUser(user);		
		Comment savedComment = commentRepo.save(comment);
		
		return this.commentToCommentDto(savedComment);
	}

	
	//deleting comment
	@Override
	public void deleleComment(int commentId) {
		Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment ", "Comment id", commentId));		
		commentRepo.delete(comment);
		
	}

	
	//get comments by blog 
	public List<CommentDto>getCommentsForBlog(BlogDto blog){
		Blog blog2 = new Blog();
		blog2.setId(blog.getId());
		
		List<Comment> findByBlog = commentRepo.findByBlog(blog2);
		
		List<CommentDto> comments = new ArrayList<>();
		for(Comment com : findByBlog) {
			comments.add(commentToCommentDto(com));
		}	
		
		return comments;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private UserServicesImple us = new UserServicesImple();
	
	//Comment dto to comment
	Comment commentDtoToComment(CommentDto comDto) {		
		Comment com = new Comment();
		com.setCommentId(comDto.getCommentId());
		com.setContent(comDto.getContent());
		com.setUser(this.us.userDtoToUser(comDto.getUser()));	
		
		return com;		
	}
	
	
	//comment to CommentDto
	CommentDto commentToCommentDto(Comment com) {
		
		CommentDto comDto = new CommentDto();
		
		comDto.setCommentId(com.getCommentId());
		comDto.setContent(com.getContent());		
		comDto.setUser(this.us.userToUserDto(com.getUser()));	
		
		return comDto;		
	}
	
}
