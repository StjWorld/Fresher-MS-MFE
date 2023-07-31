package com.spencer.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="products")
@Data
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pid")
	private Long pId;
	
	@Column(name="vis", nullable=false)
	private Boolean vis;
	
	@Column(name="pname", unique=true, nullable=false)
	private String pName;
	
	@Column(name="pdesc", nullable=false)
	private String pDesc;
	
	@Column(name="price", nullable=false)
	private Float price;
	
	@Column(name="imgurl", columnDefinition="varchar(255)default 'imgUrl.jpg'")
	private String imgUrl = "imgUrl.jpg";
	
	@Column(name="qty", nullable=false)
	private Long qty;
	
	@JsonIgnore
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ProdToCat> cId;
	
	@JsonIgnore
	@OneToMany(mappedBy="pid",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Review> reviews;
}
