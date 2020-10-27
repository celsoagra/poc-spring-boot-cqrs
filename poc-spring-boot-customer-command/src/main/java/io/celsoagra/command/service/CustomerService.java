package io.celsoagra.command.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

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
	private CustomerRepository clienteRepository;
	
	@Autowired
	private CustomerMQSender mqSender;

	public Customer create(CreateCustomerDTO dto) throws NotFoundException, JsonProcessingException {
		Gender genero = null;
		try {
			genero = Gender.valueOf(dto.getGender().toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new NotFoundException("Genero não encontrado, tente incluir M ou F");
		}

		Customer cliente = new Customer(dto.getName(), genero, dto.getBithdate());
		Customer saved = clienteRepository.save(cliente);
		
		this.mqSender.send(saved);
		return saved;
	}

	public void delete(Long id) throws NotFoundException, JsonProcessingException {
		Customer customer = clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não encontrado."));
		clienteRepository.deleteById(id);
	
		this.mqSender.sendAndRemove(customer);
	}

	public Customer update(Long id, UpdateCustomerDTO dto) throws NotFoundException, JsonProcessingException {
		Customer cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cliente não encontrado."));
		cliente.setName(dto.getName());

		Customer updated = clienteRepository.save(cliente);
		
		this.mqSender.sendAndUpdate(id, dto);
		return updated;
	}

	public Customer findByName(String name) throws NotFoundException {
		return clienteRepository.findOneByNameIgnoreCase(name)
				.orElseThrow(() -> new NotFoundException("Cliente não encontrado."));
	}
	
	public Customer findById(Long id) throws NotFoundException {
		return clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não encontrado."));
	}

}
