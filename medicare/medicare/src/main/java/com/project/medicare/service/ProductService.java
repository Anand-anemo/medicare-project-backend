package com.project.medicare.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.medicare.configuration.JwtRequestFilter;
import com.project.medicare.dao.CartDao;
import com.project.medicare.dao.ProductDao;
import com.project.medicare.dao.UserDao;
import com.project.medicare.entity.Cart;
import com.project.medicare.entity.Category;
import com.project.medicare.entity.Product;
import com.project.medicare.entity.User;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private CartDao cartDao;
	
	//add product
	public Product addProduct(Product product) {
		return productDao.save(product);
	}
	
	//get products
	public List<Product> getProducts(int pageNumber , String searchKey){
		Pageable pageable = PageRequest.of(pageNumber,12);
		
		 if(searchKey.equals("")) {
			 return (List<Product>) productDao.findAll(pageable);
	            
	        } else {
	            return (List<Product>)
	            		productDao.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(
	                    searchKey, searchKey, pageable
	            );
	        }
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
        	
        	//going to checkout whole cart
        	 String username = JwtRequestFilter.CURRENT_USER;
             User user = userDao.findById(username).get();
             List<Cart> carts = cartDao.findByUser(user);

             return carts.stream().map(x -> x.getProduct()).collect(Collectors.toList());
           
        }
    }



}
