package io.celsoagra.query.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.celsoagra.query.entity.Customer;

/**
 * Repositorio referente ao Cliente
 * 
 * @author celsoagra
 *
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

	List<Customer> findOneByNameContainingIgnoreCase(String name);

}
