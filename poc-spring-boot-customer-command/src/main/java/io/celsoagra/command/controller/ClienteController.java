package io.celsoagra.command.controller;

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

import io.celsoagra.command.dto.CreateCustomerDTO;
import io.celsoagra.command.dto.UpdateCustomerDTO;
import io.celsoagra.command.entity.Customer;
import io.celsoagra.command.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

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

	@ApiOperation(value = "Cadastro de um cliente")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	private Customer create(@Valid @RequestBody CreateCustomerDTO dto) throws NotFoundException, JsonProcessingException {
		return service.create(dto);
	}

	@ApiOperation(value = "Consulta um cliente por nome")
	@GetMapping(path = "/find", produces = MediaType.APPLICATION_JSON_VALUE, params = { "name" })
	private Customer findByName(@RequestParam(required = true) String name) throws NotFoundException {
		return service.findByName(name);
	}

	@ApiOperation(value = "Consulta um cliente por id")
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private Customer findById(@PathVariable(required = true) Long id) throws NotFoundException {
		return service.findById(id);
	}

	@ApiOperation(value = "Remove um cliente do sistema")
	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private Map<String, String> remove(@PathVariable(required = true) Long id) throws NotFoundException {
		service.delete(id);

		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "Cliente removido do cadastro.");
		return map;
	}

	@ApiOperation(value = "Altera o nome do cliente")
	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private Customer cadastrar(@PathVariable(required = true) Long id, @Valid @RequestBody UpdateCustomerDTO dto)
			throws NotFoundException {
		return service.update(id, dto);
	}

}
