package com.spencer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spencer.entity.ProdToCat;

@Repository
public interface ProdToCatRepo extends JpaRepository<ProdToCat, Long>{

}
