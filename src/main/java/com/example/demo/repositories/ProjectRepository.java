package com.example.demo.repositories;

import java.util.*;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
	
	List<Project> findAll();
}
