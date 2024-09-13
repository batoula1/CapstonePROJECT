package com.open.ink.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.open.ink.payloads.*;
import com.open.ink.serviceImple.FileServicesImple;
import com.open.ink.services.BlogServices;
import com.open.ink.services.CategoryServices;
import com.open.ink.services.FileService;
import com.open.ink.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@RestController
@RequestMapping("/api")
public class BlogControllers {

	@Autowired
	private BlogServices blogServices;

	@Autowired
	private UserServices userServices;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private CategoryServices categoryServices;



	// add Blog
	@PostMapping("/blog")
	public ResponseEntity<?> addBlog(@RequestParam("blogData") String blogData,
			@RequestParam(name = "blogImage", required = false) MultipartFile image) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDto user = userServices.getUserByEmail(authentication.getName());
		int userId = user.getId();

		BlogDto blog = null;
		try {
			blog = mapper.readValue(blogData, BlogDto.class);

			List<CategoryDto> categoryByName = categoryServices.getCategoryByName(blog.getCategory().getCategoryTitle());

			CategoryDto category = new CategoryDto();
			if (categoryByName != null && categoryByName.size() > 0) {
				category = categoryByName.get(0);
			} else {
				category = categoryServices.addCategory(blog.getCategory());

			}
			blog.setCategoryId(category.getCategoryId());
			blog.setCategory(category);
			
		} catch (JsonProcessingException e) {
			ApiResponse resp = new ApiResponse("InvalidDataConversion", false, "Invalid Blog data");
			return ResponseEntity.badRequest().body(resp);
		}

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<BlogDto>> violations = validator.validate(blog);

		// checking if there is any error
		if (!violations.isEmpty()) {

			ValidationResponse validResp = new ValidationResponse();
			Map<String, String> resp = validResp.getBlogErrors(violations);
			return ResponseEntity.badRequest().body(resp);
		}

		FileService file = new FileServicesImple();

		if (image != null) {
			Map<String, String> imageViolation = file.blogImageValidation(image);
			if (!imageViolation.isEmpty()) {
				return ResponseEntity.badRequest().body(imageViolation);
			}

			String blogImagePath = file.uploadBlogImage(image);
			if (blogImagePath == null) {
				return ResponseEntity.badRequest().body(new HashMap<>().put("FileError", "File Upload Fail"));
			}

			blog.setBlogImage(blogImagePath);
		}

		BlogDto addedBlog = blogServices.addBlog(blog, userId);

		return ResponseEntity.ok(addedBlog);
	}

	// update blog
	@PutMapping("/blog/{blogId}")
	public ResponseEntity<?> updateBlog(@RequestBody BlogDto blogDto, @PathVariable int blogId) {
		BlogDto updateBlog = blogServices.updateBlog(blogDto, blogId);

		if (updateBlog == null) {
			Map<String, String> resp = new HashMap<>();
			resp.put("message", "You are not the Author of this blog!!");
			return new ResponseEntity<>(resp, HttpStatus.FORBIDDEN);
		}

		return ResponseEntity.ok(updateBlog);
	}

	// get blog by id
	@GetMapping("/blog/{blogId}")
	public ResponseEntity<?> getBlogById(@PathVariable int blogId) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BlogDto blog = blogServices.getBlogById(blogId);
		return ResponseEntity.ok(blog);
	}

	// get all blog
	@GetMapping("/blogs")
	public ResponseEntity<?> getAllBlogs(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = "desc", required = false) String sortDirection) {

		if (pageSize == 0) {
			pageSize = 5;
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PageResponse allBlogs = blogServices.getAllBlogs(pageNumber, pageSize, sortBy, sortDirection);
		return ResponseEntity.ok(allBlogs);
	}

	// get blogs by user
	@GetMapping("/user/{userId}/blogs")
	public ResponseEntity<?> getBlogsByUser(@PathVariable int userId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = "desc", required = false) String sortDirection)
			throws InterruptedException {

//		Thread.sleep(2000);

		PageResponse allBlogs = blogServices.getAllBlogsByUser(userId, pageNumber, pageSize, sortBy, sortDirection);
		return ResponseEntity.ok(allBlogs);

	}

	// get blogs by category
	@GetMapping("/category/{catId}/blogs")
	public ResponseEntity<?> getBlogsByCategory(@PathVariable int catId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = "desc", required = false) String sortDirection) {
		PageResponse allBlogs = blogServices.getAllBlogsByCategory(catId, pageNumber, pageSize, sortBy, sortDirection);
		return ResponseEntity.ok(allBlogs);
	}

	// search query
	@GetMapping("/blogs/results")
	public ResponseEntity<?> searchForBlog(
			@RequestParam(value = "search_query", defaultValue = "", required = false) String search_query,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = "desc", required = false) String sortDirection) {

		PageResponse blogs = blogServices.searchBlogs(search_query, pageNumber, pageSize, sortBy, sortDirection);

		return ResponseEntity.ok(blogs);
	}

}
