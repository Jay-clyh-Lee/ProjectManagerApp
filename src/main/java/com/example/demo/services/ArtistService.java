package com.example.demo.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Artist;
import com.example.demo.repositories.ArtistRepository;

@Service
public class ArtistService {
	
	@Autowired
	private ArtistRepository repo;
	
	// get
	public List<Artist> getAll(){
		return this.repo.findAll();
	}
	
	public List<Artist> allExcluding(List<Long> excludedArtistIds) {
		return this.repo.findByIdNotIn(excludedArtistIds);
	}
	
	// get 1
	public Artist retrieve(Long itemId) {
		return this.repo.findById(itemId).get();
	}
	
	// create
	public Artist create(Artist item) {
		return this.repo.save(item);
	}
	
	// update
	public Artist update(Artist itemId) {
		return this.repo.save(itemId);
	}
	
	// delete
	public void delete(Long itemId) {
		this.repo.deleteById(itemId);
	}
}
