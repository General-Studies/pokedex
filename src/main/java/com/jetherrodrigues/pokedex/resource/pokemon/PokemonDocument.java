package com.jetherrodrigues.pokedex.resource.pokemon;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jetherrodrigues.pokedex.domain.pokemon.Pokemon;

@Document(collection = "pokemons")
public final class PokemonDocument implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    private String id;
	@Indexed(unique = true)
    private String name;
    private String category;
    private String skills;
    private Double weight;
	
    public PokemonDocument() {}
    
    private PokemonDocument(final String name, final String category, final String skills, final Double weight) {		
		this.name = name;
		this.category = category;
		this.skills = skills;
		this.weight = weight;
	}
    
    public final PokemonDocument name(final String name) {
    	this.name = name;
    	return this;
    }
    
    public final PokemonDocument category(final String category) {
    	this.category = category;
    	return this;
    }
    
    public final PokemonDocument skills(final String skills) {
    	this.skills = skills;
    	return this;
    }
    
    public final PokemonDocument weight(final Double weight) {
    	this.weight = weight;
    	return this;
    }
    
    public final Pokemon toPokemon() {
    	return Pokemon.of(this.id, this.name, this.category, this.skills, this.weight);
    }
    
    public static PokemonDocument from(final Pokemon pokemon) {
    	return new PokemonDocument(pokemon.getName(), pokemon.getCategory(), pokemon.getSkills(), pokemon.getWeight());
    }
    
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public String getSkills() {
		return skills;
	}

	public Double getWeight() {
		return weight;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PokemonDocument other = (PokemonDocument) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(weight, other.weight);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pokemon [id=").append(id).append(", name=").append(name).append(", category=").append(category)
				.append(", skills=").append(skills).append(", weight=").append(weight).append("]");
		return builder.toString();
	}
}
