package com.spencer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spencer.entity.Category;
import com.spencer.repo.CategoryRepo;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepo repo;

	public List<Category> getAll() {
		List<Category> categories = repo.findAll();
		
		if(categories.size() > 0) {
			return categories;
		} else {
			return new ArrayList<Category>();
		}
	}

	public Optional<Category> getCategoryById(Long id) {
		return repo.findById(id);
	}

	public Category createCat(Category category) {
		repo.save(category);
		return category;
	}

	public void updateCat(Category category) {
		repo.saveAndFlush(category);
	}

	public void deleteCat(Long id) {
		repo.deleteById(id);
	}
	
	

}
