package com.project.medicare.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.medicare.entity.Cart;
import com.project.medicare.entity.User;

@Repository
public interface CartDao extends CrudRepository<Cart,Integer> {
	
	 public List<Cart> findByUser(User user);

}
