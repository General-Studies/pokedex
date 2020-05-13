package com.jetherrodrigues.pokedex.resource.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jetherrodrigues.pokedex.domain.exceptions.NotFoundException;
import com.jetherrodrigues.pokedex.domain.pokemon.Pokemon;
import com.jetherrodrigues.pokedex.domain.pokemon.PokemonRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PokemonMongoAdapter implements PokemonRepository {

	@Autowired
	private PokemonDocumentRepository repository;
	
	@Override
	public Flux<Pokemon> findAll() {
		return repository.findAll().map(PokemonDocument::toPokemon);
	}

	@Override
	public Mono<Pokemon> findById(final String id) {
		return repository.findById(id)
				.map(PokemonDocument::toPokemon)
				.onErrorResume(e -> Mono.error(new NotFoundException(String.format("The Pokemon [Id: %s] was not foundd!", id))));
	}

	@Override
	public Mono<Pokemon> save(final Pokemon entity) {
		return repository.save(PokemonDocument.from(entity)).map(PokemonDocument::toPokemon);
	}
	
	@Override
	public Mono<Pokemon> update(final Pokemon entity) {
		return repository.findById(entity.getId())
		        .flatMap(existingPokemon -> {
		            existingPokemon
			            .name(entity.getName())
			            .category(entity.getCategory())
			            .skills(entity.getSkills())
			            .weight(entity.getWeight());                    
		            
		            return repository.save(existingPokemon);
		        })
		        .map(PokemonDocument::toPokemon)
		        .onErrorResume(e -> Mono.error(new NotFoundException(String.format("The Pokemon [Id: %s] was not foundd!", entity.getId()))));
	}

	@Override
	public Mono<Void> delete(final String id) {
		return repository.findById(id)
		        .flatMap(existingPokemon ->
		                repository.delete(existingPokemon)
		        ).onErrorResume(e -> Mono.error(new NotFoundException(String.format("The Pokemon [Id: %s] was not foundd!", id))));		
	}
}
