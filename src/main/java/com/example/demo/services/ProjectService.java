package com.example.demo.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Project;
import com.example.demo.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository repo;
	
	// get
	public List<Project> getAll(){
		return this.repo.findAll();
	}
	
	// get 1
	public Project retrieve(Long itemId) {
		return this.repo.findById(itemId).get();
	}
	
	// create
	public Project create(Project item) {
		return this.repo.save(item);
	}
	
	// update
	public Project update(Project itemId) {
		return this.repo.save(itemId);
	}
	
	// delete
	public void delete(Long itemId) {
		this.repo.deleteById(itemId);
	}
}
