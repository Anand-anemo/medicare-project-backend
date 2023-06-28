package com.project.medicare.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String categoryName;
	
	@OneToMany(mappedBy="category" , fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Product>product=new LinkedHashSet<>();
	
	public Category(int id, String categoryName, Set<Product> product) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.product = product;
	}
	
	
	
	public Category() {
		
	}
	
	public Set<Product> getProduct() {
		return product;
	}
	public void setProduct(Set<Product> product) {
		this.product = product;
	}
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getCategoryName() {
		return categoryName;
	}



	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	


}
