package com.project.medicare.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	private String productName;
	private String brand;
	private Long quantity;
	private boolean active =false;
	
	@Column(length = 2000)
	private String productDescription;
	private double productDiscountedPrice;
	private double productActualPrice;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Category category;
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "product_images",
            joinColumns = {
                    @JoinColumn(name = "product_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "image_id")
            }
    )
	private Set<ImageModel> productImages;

	public Set<ImageModel> getProductImages() {
		return productImages;
	}

	public void setProductImages(Set<ImageModel> productImages) {
		this.productImages = productImages;
	}

	public Product() {
		
	}

	public Product(int productId, String productName, String brand, Long quantity, boolean active,
			String productDescription, double productDiscountedPrice, double productActualPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.brand = brand;
		this.quantity = quantity;
		this.active = active;
		this.productDescription = productDescription;
		this.productDiscountedPrice = productDiscountedPrice;
		this.productActualPrice = productActualPrice;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public double getProductDiscountedPrice() {
		return productDiscountedPrice;
	}

	public void setProductDiscountedPrice(double productDiscountedPrice) {
		this.productDiscountedPrice = productDiscountedPrice;
	}

	public double getProductActualPrice() {
		return productActualPrice;
	}

	public void setProductActualPrice(double productActualPrice) {
		this.productActualPrice = productActualPrice;
	}
	

	
}
