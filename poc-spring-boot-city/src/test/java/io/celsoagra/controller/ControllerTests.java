package io.celsoagra.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.celsoagra.entity.Cidade;
import io.celsoagra.repository.CidadeRepository;

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
	private CidadeRepository cidadeRepository;
	
	@Order(0)
	@Test
	void limpando_bases() {
		cidadeRepository.deleteAll();
	}

	@Order(1)
	@Test
	public void health_check() throws Exception {
		MvcResult result = mockMvc.perform(get("/health")
				.contentType("application/json"))
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals("check!", result.getResponse().getContentAsString());
	}
	
	@Order(2)
	@Test
	public void cadastrar_cidade() throws Exception {
		Map<String, String> cidade = new HashMap<String, String>();
		cidade.put("nome", "recife");
		cidade.put("estado", "PE");
		
		mockMvc.perform(post("/cidade")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(cidade)))
				.andExpect(status().isOk());
		
		Cidade recife = cidadeRepository.findOneByNomeIgnoreCase("recife").get();
		assertNotNull(recife);
	}
	
	@Order(4)
	@Test
	public void consultar_cidade_pelo_nome() throws Exception {
		mockMvc.perform(get("/cidade/find")
				.param("nome", "recife")
				.contentType("application/json"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("id").exists())
				.andExpect(jsonPath("nome").value("recife"))
	            .andExpect(jsonPath("estado").value("PE"));
	}
	
	@Order(5)
	@Test
	public void consultar_cidade_pelo_estado() throws Exception {
		mockMvc.perform(get("/cidade/find")
				.param("estado", "PE")
				.contentType("application/json"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").exists());
	}

	
	@Order(10)
	@Test
	public void cadastrar_cidade_com_estado_desconhecido() throws Exception {
		Map<String, String> cidade = new HashMap<String, String>();
		cidade.put("nome", "recife");
		cidade.put("estado", "EP");
		
		mockMvc.perform(post("/cidade")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(cidade)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("msg").value("Estado não encontrado, tente novamente respeitando o padrão de siglas. Ex.: PE, RS, SP, ..."));
	}
	
	@Order(11)
	@Test
	public void consultar_cidade_desconhecida_pelo_nome() throws Exception {
		mockMvc.perform(get("/cidade/find")
				.param("nome", "pindamonhangaba")
				.contentType("application/json"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("msg").value("Cidade não encontrada."));
	}
	
	@Order(12)
	@Test
	public void consultar_cidade_pelo_estado_desconhecido() throws Exception {
		mockMvc.perform(get("/cidade/find")
				.param("estado", "EP")
				.contentType("application/json"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("msg").value("Estado não encontrado, tente novamente respeitando o padrão de siglas. Ex.: PE, RS, SP, ..."));
	}

}
