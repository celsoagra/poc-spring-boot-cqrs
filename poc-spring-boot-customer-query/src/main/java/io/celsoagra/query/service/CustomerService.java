package io.celsoagra.query.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.celsoagra.query.entity.Customer;
import io.celsoagra.query.repository.CustomerRepository;

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

	public List<Customer> findByName(String name) throws Exception {
		
		return customerRepository.findOneByNameContainingIgnoreCase(name);
	}
}
