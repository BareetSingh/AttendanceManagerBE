package com.manage.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String username;
		private String password;
		
		@OneToMany(mappedBy = "admin", cascade =CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)  
		@JsonManagedReference
		private List<User> users;

}
