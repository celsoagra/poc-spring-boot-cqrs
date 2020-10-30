package io.celsoagra.controller.handler;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javassist.NotFoundException;

/**
 * Camada Handler para tramento das exceções do sistema
 * 
 * @author celsoagra
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(value = {Exception.class, NotFoundException.class, ConstraintViolationException.class})
	public final ResponseEntity handleAllExceptions(Exception ex, WebRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", ex.getMessage());
		map.put("error", ex.getClass().getName());

		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}
}
