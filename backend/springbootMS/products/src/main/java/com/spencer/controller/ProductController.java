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

import com.spencer.entity.Product;
import com.spencer.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService service;
	
	@GetMapping
	public ResponseEntity<List<Product>> getAll(){
		List<Product> products = service.getAll();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable("id")Long id){
		Optional<Product> product = service.getProductById(id);
		if(!product.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product) {
		Product saved = service.createProd(product);
		return new ResponseEntity<Product>(saved, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable("id") Long id ,@RequestBody Product product){
		Optional<Product> update = service.getProductById(id);
		if(!update.isPresent()) {				
			return ResponseEntity.notFound().build();
		}
		Product toUpdate = new Product();
		toUpdate.setPId(id);
		toUpdate.setPName(product.getPName());
		toUpdate.setPDesc(product.getPDesc());
		toUpdate.setImgUrl(product.getImgUrl());
		toUpdate.setPrice(product.getPrice());
		toUpdate.setQty(product.getQty());
		toUpdate.setVis(product.getVis());
		service.updateProd(toUpdate);
		return new ResponseEntity<Product>(toUpdate, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Product> delete(@PathVariable("id")Long id){
		Optional<Product> removed = service.getProductById(id);
		if(!removed.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		service.deleteProd(id);
		return ResponseEntity.ok().build();
	}
}
