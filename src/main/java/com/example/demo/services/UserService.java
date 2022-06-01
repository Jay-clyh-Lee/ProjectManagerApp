package com.example.demo.services;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	// get
	public List<User> getAll(){
		return this.repo.findAll();
	}
	
	// get 1
	public User retrieve(Long userId) {
		return this.repo.findById(userId).get();
	}
	
	public User retrieveInSessionUser(HttpSession session) {
		
		if ( session.getAttribute("user")  == null ) return null;
		
		return this.retrieve((Long) session.getAttribute("user"));
	}
	
	// authenticate
	public User authenticate(User user) {
		
		Optional<User> foundUser = this.repo.findByEmail(user.getEmail());
		
		if ( foundUser.isEmpty() || 
				! BCrypt.checkpw(user.getPassword(), foundUser.get().getPassword()) 
				) return null;
		
		return foundUser.get();
	}
	
	// create
	public User create(User user) {
		
		String salt = BCrypt.gensalt();
		String hashedPassword = BCrypt.hashpw(user.getPassword(), salt);
		
		user.setPassword(hashedPassword);
		
		return this.repo.save(user);
	}
	
	// update
	public User update(User userId) {
		return this.repo.save(userId);
	}
	
	// delete
	public void delete(Long userId) {
		this.repo.deleteById(userId);
	}
}
