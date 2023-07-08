package com.project.medicare.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.medicare.configuration.JwtRequestFilter;
import com.project.medicare.dao.CartDao;
import com.project.medicare.dao.OrderDetailsDao;
import com.project.medicare.dao.ProductDao;
import com.project.medicare.dao.UserDao;
import com.project.medicare.entity.Cart;
import com.project.medicare.entity.OrderDetails;
import com.project.medicare.entity.OrderInput;
import com.project.medicare.entity.OrderProductQuantity;
import com.project.medicare.entity.Product;
import com.project.medicare.entity.TransactionDetails;
import com.project.medicare.entity.User;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class OrderdetailService {
	
	 private static final String ORDER_PLACED = "Placed";
	 private static final String KEY = "rzp_test_qM5FT2pfokcchZ";
	 private static final String KEY_SECRET = "4AoFvTBfPr5PbJWVFFllDmm6";
	 private static final String CURRENCY = "INR";

	@Autowired
	private OrderDetailsDao orderdetailsDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CartDao cartDao;
	
	

	public void placeOrder(OrderInput orderInput , boolean isSingleProductCheckout) {
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
                        product,user,orderInput.getTransactionId()
                        );
        	  

              // empty the cart.
              if(!isSingleProductCheckout) {
                  List<Cart> carts = cartDao.findByUser(user);
                  carts.stream().forEach(x -> cartDao.deleteById(x.getCartId()));
              }
        	  orderdetailsDao.save(orderDetail);
        }
	}
	
//	 public List<OrderDetails> getAllOrderDetails(String status) {
//	        List<OrderDetails> orderDetails = new ArrayList<>();
//
//	        if(status.equals("All")) {
//	            orderdetailsDao.findAll().forEach(
//	                    x -> orderDetails.add(x)
//	            );
//	        } else {
//	            orderdetailsDao.findByOrderStatus(status).forEach(
//	                    x -> orderDetails.add(x)
//	            );
//	        }
//
//
//	         return orderDetails;
//	    }
	 
	 public List<OrderDetails> getOrderDetails() {
	        String currentUser = JwtRequestFilter.CURRENT_USER;
	        User user = userDao.findById(currentUser).get();

	        return orderdetailsDao.findByUser(user);
	    }
	 
	 public  TransactionDetails createTransaction(Double amount) {
	        try {

	            JSONObject jsonObject = new JSONObject();
	            jsonObject.put("amount", (amount * 100) );
	            jsonObject.put("currency", CURRENCY);

	            RazorpayClient razorpayClient = new RazorpayClient(KEY, KEY_SECRET);

	            Order order = razorpayClient.orders.create(jsonObject);

	            TransactionDetails transactionDetails =  prepareTransactionDetails(order);
	            return transactionDetails;
	           
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        return null;
	    }
	 
	 private TransactionDetails prepareTransactionDetails(Order order) {
	        String orderId = order.get("id");
	        String currency = order.get("currency");
	        Integer amount = order.get("amount");

	        TransactionDetails transactionDetails = new TransactionDetails(orderId, currency, amount, KEY);
	        return transactionDetails;
	    }
	 
	

		
		
	}


