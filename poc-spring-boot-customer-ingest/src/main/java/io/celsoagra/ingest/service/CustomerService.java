package io.celsoagra.ingest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.celsoagra.ingest.entity.Customer;
import io.celsoagra.ingest.repository.CustomerRepository;

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

	public Customer create(Customer customer) {
		Customer saved = customerRepository.save(customer);
		return saved;
	}
}
