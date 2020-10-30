package io.celsoagra;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.celsoagra.utils.enumeration.Estado;

/**
 * Testes gerais da Aplicação
 * 
 * @author celsoagra
 *
 */
@SpringBootTest
class SpringBootInterviewApplicationTests {

	@Test
	void validando_consulta_estado_enum() {
		Estado estado = Estado.valueOf("PE");
		assertEquals(Estado.PE, estado);
	}

}
