package com.manage.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manage.model.Admin;
import com.manage.model.Attendance;
import com.manage.model.User;
import com.manage.repo.AdminRepository;
import com.manage.repo.UserRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private UserRepository userRepository;

	public boolean login(Admin adminUser) {
		if (!adminRepository.findByUsernameAndPassword(adminUser.getUsername(), adminUser.getPassword())
				.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public boolean addUser(User user) {
		String name="arpit@gmail.com";
		Admin admin=adminRepository.findByUsername(name);

		user.setAdmin(admin);
		admin.getUsers().add(user);
		
		adminRepository.save(admin);
		return true;
	}

	public boolean setPresence(Long id, String presence) {
		Attendance attendance=new Attendance();
		System.out.println("id:"+id);
		System.out.println("presence:"+presence);
		
		Optional<User> tempUser=userRepository.findById(id);
		if(tempUser.isEmpty())
			return false;
		boolean flag=true;
		int length=tempUser.get().getAttendances().size();

		if(length>0) {
			Date lastDate=tempUser.get().getAttendances().get(length-1).getDate();
			Date todayDate=new Date(System.currentTimeMillis());
			System.out.println(lastDate);
			System.out.println(todayDate);

			if(lastDate.toString().equals(todayDate.toString())) {
				System.out.println(lastDate);
				System.out.println(todayDate);

				flag=false;
				return false;
			}
		}
		if(!tempUser.isEmpty() && flag==true) {
			attendance.setUser(tempUser.get());
			attendance.setDate(new Date(System.currentTimeMillis()));
			attendance.setAttendance(presence);
			tempUser.get().getAttendances().add(attendance);
			userRepository.save(tempUser.get());
			return true;
		}
		return false;
	}

	public List<User> getUsers(String name) {
//		String name="arpit@gmail.com";
		Admin admin=adminRepository.findByUsername(name);
		return admin.getUsers();
	}
	
	public List<Map<String,Object>> getPresentDayAttendance(){
		List<Map<String,Object>> result=new ArrayList<>();
		List<User> users=userRepository.findAll();
		for (User user : users) {
			Map <String,Object> map=new HashMap<>();
			map.put("id",user.getId());
			map.put("username",user.getUsername());
			map.put("date",new Date(System.currentTimeMillis()));
			List<Attendance> a=user.getAttendances();
			
			if(a.size()>0)
			map.put("attendance",(a.get(a.size()-1)).getAttendance());
			
			result.add(map);
		}
		
		return result;
		
	}
	
}
