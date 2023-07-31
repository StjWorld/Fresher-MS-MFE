package com.spencer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="product_to_category")
@Data
public class ProdToCat {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long pcId;
	
	@ManyToOne
	@JoinColumn(name="pid", nullable=false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="cid", nullable=false)
	private Category category;
		
}
