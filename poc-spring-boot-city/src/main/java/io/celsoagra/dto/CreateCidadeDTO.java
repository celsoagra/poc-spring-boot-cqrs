package io.celsoagra.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * Data Transfer Object.
 * 
 * Responsável pelo case de criação do cliente
 * 
 * @author celsoagra
 *
 */
public class CreateCidadeDTO {

	@NotNull
	@Length(min = 2, max = 30, message = "O tamanho do nome deve ser entre {min} e {max} caracteres")
	private String nome;
	
	@NotNull
	@Length(min = 2, max = 2, message = "O tamanho do estado deve ser fixo {min} caracteres maiusculos")
	private String estado;

	public CreateCidadeDTO() {

	}

	public CreateCidadeDTO(String nome, String estado) {
		this.nome = nome;
		this.estado = estado;
	}

	public String getNome() {
		return nome;
	}

	public String getEstado() {
		return estado;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "CreateCidadeDTO [nome=" + nome + ", estado=" + estado + "]";
	}

}
