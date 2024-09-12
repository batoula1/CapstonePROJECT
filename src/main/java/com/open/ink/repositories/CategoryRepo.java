package com.open.ink.repositories;

import java.util.List;

import com.open.ink.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	List<Category> findByCategoryTitle(String title);
}
