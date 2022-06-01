package com.example.demo.repositories;

import java.util.*;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.UserProject;

@Repository
public interface UserProjectRepository extends CrudRepository<UserProject, Long> {
	
	List<UserProject> findAll();
}
