package com.jetherrodrigues.pokedex.application.web;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.jetherrodrigues.pokedex.application.web.request.PokemonRequest;
import com.jetherrodrigues.pokedex.domain.pokemon.Pokemon;
import com.jetherrodrigues.pokedex.domain.pokemon.PokemonService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonControllerImpl implements PokemonController {
	
	private final PokemonService service;

	@Autowired
	public PokemonControllerImpl(final PokemonService service) {
		this.service = service;
	}
	
	@Override
	public Flux<Pokemon> getAll() {
		return service.findAll();
	}
	
	@Override
	public Flux<Pokemon> getAllPokemonEvents() {
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
        Flux<Pokemon> events = service.findAll();
        
        return Flux.zip(events, interval, (key, value) -> key);
	}
	
	@Override
	public Mono<ResponseEntity<Pokemon>> getPokemonById(final String id) {
        return service.findById(id).map(ResponseEntity::ok);
    }
	
	@Override
	public ResponseEntity<Mono<Pokemon>> savePokemon(final PokemonRequest request) {
		final Mono<Pokemon> saved = service.save(request.toPokemon());
		final Mono<String> id = saved.map(Pokemon::getId);
		
		return ResponseEntity.created(
					UriComponentsBuilder.fromPath("/api/pokeman/{id}").buildAndExpand(id).toUri()
				).body(saved);
    }
	
	@Override
	public Mono<ResponseEntity<Pokemon>> updatePokemon(final PokemonRequest request) {
		final Pokemon pokemon = request.toUpdatePokemon();
        return service.update(pokemon).then(Mono.just(ResponseEntity.ok().body(pokemon)));
    }
	
	@Override
	public Mono<ResponseEntity<Void>> deletePokemon(final String id) {
        return service.delete(id).then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
}
