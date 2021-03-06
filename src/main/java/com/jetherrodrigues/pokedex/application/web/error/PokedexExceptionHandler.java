package com.jetherrodrigues.pokedex.application.web.error;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jetherrodrigues.pokedex.application.web.exceptions.BadRequestException;
import com.jetherrodrigues.pokedex.domain.exceptions.NotFoundException;

@RestControllerAdvice
public class PokedexExceptionHandler implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String NOT_FOUND_ERROR = "NOT_FOUND_ERROR";
	private static final String BAD_REQUEST_ERROR = "BAD_REQUEST_ERROR";
	private static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
	
	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleGenericNotFoundException(final NotFoundException e) {
		return new ResponseEntity<>(ErrorResponse.of(NOT_FOUND_ERROR, e.getMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<ErrorResponse> handleGenericBadRequestException(final BadRequestException e) {
		return new ResponseEntity<>(ErrorResponse.of(BAD_REQUEST_ERROR, e.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(final Exception e) {
		return new ResponseEntity<>(ErrorResponse.of(INTERNAL_SERVER_ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
