package io.celsoagra.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.celsoagra.entity.Cidade;
import io.celsoagra.utils.enumeration.Estado;

/**
 * Repositorio referente a Cidade
 * 
 * @author celsoagra
 *
 */
public interface CidadeRepository extends JpaRepository<Cidade, String> {

	Optional<Cidade> findOneByNomeIgnoreCase(String nome);
	
	List<Cidade> findByEstado(Estado nome);

}
