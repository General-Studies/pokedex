package com.jetherrodrigues.pokedex.application.web.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.jetherrodrigues.pokedex.domain.pokemon.Pokemon;

public final class PokemonRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	@NotNull
	private String name;
	@NotNull
	private String category;
	@NotNull
	private String skills;
	@NotNull
	private Double weight;

	public final Pokemon toPokemon() {
		return Pokemon.of(this.getName(), this.getCategory(), this.getSkills(), this.getWeight());
	}
	
	public final Pokemon toUpdatePokemon() {
		return Pokemon.of(this.getId(), this.getName(), this.getCategory(), this.getSkills(), this.getWeight());
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setCategory(final String category) {
		this.category = category;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(final String skills) {
		this.skills = skills;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(final Double weight) {
		this.weight = weight;
	}
}
