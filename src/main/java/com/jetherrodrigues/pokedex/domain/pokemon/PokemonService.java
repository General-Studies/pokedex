package com.jetherrodrigues.pokedex.domain.pokemon;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PokemonService {
	
	private PokemonRepository repository;
	
	public PokemonService(final PokemonRepository repository) {
		this.repository = repository;
	}

	public Flux<Pokemon> findAll() {
		return repository.findAll();
	}
	
	public Mono<Pokemon> findById(final String id) {
		return repository.findById(id);
	}
	
	public Mono<Pokemon> save(final Pokemon entity) {
		return repository.save(entity);
	}
	
	public Mono<Pokemon> update(final Pokemon entity) {
		return repository.update(entity);
	}
	
	public Mono<Void> delete(final String id) {
		return repository.delete(id);
	}
}
