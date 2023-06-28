package com.project.medicare.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.medicare.entity.Category;
import com.project.medicare.entity.Product;

@Repository
public interface ProductDao extends CrudRepository<Product,Integer> {
	
	List<Product>findBycategory(Category category);
   

	
}
