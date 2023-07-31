package com.spencer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spencer.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

}
