package io.celsoagra.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.celsoagra.dto.CreateCidadeDTO;
import io.celsoagra.entity.Cidade;
import io.celsoagra.repository.CidadeRepository;
import io.celsoagra.utils.enumeration.Estado;
import javassist.NotFoundException;

/**
 * Camada de negócio relacionada ao Cliente
 * 
 * @author celsoagra
 *
 */
@Service
public class CidadeService {

	private static final Logger LOGGER = LogManager.getLogger(CidadeService.class);

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	ModelMapper modelMapper;

	public Cidade create(CreateCidadeDTO dto) throws NotFoundException {
		LOGGER.info("CidadeService.findByName - Start - Input - [{}]", dto);
		LOGGER.debug("CidadeService.findByName - Start - Input - [{}]", dto);

		Cidade cidade = modelMapper.map(dto, Cidade.class);
		cidade = cidadeRepository.save(cidade);

		LOGGER.debug("CidadeService.findByName - End - Input: {} Output: {}", dto, cidade);
		return cidade;
	}

	public Cidade findByName(String name) throws NotFoundException {
		LOGGER.info("CidadeService.findByName - Start - Input - [{}]", name);
		LOGGER.debug("CidadeService.findByName - Start - Input - [{}]", name);

		Optional<Cidade> optCity = cidadeRepository.findOneByNomeIgnoreCase(name.toLowerCase());

		if (optCity.isEmpty()) {
			throw new NotFoundException(
					"Estado não encontrado, tente novamente respeitando o padrão de siglas. Ex.: PE, RS, SP, ...");
		}

		Cidade city = optCity.get();

		LOGGER.debug("CidadeService.findByName - End - Input: {} Output: {}", name, city);
		return city;
	}

	public List<Cidade> findByEstado(String estadoStr) throws NotFoundException {
		Estado estado = null;
		try {
			estado = Estado.valueOf(estadoStr.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new NotFoundException(
					"Estado não encontrado, tente novamente respeitando o padrão de siglas. Ex.: PE, RS, SP, ...");
		}

		return cidadeRepository.findByEstado(estado);
	}

}
