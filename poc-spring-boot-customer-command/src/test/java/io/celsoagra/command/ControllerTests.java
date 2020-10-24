package io.celsoagra.command;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.celsoagra.command.entity.Customer;
import io.celsoagra.command.repository.CustomerRepository;

/**
 * Testes relacionados aos Rest Controllers
 * 
 * @author celsoagra
 *
 */
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureMockMvc
public class ControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
		
	@Autowired
	private CustomerRepository customerRepository;
	
	@Order(0)
	@Test
	void limpando_bases() {
		customerRepository.deleteAll();
	}

	
	@Order(3)
	@Test
	public void cadastrar_cliente() throws Exception {
		Map<String, String> cliente = new HashMap<String, String>();
		cliente.put("name", "Celso Agra");
		cliente.put("gender", "M");
		cliente.put("bithdate", "1988-07-26");
		
		mockMvc.perform(post("/customer")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(cliente)))
				.andExpect(status().isOk());
		
		Customer celso = customerRepository.findOneByNameIgnoreCase("celso agra").get();
		assertNotNull(celso);
	}

	
	@Order(5)
	@Test
	public void consultar_cliente_pelo_nome() throws Exception {
		mockMvc.perform(get("/customer/find")
				.param("name", "Celso Agra")
				.contentType("application/json"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("id").exists())
				.andExpect(jsonPath("name").value("Celso Agra"));
	}

	
	@Order(7)
	@Test
	public void alterar_nome_do_cliente() throws Exception {
		Customer celso = customerRepository.findOneByNameIgnoreCase("celso agra").get();
		
		Map<String, String> update = new HashMap<String, String>();
		update.put("name", "Celso Agra Filho");
		
		mockMvc.perform(put("/customer/" + celso.getId())
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(update)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("id").value(celso.getId()))
				.andExpect(jsonPath("name").value("Celso Agra Filho"));
	}
	
	@Order(8)
	@Test
	public void remover_cliente() throws Exception {
		Customer celso = customerRepository.findOneByNameIgnoreCase("celso agra filho").get();
		
		mockMvc.perform(delete("/customer/" + celso.getId())
				.contentType("application/json"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("msg").value("Cliente removido do cadastro."));
		
	}
	
	@Order(13)
	@Test
	public void alterar_nome_do_cliente_não_encontrado() throws Exception {
		Map<String, String> update = new HashMap<String, String>();
		update.put("nome", "Celso Agra Filho");
		
		mockMvc.perform(put("/customer/0")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(update)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("msg").value("Cliente não encontrado."));
	}
	
	@Order(14)
	@Test
	public void remover_cliente_nao_encontrado() throws Exception {
		mockMvc.perform(delete("/customer/0")
				.contentType("application/json"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("msg").value("Cliente não encontrado."));
		
	}

}
