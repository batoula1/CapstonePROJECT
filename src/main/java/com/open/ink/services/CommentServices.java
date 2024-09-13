package com.open.ink.services;


import java.util.List;

import com.open.ink.payloads.BlogDto;
import com.open.ink.payloads.CommentDto;

public interface CommentServices {

	//adding comment
	CommentDto addComment(CommentDto commentDto, int blogId);
	
	//deleting comment
	void deleleComment(int commentId);
	
	List<CommentDto> getCommentsForBlog(BlogDto blog);
	
	
}
