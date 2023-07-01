package com.project.medicare.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.medicare.entity.Category;
import com.project.medicare.entity.ImageModel;
import com.project.medicare.entity.Product;
import com.project.medicare.service.ProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	//ADD PRODUCT
	@PostMapping(value={"/"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Product> addProduct(@RequestPart Product product , 
			@RequestPart("imageFile") MultipartFile[] file) {
		 try {
	            Set<ImageModel> images = uploadImage(file);
	            product.setProductImages(images);
	            return ResponseEntity.ok(this.productService.addProduct(product));
	           
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            return null;
	        }

		
	}
	//FOR IMAGE STORING
	 public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
	        Set<ImageModel> imageModels = new HashSet<>();

	        for (MultipartFile file: multipartFiles) {
	            ImageModel imageModel = new ImageModel(
	                    file.getOriginalFilename(),
	                    file.getContentType(),
	                    file.getBytes()
	            );
	            imageModels.add(imageModel);
	        }

	        return imageModels;
	    }
	 
	//GET PRODUCT
	@GetMapping("/")
	public List<Product> getProduct(@RequestParam(defaultValue="0") int pageNumber , 
			 @RequestParam(defaultValue="")String searchKey){
		return this.productService.getProducts(pageNumber , searchKey);
	}
	
	//GET PRODUCT BY ID
	@GetMapping("/{productId}")
	public Product getProductById(@PathVariable("productId")int productId) {
		
		return this.productService.getProductById(productId);
		
	}
	//UPDATE PRODUCT
	@PutMapping("/")
	public Product updateProduct(@RequestBody Product product) {
		return this.productService.updateProduct(product);
	}
	//DELETE PRODUCT
	@DeleteMapping("/{productId}")
	public void deleteProduct(@PathVariable("productId")int productId) {
		this.productService.deleteProduct(productId);
	}
	
	//getting product by category
	@GetMapping("/category/{id}")
	public List<Product> getProductsOfCategory(@PathVariable("id")int id){
		
		Category category = new Category();
		category.setId(id);
		return this.productService.getProductsOfCategory(category);
		
	}
	//getting product details for buying and also checking for single product checkout
	@GetMapping({"/getProductDetails/{isSingleProductCheckout}/{productId}"})
    public List<Product> getProductDetails(@PathVariable(name = "isSingleProductCheckout" ) boolean isSingleProductCheckout,
                                           @PathVariable(name = "productId")  Integer productId) {
        return productService.getProductDetails(isSingleProductCheckout, productId);
    }
	}
	
	


