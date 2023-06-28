package com.project.medicare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.medicare.dao.RoleDao;
import com.project.medicare.entity.Role;

@Service
public class RoleService {
	
	 @Autowired
	    private RoleDao roleDao;
	
	  public Role createNewRole(Role role) {
	        return roleDao.save(role);
	    }

}
