package com.jetherrodrigues.pokedex.application.web.exceptions;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BadRequestException(final String message) {
		super(message);	
	}
	
}
