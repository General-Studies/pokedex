package com.jetherrodrigues.pokedex.application.config;

import java.io.Serializable;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;

import com.jetherrodrigues.pokedex.resource.pokemon.PokemonDocument;
import com.jetherrodrigues.pokedex.resource.pokemon.PokemonDocumentRepository;

import reactor.core.publisher.Flux;

@Profile("dev")
@Configuration
public class DatabasePopulation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Bean
	CommandLineRunner init (final ReactiveMongoOperations operations, final PokemonDocumentRepository repository) {
		return args -> {
			repository.deleteAll();
			
			Flux<PokemonDocument> pokemonFlux = Flux.just(
					new PokemonDocument()
						.name("Bulbassauro").category("Semente").skills("OverGrow").weight(6.09),
					new PokemonDocument()
						.name("Charizard").category("Fogo").skills("Blaze").weight(90.05),
					new PokemonDocument()
						.name("Caterpie").category("Minhoca").skills("Poeira do Escudo").weight(2.09),
					new PokemonDocument()
						.name("Blastoise").category("Marisco").skills("Torrente").weight(6.09))
							.flatMap(repository::save);
			pokemonFlux
					.thenMany(repository.findAll())
					.subscribe(System.out::println);
		};
	}
}
