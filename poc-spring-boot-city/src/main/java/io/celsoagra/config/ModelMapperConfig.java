package io.celsoagra.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.celsoagra.mapper.CityDTOConverter;

@Configuration
public class ModelMapperConfig {

	@Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(new CityDTOConverter());
        return modelMapper;
    }
	
}
