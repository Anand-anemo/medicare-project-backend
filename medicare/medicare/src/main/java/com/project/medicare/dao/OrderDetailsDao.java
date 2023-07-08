package com.project.medicare.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.medicare.entity.OrderDetails;
import com.project.medicare.entity.User;

public interface OrderDetailsDao extends CrudRepository<OrderDetails,Integer> {
	
	public List<OrderDetails> findByUser(User user);


    public List<OrderDetails> findByOrderStatus(String status);

}
