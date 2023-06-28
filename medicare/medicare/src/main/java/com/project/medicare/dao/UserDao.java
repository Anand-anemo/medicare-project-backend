package com.project.medicare.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.medicare.entity.User;

@Repository
public interface UserDao extends CrudRepository<User,String> {

	public User findByUserName(String userName);

}
