package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.RegistrationService;

@RestController
@CrossOrigin("http://localhost:3000")
public class RegistrationController {

	@Autowired
	private RegistrationService reSer;
	@PostMapping("/registeruser")
	public User registerUser(@RequestBody User user) throws Exception {
		String tempEmilId=user.getEmailId();
		if(tempEmilId!=null && !"".equals(tempEmilId)) {
		User userObjd=	reSer.fetchUserByEmailId(tempEmilId);
		if(userObjd!=null) {
			throw new Exception("user already exists");
		}
		}
		User userObjd=null;
	userObjd=	reSer.saveUser(user);
	return userObjd;
	}
	@PostMapping("/login")
	public User loginUser(@RequestBody User user) throws Exception {
		String tempEmailid=user.getEmailId();
		String tempPassw=user.getPassword();
		User userObj=null;
		if(tempEmailid!=null && tempPassw!=null) {
			userObj=	reSer.fetchUserByEmailIdAndPassword(tempEmailid, tempPassw);
		}
		if(userObj == null) {
			throw new Exception("bad credentials");
		}
		return userObj;
		
	}
	
}
