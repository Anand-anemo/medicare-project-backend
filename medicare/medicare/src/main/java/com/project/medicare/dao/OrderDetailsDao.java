package com.project.medicare.dao;

import org.springframework.data.repository.CrudRepository;

import com.project.medicare.entity.OrderDetails;

public interface OrderDetailsDao extends CrudRepository<OrderDetails,Integer> {

}
