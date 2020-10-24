package io.celsoagra.query.handler;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Camada Handler para tramento das exceções do sistema
 * 
 * @author celsoagra
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	private static final Logger LOGGER = LogManager.getLogger(ControllerExceptionHandler.class);
	
	@ExceptionHandler(value = {Exception.class, JsonProcessingException.class })
	public final ResponseEntity handleAllExceptions(Exception ex, WebRequest request) {
		
		LOGGER.error(ex);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", ex.getMessage());
		map.put("error", ex.getClass().getName());

		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}
}
