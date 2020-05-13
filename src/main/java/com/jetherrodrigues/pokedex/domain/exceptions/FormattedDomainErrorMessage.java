package com.jetherrodrigues.pokedex.domain.exceptions;

public enum FormattedDomainErrorMessage {
	POKEMON_NOT_FOUND("Pokemon [id: %s] not found!");
	
	private String description;

	private FormattedDomainErrorMessage(final String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
