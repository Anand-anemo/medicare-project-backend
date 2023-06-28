package com.project.medicare.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.medicare.entity.Category;



@Repository
public interface CategoryDao extends CrudRepository<Category, Integer> {

}
