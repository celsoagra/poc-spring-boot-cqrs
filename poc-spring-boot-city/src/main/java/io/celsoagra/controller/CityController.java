package io.celsoagra.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.celsoagra.dto.CreateCidadeDTO;
import io.celsoagra.entity.Cidade;
import io.celsoagra.repository.CidadeRepository;
import io.celsoagra.service.CidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

/**
 * Classe Controller referente ao case de Cidade
 * 
 * @author celsoagra
 *
 */
@Api(value = "City")
@RestController
@RequestMapping(path = "/city")
public class CityController {
	
    private static final Logger LOGGER = LogManager.getLogger(CityController.class);

	@Autowired
	private CidadeRepository repository;

	@Autowired
	private CidadeService service;

	@ApiOperation(value = "Cadastro de uma cidade, através do nome e estado")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	private Cidade cadastrar(@Valid @RequestBody CreateCidadeDTO dto) throws NotFoundException {
		LOGGER.info("CityController.cadastrar - Start - Input - [{}]", dto);
		LOGGER.debug("CityController.cadastrar - Start - Input - [{}]", dto);
		
		Cidade city = service.create(dto);
		
		LOGGER.debug("CityController.cadastrar - End - Input: {} Output: {}", dto, city);
		return city;
	}

	@ApiOperation(value = "Consulta uma cidade por Nome")
	@GetMapping(path = "/find", produces = MediaType.APPLICATION_JSON_VALUE, params = { "nome" })
	private Cidade findByNome(@RequestParam(required = true) String name) throws NotFoundException {
		LOGGER.info("CityController.findByNome - Start - Input - [{}]", name);
		LOGGER.debug("CityController.findByNome - Start - Input - [{}]", name);
		
		Cidade city = service.findByName(name);
		
		LOGGER.debug("CityController.findByNome - End - Input: {} Output: {}", name, city);
		return city;
	}

	@ApiOperation(value = "Consulta uma cidade pelo estado")
	@GetMapping(path = "/find", produces = MediaType.APPLICATION_JSON_VALUE, params = { "estado" })
	private List<Cidade> findByEstado(
			@RequestParam(name = "estado", required = true) @Length(min = 2, max = 2) String estadoStr) throws NotFoundException {
		LOGGER.info("CityController.cadastrar - Start - Input - [{}]", estadoStr);
		LOGGER.debug("CityController.cadastrar - Start - Input - [{}]", estadoStr);
		
		List<Cidade> list = service.findByEstado(estadoStr);
		
		LOGGER.debug("CityController.cadastrar - End - Input: {} Output: {}", estadoStr, list.size());
		return list;
		
		
	}
}
