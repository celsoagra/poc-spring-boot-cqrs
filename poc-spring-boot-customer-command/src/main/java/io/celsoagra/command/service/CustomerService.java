package io.celsoagra.command.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.celsoagra.command.boundary.city.dto.FindCityDTO;
import io.celsoagra.command.boundary.city.repository.CityRepository;
import io.celsoagra.command.dto.CreateCustomerDTO;
import io.celsoagra.command.dto.UpdateCustomerDTO;
import io.celsoagra.command.entity.Customer;
import io.celsoagra.command.repository.CustomerRepository;
import io.celsoagra.command.utils.enumeration.Gender;
import javassist.NotFoundException;

/**
 * Camada de negócio relacionada ao Cliente
 * 
 * @author celsoagra
 *
 */
@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CustomerMQSender mqSender;

	public Customer create(CreateCustomerDTO dto) throws NotFoundException, JsonProcessingException {
		FindCityDTO cityDTO = this.cityRepository.findByName(dto.getCity());
		Optional<FindCityDTO> optCity = Optional.ofNullable(cityDTO);
		if (optCity.isEmpty()) {
			throw new NotFoundException("Genero não encontrado, tente incluir M ou F");
		}
		
		Gender genero = null;
		try {
			genero = Gender.valueOf(dto.getGender().toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new NotFoundException("Genero não encontrado, tente incluir M ou F");
		}

		Customer cliente = new Customer(dto.getName(), genero, dto.getBithdate(), cityDTO.getName());
		Customer saved = customerRepository.save(cliente);
		
		this.mqSender.send(saved);
		return saved;
	}

	public void delete(Long id) throws NotFoundException, JsonProcessingException {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não encontrado."));
		customerRepository.deleteById(id);
	
		this.mqSender.sendAndRemove(customer);
	}

	public Customer update(Long id, UpdateCustomerDTO dto) throws NotFoundException, JsonProcessingException {
		Customer cliente = customerRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cliente não encontrado."));
		cliente.setName(dto.getName());

		Customer updated = customerRepository.save(cliente);
		
		this.mqSender.sendAndUpdate(id, dto);
		return updated;
	}

	public Customer findByName(String name) throws NotFoundException {
		return customerRepository.findOneByNameIgnoreCase(name)
				.orElseThrow(() -> new NotFoundException("Cliente não encontrado."));
	}
	
	public Customer findById(Long id) throws NotFoundException {
		return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não encontrado."));
	}

}
