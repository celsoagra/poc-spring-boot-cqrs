package io.celsoagra.query.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.celsoagra.query.entity.Customer;
import io.celsoagra.query.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * Classe Controller referente ao case de Cidade
 * 
 * @author celsoagra
 *
 */
@Api(value = "Customer", description = "Endpoint referente ao case de Clientes")
@RestController
@RequestMapping(path = "/customer")
public class ClienteController {

	@Autowired
	private CustomerService service;

	@ApiOperation(value = "Consulta um cliente por nome")
	@GetMapping(path = "/find", produces = MediaType.APPLICATION_JSON_VALUE, params = { "name" })
	private Customer findByName(@RequestParam(required = true) String name) throws Exception {
		return service.findByName(name);
	}

}
