package io.celsoagra.command.boundary.city.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.celsoagra.command.boundary.city.dto.FindCityDTO;
import javassist.NotFoundException;

/**
 * Camada de negócio relacionada ao Cliente
 * 
 * @author celsoagra
 *
 */
@Component
public class CityRepository {

	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Value("${app.api.url.city.base}")
	private String baseAPICity;
	
	@Value("${app.api.url.city.find}")
	private String findAPICity;

	private static final Logger LOGGER = LogManager.getLogger(CityRepository.class);

	public FindCityDTO findByName(String name) throws NotFoundException {
		String url = baseAPICity + findAPICity + "?name={name}";
		
		Map<String, String> params = new HashMap<>();
		params.put("name", name);
		
		String str = restTemplate.getForObject(url, String.class, params);
		LOGGER.info("STR: " + str);
		
		return restTemplate.getForObject(url, FindCityDTO.class, params);
	}

}
