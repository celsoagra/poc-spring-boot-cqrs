package io.celsoagra.command.dto;

/**
 * Data Transfer Object.
 * 
 * Responsável pelo case de alteração do nome do cliente
 * 
 * @author celsoagra
 *
 */
public class UpdateCustomerDTO {

	private String name;

	private UpdateCustomerDTO() {

	}

	private UpdateCustomerDTO(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
