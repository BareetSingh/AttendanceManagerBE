package com.manage.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsernameAndPassword(String username, String password);

	Optional<User> findByUsername(String username);
	
}
