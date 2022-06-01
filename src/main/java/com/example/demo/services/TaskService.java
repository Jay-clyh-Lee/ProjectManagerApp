package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Project;
import com.example.demo.models.Task;
import com.example.demo.repositories.TrackRepository;

@Service
public class TaskService {
	
	@Autowired
	private TrackRepository repo;
	
	@Autowired
	private ProjectService service;
	
	// get
	public List<Task> getAll(){
		return this.repo.findAll();
	}
	
	// get 1
	public Task retrieve(Long itemId) {
		return this.repo.findById(itemId).get();
	}
	
	// create
	public Task create(Task item, Long project_id) {
		
		Project project = this.service.retrieve(project_id);
		
		if ( project == null ) return null;
		
		item.setProject(project);
		
		return this.repo.save(item);
	}
	
	// update
	public Task update(Task itemId) {
		return this.repo.save(itemId);
	}
	
	// delete
	public void delete(Long itemId) {
		this.repo.deleteById(itemId);
	}
}
