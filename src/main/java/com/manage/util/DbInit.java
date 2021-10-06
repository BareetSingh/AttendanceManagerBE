package com.manage.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manage.model.Admin;
import com.manage.repo.AdminRepository;

@Component
public class DbInit {

    @Autowired
    private AdminRepository adminRepository;

    @PostConstruct
    private void postConstruct() {
        if(adminRepository.findAll().isEmpty()) {
        	Admin admin = new Admin(null, "arpit@gmail.com","Arpit@123a",null);
        	adminRepository.save(admin);
        }
    }
}