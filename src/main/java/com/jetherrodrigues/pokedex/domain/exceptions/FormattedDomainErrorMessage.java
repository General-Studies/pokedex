package com.jetherrodrigues.pokedex.domain.exceptions;

public enum FormattedDomainErrorMessage {
	POKEMON_NOT_FOUND("The Pokemon [Id: %s] was not foundd!");
	
	private String description;

	private FormattedDomainErrorMessage(final String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
