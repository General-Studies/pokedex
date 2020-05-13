package com.jetherrodrigues.pokedex.application.web;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jetherrodrigues.pokedex.application.web.request.PokemonRequest;
import com.jetherrodrigues.pokedex.domain.pokemon.Pokemon;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PokemonController {
		
	@GetMapping(produces = "application/vnd.pokedex.pokemons-v1+json")
	public Flux<Pokemon> getAll();
	
	@GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Pokemon> getAllPokemonEvents();
	
	@GetMapping(value = "/{id}", produces = "application/vnd.pokedex.pokemon_detail-v1+json")
    public Mono<ResponseEntity<Pokemon>> getPokemonById(@PathVariable final String id);
	
	@PostMapping(produces = "application/vnd.pokedex.pokemon_create-v1+json")
    public ResponseEntity<Mono<Pokemon>> savePokemon(@RequestBody final PokemonRequest request);
	
	@PutMapping(produces = "application/vnd.pokedex.pokemon_update-v1+json")
    public Mono<ResponseEntity<Pokemon>> updatePokemon(@RequestBody final PokemonRequest request);
	
	@DeleteMapping(value = "{id}", produces = "application/vnd.pokedex.pokemon_delete-v1+json")
    public Mono<ResponseEntity<Void>> deletePokemon(@PathVariable(value = "id") final String id);
}
