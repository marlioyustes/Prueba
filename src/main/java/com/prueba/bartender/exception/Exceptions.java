package com.prueba.bartender.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/*
 * Con esta clase controlamos los errores o validaciones para darle una mejor estetica a las respuestas
 */

@ControllerAdvice
public class Exceptions {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionsResponse> exception(Exception e) {

		ExceptionsResponse exceptionResponse = new ExceptionsResponse("Ocurrió un error");

		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//En este se lanzará una excepción cuando se anote la validación de un argumento
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(err -> {
			errors.put(err.getField(), "El atributo" + err.getField() + " " + err.getDefaultMessage());
		});

		ExceptionsResponse exceptionResponse = new ExceptionsResponse("Ocurrió un error", errors);

		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	//En este metodo controlamos la exception de dque ningun parametro puede ir vacío
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Object> handleMissingParams(MissingServletRequestParameterException ex) {

		Map<String, String> errors = new HashMap<>();
		String parametro = ex.getParameterName();
		errors.put(parametro, "El atributo " + parametro + " No puede estar vacio");
		ExceptionsResponse exceptionResponse = new ExceptionsResponse("Ocurrió un error", errors);

		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);

	}

}
