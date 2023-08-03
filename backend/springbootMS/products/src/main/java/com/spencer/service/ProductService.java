package com.spencer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spencer.entity.Product;
import com.spencer.repo.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	ProductRepo repo;
	
	@Autowired
	RabbitSender send;

	public List<Product> getAll() {
		List<Product> products = repo.findAll();
		
		if(products.size() > 0) {
			return products;
		} else {
			return new ArrayList<Product>();
		}
	}

	public Optional<Product> getProductById(Long id) {
		return repo.findById(id);
	}

	public Product createProd(Product product) {	
		// remove send.sendMessage for normal operation
		// send.sendMessage(Product) is for testing purposes 
		send.sendMessage(repo.save(product));
		return product;
	}

	public void updateProd(Product product) {
		repo.saveAndFlush(product);
	}

	public void deleteProd(Long id) {
		repo.deleteById(id);
	}

	
}
