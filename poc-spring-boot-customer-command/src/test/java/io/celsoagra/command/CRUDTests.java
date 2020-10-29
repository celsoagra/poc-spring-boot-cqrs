package io.celsoagra.command;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.celsoagra.command.entity.Customer;
import io.celsoagra.command.repository.CustomerRepository;
import io.celsoagra.command.utils.enumeration.Gender;

/**
 * Testes relacionados aos Repositories
 * 
 * @author celsoagra
 *
 */
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class CRUDTests {

	@Autowired
	private CustomerRepository customerRepository;

	@Order(0)
	@Test
	void limpando_bases() {
		customerRepository.deleteAll();
	}

	@Order(2)
	@Test
	void cadastrando_clientes() {
		Customer celso = new Customer("Celso Agra", Gender.M, LocalDate.of(1988, Month.JULY, 26), "recife");
		celso = customerRepository.save(celso);

		Customer debora = new Customer("Debora Cole", Gender.F, LocalDate.of(1990, Month.APRIL, 20), "passo fundo");
		customerRepository.save(debora);

		Customer sergio = new Customer("Sergio Faria", Gender.M, LocalDate.of(1986, Month.DECEMBER, 12), "passo fundo");
		customerRepository.save(sergio);

		assertEquals(3, customerRepository.count());
	}

	@Order(3)
	@Test
	void consultando_clientes() {
		assertNotNull(customerRepository.findOneByNameIgnoreCase("Celso Agra"));

		assertNotNull(customerRepository.findOneByNameIgnoreCase("Debora Cole"));

		assertNotNull(customerRepository.findOneByNameIgnoreCase("Sergio Faria"));
	}

	@Order(3)
	@Test
	void removendo_clientes() {
		assertEquals(3, customerRepository.count());

		Customer celso = customerRepository.findOneByNameIgnoreCase("Celso Agra").get();
		customerRepository.deleteById(celso.getId());

		assertEquals(2, customerRepository.count());

		Customer debora = customerRepository.findOneByNameIgnoreCase("Debora Cole").get();
		customerRepository.deleteById(debora.getId());

		assertEquals(1, customerRepository.count());

		Customer sergio = customerRepository.findOneByNameIgnoreCase("Sergio Faria").get();
		customerRepository.deleteById(sergio.getId());

		assertEquals(0, customerRepository.count());
	}

}
