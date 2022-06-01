package com.example.demo.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.UserProject;
import com.example.demo.repositories.UserProjectRepository;

@Service
public class UserProjectService {
	
	@Autowired
	private UserProjectRepository repo;
	
	// get
	public List<UserProject> getAll(){
		return this.repo.findAll();
	}
	
	// get 1
	public UserProject retrieve(Long itemId) {
		return this.repo.findById(itemId).get();
	}
	
	// create
	public UserProject create(UserProject item) {
		return this.repo.save(item);
	}
	
	// update
	public UserProject update(UserProject itemId) {
		return this.repo.save(itemId);
	}
	
	// delete
	public void delete(Long itemId) {
		this.repo.deleteById(itemId);
	}
}
