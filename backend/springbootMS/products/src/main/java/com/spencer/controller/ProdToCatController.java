package com.spencer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spencer.entity.ProdToCat;
import com.spencer.service.ProdToCatService;

@RestController
@RequestMapping("/prods2cats")
public class ProdToCatController {
	
	@Autowired
	ProdToCatService service;
	
	@GetMapping
	public ResponseEntity<List<ProdToCat>> getAll(){
		List<ProdToCat> p2c = service.getAll();
		return new ResponseEntity<List<ProdToCat>>(p2c, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdToCat> getById(@PathVariable("id")Long id){
		Optional<ProdToCat> p2c = service.getProdToCatById(id);
		if(!p2c.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<ProdToCat>(p2c.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ProdToCat> create(@RequestBody ProdToCat prodToCat){
		ProdToCat saved = service.createProdToCat(prodToCat);
		return new ResponseEntity<ProdToCat>(saved, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProdToCat> update(@PathVariable("id")Long id, @RequestBody ProdToCat prodToCat){
		Optional<ProdToCat> update = service.getProdToCatById(id);
		if(!update.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		ProdToCat toUpdate = new ProdToCat();
		toUpdate.setCategory(prodToCat.getCategory());
		toUpdate.setProduct(prodToCat.getProduct());
		service.updateProdToCat(toUpdate);
		return new ResponseEntity<ProdToCat>(toUpdate, HttpStatus.OK);
	}
	
	@DeleteMapping("/id")
	public ResponseEntity<ProdToCat> delete(@PathVariable("id")Long id){
		Optional<ProdToCat> removed = service.getProdToCatById(id);
		if(!removed.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		service.deleteProdToCat(id);
		return ResponseEntity.ok().build();
	}
}
