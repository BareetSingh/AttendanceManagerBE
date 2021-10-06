package com.manage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manage.model.Attendance;
import com.manage.model.User;
import com.manage.repo.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public Boolean login(User user) {
		Optional<User> temp=userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (!temp.isEmpty()) {
			 return true;
		}
		return false;
	}

	public List<Attendance> getAttendances(String email){
		Optional<User> temp=userRepository.findByUsername(email);
		if(!temp.isEmpty()) {
			return temp.get().getAttendances();
		}
		else
			return null;
	}
}
