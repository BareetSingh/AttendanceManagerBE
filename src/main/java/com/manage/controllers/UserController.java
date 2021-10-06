package com.manage.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manage.model.Attendance;
import com.manage.model.User;
import com.manage.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;	
	
	@PostMapping("/login")
	public Boolean login(@RequestBody User user) {
		return userService.login(user);
	}
	
	@GetMapping("/get-attendances/{email}")
	public List<Attendance> get(@PathVariable("email") String email){
		return userService.getAttendances(email);
	}
}
