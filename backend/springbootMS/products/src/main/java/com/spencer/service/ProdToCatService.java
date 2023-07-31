package com.spencer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spencer.entity.ProdToCat;
import com.spencer.repo.ProdToCatRepo;

@Service
public class ProdToCatService {
	
	@Autowired
	ProdToCatRepo repo;
	
	public List<ProdToCat> getAll() {
		List<ProdToCat> p2c = repo.findAll();
		
		if(p2c.size() > 0) {
			return p2c;
		} else {
			return new ArrayList<ProdToCat>();
		}
	}

	public Optional<ProdToCat> getProdToCatById(Long id) {
		return repo.findById(id);
	}

	public ProdToCat createProdToCat(ProdToCat prodToCat) {
		repo.save(prodToCat);
		return prodToCat;
	}

	public void updateProdToCat(ProdToCat prodToCat) {
		repo.saveAndFlush(prodToCat);
	}

	public void deleteProdToCat(Long id) {
		repo.deleteById(id);
	}

}
