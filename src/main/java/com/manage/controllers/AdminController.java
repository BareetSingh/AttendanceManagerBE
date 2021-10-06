package com.manage.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manage.model.Admin;
import com.manage.model.User;
import com.manage.service.AdminService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
		
	@PostMapping("/add-user")
	public boolean addUser(@RequestBody User user) {
		return adminService.addUser(user);
	}
	
	@PostMapping("/login")
	public boolean login(@RequestBody Admin adminUser) {
		return adminService.login(adminUser);
	}
	
	@PostMapping("/set-presence")
	public boolean setPresence(@RequestBody Map<String,String> map) {
		return adminService.setPresence(Long.parseLong(map.get("id").toString()),map.get("present"));
	}
	
	@GetMapping("get-current-presence/{admin}")
	public List<Map<String,Object>> setCurrentPresence(){
		return adminService.getPresentDayAttendance();
	}
	
	@GetMapping("/get-users/{admin}")
	public List<User> getUsers(@PathVariable("admin") String adminName){
		return adminService.getUsers(adminName);
	}
	
	
	
}
