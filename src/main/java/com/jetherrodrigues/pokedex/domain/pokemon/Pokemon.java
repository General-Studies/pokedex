package com.jetherrodrigues.pokedex.domain.pokemon;

import java.io.Serializable;
import java.util.Objects;

public final class Pokemon implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
    private String category;
    private String skills;
    private Double weight;
	
    private Pokemon(final String name, final String category, final String skills, final Double weight) {
		super();
		
		this.name = name;
		this.category = category;
		this.skills = skills;
		this.weight = weight;
	}
    
    private Pokemon(final String id, final String name, final String category, final String skills, final Double weight) {
		super();
		
		this.id = id;
		this.name = name;
		this.category = category;
		this.skills = skills;
		this.weight = weight;
	}
    
    public static Pokemon of(final String name, final String category, final String skills, final Double weight) {
    	return new Pokemon(name, category, skills, weight);
    }
    
    public static Pokemon of(final String id, final String name, final String category, final String skills, final Double weight) {
    	return new Pokemon(id, name, category, skills, weight);
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
		Pokemon other = (Pokemon) obj;
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
