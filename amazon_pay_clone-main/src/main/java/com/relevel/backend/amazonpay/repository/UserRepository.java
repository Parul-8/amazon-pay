package com.relevel.backend.amazonpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.relevel.backend.amazonpay.beans.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("Select u from User u where u.username = :username")
	public User getUserByUsername( @Param("username") String username);
	
	User findByUsername(String username);
}
