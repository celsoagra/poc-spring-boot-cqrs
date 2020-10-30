package io.celsoagra.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.celsoagra.entity.Cidade;
import io.celsoagra.utils.enumeration.Estado;

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
	private CidadeRepository cidadeRepository;
	
	@Order(0)
	@Test
	void limpando_bases() {
		cidadeRepository.deleteAll();
	}

	@Order(1)
	@Test
	void cadastrando_cidades() {
		Cidade recife = Cidade.builder().nome("ReciFE").estado(Estado.PE).build();
		cidadeRepository.save(recife);

		Cidade caruaru = Cidade.builder().nome("CARUAru").estado(Estado.PE).build();
		cidadeRepository.save(caruaru);

		Cidade ipojuca = Cidade.builder().nome("Ipojuca").estado(Estado.PE).build();
		cidadeRepository.save(ipojuca);

		Cidade passoFundo = Cidade.builder().nome("PASSO FUNDO").estado(Estado.RS).build();
		cidadeRepository.save(passoFundo);

		Cidade cidadeComNomeMenor = Cidade.builder().nome("A").estado(Estado.RS).build();
		assertThrows(ConstraintViolationException.class, () -> cidadeRepository.save(cidadeComNomeMenor));

		Cidade cidadeComNomeMaior = Cidade.builder().nome("nNcIlkf7KgkvEIdJGoMrZKWWQ8M5A4y").estado(Estado.RS).build();
		assertThrows(ConstraintViolationException.class, () -> cidadeRepository.save(cidadeComNomeMaior));

		assertEquals(4, cidadeRepository.count());
	}


}
