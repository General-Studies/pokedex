package com.jetherrodrigues.pokedex.domain.pokemon;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PokemonRepository {
	Flux<Pokemon> findAll();
	
	Mono<Pokemon> findById(final String id);
	
	Mono<Pokemon> save(final Pokemon entity);
	
	Mono<Pokemon> update(final Pokemon entity);
	
	Mono<Void> delete(final String id);
}
