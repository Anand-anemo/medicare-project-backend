package com.project.medicare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.medicare.entity.OrderInput;
import com.project.medicare.service.OrderdetailService;



@RestController
public class OrderdetailController {
	
	@Autowired
	private OrderdetailService orderDetailService;
	
	@PostMapping({"/placeOrder/{isSingleProductCheckout}"})
	public void placeOrder(@PathVariable (name= "isSingleProductCheckout")boolean isSingleProductCheckout ,@RequestBody OrderInput orderInput) {
		orderDetailService.placeOrder(orderInput , isSingleProductCheckout);
	}

}
