package io.celsoagra.command.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javassist.NotFoundException;

/**
 * Camada de negócio relacionada ao Cliente
 * 
 * @author celsoagra
 *
 */
@Service
public class CityService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Value("${app.api.url.city}")
    private String urlCityAPI;
	
	private static final Logger LOGGER = LogManager.getLogger(CityService.class);

	public String findByName(String name) throws NotFoundException {
		String quote = restTemplate.getForObject(urlCityAPI, String.class);
		
		LOGGER.info("Resultado da Chamada REST: " + quote);
		return quote;
	}

}
