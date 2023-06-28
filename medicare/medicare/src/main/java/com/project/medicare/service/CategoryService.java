package com.project.medicare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.medicare.dao.CategoryDao;
import com.project.medicare.entity.Category;
@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	
	//adding categories
	public Category addCategory(Category category) {
		
		return categoryDao.save(category);
	}
	
	//getting all categories
	public List<Category> getCategories(){
		return (List<Category>) categoryDao.findAll();
	}
	
	//getting category by id
	public Category getCategoryById(int categoryId) {
		return this.categoryDao.findById(categoryId).get();
		
	}
	
	//updating category
	public Category updateCategory(Category category) {
		return categoryDao.save(category);
	}
	
	public void deleteCategory(int categoryId) {
		Category category = new Category();
		category.setId(categoryId);
	    categoryDao.delete(category);
	}

}
