package com.example.demo.repositories;

import java.util.*;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Task;

@Repository
public interface TrackRepository extends CrudRepository<Task, Long>{

	List<Task> findAll();
}
