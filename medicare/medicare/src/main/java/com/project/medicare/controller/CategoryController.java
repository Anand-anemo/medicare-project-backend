package com.project.medicare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.medicare.entity.Category;
import com.project.medicare.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	//ADD CATEGORY
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody Category category){
		Category category1=this.categoryService.addCategory(category);
		return ResponseEntity.ok(category1);
		
	}
	//GET CATEGORY BY ID
	@GetMapping("/{categoryId}")
	public Category getCategoryById(@PathVariable("categoryId") int categoryId) {
		return this.categoryService.getCategoryById(categoryId);
	}
	
	//GETTING ALL CATEGORIES
	@GetMapping("/")
	public ResponseEntity<?> getCategories(){
		return ResponseEntity.ok(this.categoryService.getCategories());
	}
	//UPDATE CATEGORIES
	@PutMapping("/")
	public Category updateCategory(@RequestBody Category category) {
		return this.categoryService.updateCategory(category);
		
	}
	//DELETING CATEGORY
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable("categoryId") int categoryId) {
		 this.categoryService.deleteCategory(categoryId);
		
	}
		
	
	
	

}
