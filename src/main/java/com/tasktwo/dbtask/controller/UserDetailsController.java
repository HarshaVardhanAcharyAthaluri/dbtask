package com.tasktwo.dbtask.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tasktwo.dbtask.dto.UserDeatailInfo;
import com.tasktwo.dbtask.model.UserDetails;
import com.tasktwo.dbtask.service.UserDetailsService;

@RestController
public class UserDetailsController {
	private static final Logger log = LoggerFactory.getLogger(UserDetailsController.class);
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@GetMapping("/user/{id}")
	public ResponseEntity<UserDetails> findUserById(@PathVariable Long id) {
		log.info("{}","id "+id);
		UserDetails user = userDetailsService.getuserDetailById(id);
		return new ResponseEntity<UserDetails>(user,HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDeatailInfo>> users(){
		log.info("{}","all users");
		List<UserDeatailInfo> userlist = userDetailsService.getUserDetails();
		return new ResponseEntity<List<UserDeatailInfo>>(userlist,HttpStatus.OK);
		
	}
}
