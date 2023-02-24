package com.relevel.backend.amazonpay.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.relevel.backend.amazonpay.services.CustomUserDetails;

import com.relevel.backend.amazonpay.beans.User;
import com.relevel.backend.amazonpay.repository.UserRepository;

@Service
public class UserDetailServiceImpl  implements UserDetailsService{

	
	@Autowired
	private UserRepository userRepository;

	
	public User register(User user) {
		userRepository.save(user);
		return user;
	}
	
	public Optional<User> getUser(Long userID) {
		return  userRepository.findById(userID);
	}
	
	public List<User> getAllUser(){
		return userRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			
			//User user = userRepository.getUserByUsername(username);
			User user = userRepository.findByUsername(username);
			CustomUserDetails userDetails = new CustomUserDetails();
			
			if(user != null) {
				userDetails.setUser(user);
				
			}
			else {
				throw new UsernameNotFoundException("Could not found user !!");
			}
			
			
			
			return userDetails;
	}

}
