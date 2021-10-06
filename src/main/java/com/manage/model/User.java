package com.manage.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String username;
		private String password;
		
		@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	    @JoinColumn(name = "adminId", referencedColumnName = "id")
		@JsonBackReference
		@NotNull
		private Admin admin;

		@OneToMany(mappedBy = "user", cascade =CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)  
		@JsonManagedReference
		private List<Attendance> attendances;

}
