package com.project.medicare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.medicare.configuration.JwtRequestFilter;
import com.project.medicare.dao.OrderDetailsDao;
import com.project.medicare.dao.ProductDao;
import com.project.medicare.dao.UserDao;
import com.project.medicare.entity.OrderDetails;
import com.project.medicare.entity.OrderInput;
import com.project.medicare.entity.OrderProductQuantity;
import com.project.medicare.entity.Product;
import com.project.medicare.entity.User;

@Service
public class OrderdetailService {
	
	 private static final String ORDER_PLACED = "Placed";
	@Autowired
	private OrderDetailsDao orderdetailsDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private UserDao userDao;
	
	

	public void placeOrder(OrderInput orderInput) {
		List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();

        for (OrderProductQuantity o: productQuantityList) {
        	Product product = productDao.findById(o.getProductId()).get();
        	String currentUser = JwtRequestFilter.CURRENT_USER;
        	User user = userDao.findById(currentUser).get();
        	  OrderDetails orderDetail = new OrderDetails(
                      orderInput.getFullName(),
                      orderInput.getFullAddress(),
                      orderInput.getContactNumber(),
                      orderInput.getAlternateContactNumber(),
                        ORDER_PLACED,product.getProductDiscountedPrice() * o.getQuantity(),
                        product,user
                        );
        	  orderdetailsDao.save(orderDetail);
        }
	}

		
		
	}


