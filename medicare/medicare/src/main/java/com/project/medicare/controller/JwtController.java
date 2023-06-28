package com.project.medicare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.medicare.entity.JwtRequest;
import com.project.medicare.entity.JwtResponse;
import com.project.medicare.service.JwtService;

@RestController
@CrossOrigin
public class JwtController {
	
	 @Autowired
	    private JwtService jwtService;

	    @PostMapping({"/authenticate"})
	    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
	        return jwtService.createJwtToken(jwtRequest);
	    }

}
