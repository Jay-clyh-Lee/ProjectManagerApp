package com.example.demo.repositories;

import java.util.*;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Artist;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {
	
	List<Artist> findAll();

	List<Artist> findByIdNotIn(List<Long> excludedArtists);
}
