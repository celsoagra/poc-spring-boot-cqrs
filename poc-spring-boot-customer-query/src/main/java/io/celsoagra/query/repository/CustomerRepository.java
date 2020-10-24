package io.celsoagra.query.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.celsoagra.query.entity.Customer;

/**
 * Repositorio referente ao Cliente
 * 
 * @author celsoagra
 *
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

	Optional<Customer> findOneByNameContainingIgnoreCase(String name);

}
