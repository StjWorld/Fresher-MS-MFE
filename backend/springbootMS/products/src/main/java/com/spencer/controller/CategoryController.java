package com.spencer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spencer.entity.Category;
import com.spencer.service.CategoryService;

@RestController
@RequestMapping("/category/")
public class CategoryController {

	@Autowired 
	CategoryService catServ;
	
	@GetMapping
	public ResponseEntity<List<Category>> getAll(){
		List<Category> categorys = catServ.getAll();
		return new ResponseEntity<List<Category>>(categorys, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getById(@PathVariable("id")Long id){
		Optional<Category> category = catServ.getCategoryById(id);
		if(!category.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<>(category.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Category> create(@RequestBody Category category){
		Category saved = catServ.createCat(category); 
		return new ResponseEntity<Category>(saved, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Category> update(@PathVariable("id") Long id, @RequestBody Category category){
		Optional<Category> update = catServ.getCategoryById(id);
		if(!update.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Category toUpdate = new Category();
		toUpdate.setCId(id);
		toUpdate.setCName(category.getCName());
		catServ.updateCat(toUpdate);
		return new ResponseEntity<Category>(toUpdate, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Category> delete(@PathVariable("id")Long id){
		Optional<Category> removed = catServ.getCategoryById(id);
		if(!removed.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		catServ.deleteCat(id);
		return ResponseEntity.ok().build();
	}
	
}
