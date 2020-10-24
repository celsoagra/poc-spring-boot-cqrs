package io.celsoagra.command.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.celsoagra.command.entity.Customer;

/**
 * Repositorio referente ao Cliente
 * 
 * @author celsoagra
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findOneByNameIgnoreCase(String name);
	
}
