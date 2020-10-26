package io.celsoagra.ingest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import io.celsoagra.ingest.entity.Customer;
import io.celsoagra.ingest.repository.CustomerRepository;
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
	MongoTemplate mongoTemplate;

	public Customer create(Customer customer) {
		Customer saved = customerRepository.save(customer);
		return saved;
	}
	
	public Customer update(String id, String name) throws Exception {
		Customer found = customerRepository.findById(id).orElseThrow(() -> new Exception("Cliente não encontrado."));;
		found.setName(name);
		return customerRepository.save(found);
	}
}
