package com.devsuperior.dsmovie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.dto.MovieGenreDTO;
import com.devsuperior.dsmovie.entities.Movie;
import com.devsuperior.dsmovie.repositories.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;
	
	@Transactional(readOnly = true)
	public Page<MovieDTO> findAll(Pageable pageable) {
		Page<Movie> result = repository.findAll(pageable);
		Page<MovieDTO> page = result.map(x -> new MovieDTO(x));
		return page;
	}
	
	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Movie result = repository.findById(id).get();
		MovieDTO dto = new MovieDTO(result);
		return dto;
	}
	
	// ADEQUADO PRA V1
	@Transactional(readOnly = true)
	public Page<MovieGenreDTO> findAllMovieGenre(Pageable pageable) {
		Page<Movie> result = repository.findAll(pageable);
		Page<MovieGenreDTO> page = result.map(x -> new MovieGenreDTO(x));
		return page;
	}
	
	@Transactional(readOnly = true)
	public MovieGenreDTO findByIdMovieGenre(Long id) {
		Movie result = repository.findById(id).get();
		MovieGenreDTO dto = new MovieGenreDTO(result);
		return dto;
	}
}
