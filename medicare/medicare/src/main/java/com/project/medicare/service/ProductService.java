package com.project.medicare.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.medicare.dao.ProductDao;
import com.project.medicare.entity.Category;
import com.project.medicare.entity.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	//add product
	public Product addProduct(Product product) {
		return productDao.save(product);
	}
	
	//get product
	public List<Product> getProducts(){
		return (List<Product>) productDao.findAll();
	}
	//get productById
	public Product getProductById(int productId) {
		return this.productDao.findById(productId).get();
	}

	//update product
	public  Product updateProduct (Product product) {
		return productDao.save(product);
	}
	
	//delete product
	public void deleteProduct(int productId) {
		Product product = new Product();
		product.setProductId(productId);
		this.productDao.delete(product);
		
	}
	//getting products of  category
	public List<Product> getProductsOfCategory(Category category){
		return this.productDao.findBycategory(category);
	}
	
	public List<Product> getProductDetails(boolean isSingleProductCheckout, Integer productId) {
        if(isSingleProductCheckout && productId != 0) {
            // we are going to buy a single product

            List<Product> list = new ArrayList<>();
            Product product = productDao.findById(productId).get();
            list.add(product);
            return list;
        } else {
        	return new ArrayList<>();
           
        }
    }



}
