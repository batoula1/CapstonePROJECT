package com.open.ink.services;

import java.util.List;

import com.open.ink.payloads.CategoryDto;


public interface CategoryServices {

	CategoryDto addCategory(CategoryDto catDto);

	CategoryDto updateCategory(CategoryDto catDto, int id);
	
	void deleteCategory(int id);


	CategoryDto getCategoryById(int catId);

	List<CategoryDto> getAllCategory();

	List<CategoryDto> getCategoryByName(String name);
}
