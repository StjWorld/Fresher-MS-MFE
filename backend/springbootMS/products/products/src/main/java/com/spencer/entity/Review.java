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
@Table(name="reviews")
@Data
public class Review {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rid")
	private Long rid;
	
	@Column(name="rating", nullable=false)
	private Float rating;
	
	@Column(name="rtite", nullable=false)
	private String title;
	
	@Column(name="rdesc", columnDefinition="varchar(255) default ''")
	private String desc = "";	
	
	@ManyToOne
	@JoinColumn(name="pid", nullable=false)
	private Product pid;
}
