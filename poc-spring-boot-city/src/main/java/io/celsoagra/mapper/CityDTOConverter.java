package io.celsoagra.mapper;

import org.modelmapper.AbstractConverter;

import io.celsoagra.dto.CreateCidadeDTO;
import io.celsoagra.entity.Cidade;
import io.celsoagra.utils.enumeration.Estado;

public class CityDTOConverter extends AbstractConverter<CreateCidadeDTO, Cidade> {
 
    @Override
    protected Cidade convert(CreateCidadeDTO dto) {
    	Estado estado = Estado.valueOf(dto.getEstado().toUpperCase());
    	return Cidade.builder().nome(dto.getNome()).estado(estado).build();
    }
}