package com.devsuperior.dsmovie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.dto.MovieGenreDTO;
import com.devsuperior.dsmovie.services.MovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

	@Autowired
	private MovieService service;
	
	@GetMapping
	public Page<MovieDTO> findAll(Pageable pageable) {
		return service.findAll(pageable);
	}
	
	@GetMapping(value = "/{id}")
	public MovieDTO findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	// VERSIONAMENTO 
	
	@GetMapping(produces = "application/vdn.devsuperior.dsmovie-v1+json") // SE NA REQUISIÇÃO TIVER ESSE CABEÇALHO, EXECUTAR ESSE ENDPOINT
	public Page<MovieGenreDTO> findAllV1(Pageable pageable) {
		return service.findAllMovieGenre(pageable);
	}
	
	@GetMapping(value = "/{id}", produces = "application/vdn.devsuperior.dsmovie-v1+json") // COM ISSO, NO POSTMAN O CAMINHO PODE SER O MESMO DO MÉTODO DE CIMA. /MOVIES/ID. MAS NO HEADERS, A VARIAVEL Accept IRÁ RECEBER ESTE CAMINHO DO PARÂMETRO <-
	public MovieGenreDTO findByIdV1(@PathVariable Long id) {
		return service.findByIdMovieGenre(id);
	}
}
