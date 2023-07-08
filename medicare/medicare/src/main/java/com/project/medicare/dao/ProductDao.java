package com.project.medicare.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.medicare.entity.Category;
import com.project.medicare.entity.Product;

@Repository
public interface ProductDao extends CrudRepository<Product,Integer> {
	
	List<Product>findBycategory(Category category);
	public List<Product> findAll(Pageable pageable);
	public List<Product> findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(
            String key1, String key2, Pageable pageable
    );
	List<Product>findByActive(Boolean b);
	List<Product>findByCategoryAndActive(Category c,Boolean b);
   

	
}
