package com.project.medicare.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.medicare.dao.RoleDao;
import com.project.medicare.dao.UserDao;
import com.project.medicare.entity.Role;
import com.project.medicare.entity.User;

@Service
public class UserService {
	
	  @Autowired
	    private UserDao userDao;
	 

	    @Autowired
	    private RoleDao roleDao;
	    
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    
	    public User registerNewUser(User user) throws Exception {
	    	
	    	User local = this.userDao.findByUserName(user.getUserName());
	    	if(local!=null) {
	    		System.out.println("user with this name is already exists");
	    		throw new Exception("User already exits");
	    	}
	    	 Role role = roleDao.findById("User").get();
	         Set<Role> userRoles = new HashSet<>();
	         userRoles.add(role);
	         user.setRole(userRoles);
	         user.setUserPassword(getEncodedPassword(user.getUserPassword()));
	           return userDao.save(user);
	    }
	  
	  public void initRoleAndUser() {

	        Role adminRole = new Role();
	        adminRole.setRoleName("Admin");
	        adminRole.setRoleDescription("Admin role");
	        roleDao.save(adminRole);

	        Role userRole = new Role();
	        userRole.setRoleName("User");
	        userRole.setRoleDescription("Default role for newly created record");
	        roleDao.save(userRole);

	        User adminUser = new User();
	        adminUser.setUserName("admin123");
	        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
	        adminUser.setUserFirstName("admin");
	        adminUser.setUserLastName("admin");
	        adminUser.setEmail("ani@gmail.com");
	        Set<Role> adminRoles = new HashSet<>();
	        adminRoles.add(adminRole);
	        adminUser.setRole(adminRoles);
	        userDao.save(adminUser);

	        User user = new User();
	        user.setUserName("raj123");
	        user.setUserPassword(getEncodedPassword("raj@123"));
	        user.setUserFirstName("raj");
	        user.setUserLastName("sharma");
	        user.setEmail("user@gmail.com");
	        Set<Role> userRoles = new HashSet<>();
	        userRoles.add(userRole);
	        user.setRole(userRoles);
	        userDao.save(user);
	    }
	  
	  public String getEncodedPassword(String password) {
	        return passwordEncoder.encode(password);
	    }
	 
	

}
