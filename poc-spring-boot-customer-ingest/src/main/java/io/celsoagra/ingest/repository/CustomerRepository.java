package io.celsoagra.ingest.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.celsoagra.ingest.entity.Customer;

/**
 * Repositorio referente ao Cliente
 * 
 * @author celsoagra
 *
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

	
	
}
