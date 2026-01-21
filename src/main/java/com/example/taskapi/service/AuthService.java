package com.example.taskapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.taskapi.model.User;
import com.example.taskapi.repository.UserRepository;
import com.example.taskapi.security.JwtUtil;

@Service
public class AuthService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;
	
	public User register(String email, String password) {
		if(userRepository.findByEmail(email).isPresent()) {
			throw new RuntimeException("Email già registrata!");
		}
		User user= new User();
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		return userRepository.save(user);
	}
	
	public String login(String email,String password) {
		User user= userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("Credenziali non valide"));
		
		if(!passwordEncoder.matches(password, user.getPassword())) {
			throw new RuntimeException("Credenziali non valide");
		}
		return jwtUtil.generateToken(email);
	}
}	
