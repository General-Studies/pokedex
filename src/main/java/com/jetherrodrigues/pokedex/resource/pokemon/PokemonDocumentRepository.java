package com.jetherrodrigues.pokedex.resource.pokemon;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonDocumentRepository extends ReactiveMongoRepository<PokemonDocument, String>{

}
