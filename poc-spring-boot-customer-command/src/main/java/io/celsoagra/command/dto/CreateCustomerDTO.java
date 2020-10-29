package io.celsoagra.command.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * Data Transfer Object.
 * 
 * Responsável pelo case de criação do cliente
 * 
 * @author celsoagra
 *
 */
public class CreateCustomerDTO {

	@NotNull
	@Length(min = 2, max = 30, message = "O tamanho do nome deve ser entre {min} e {max} caracteres")
	private String name;

	@NotNull
	@Length(min = 1, max = 1, message = "O sexo do cliente deve atender aos caracteres M ou F.")
	private String gender;

	@NotNull
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate bithdate;

	@NotNull
	private String city;

	public CreateCustomerDTO() {

	}

	public CreateCustomerDTO(String name, String gender, LocalDate bithdate, String city) {
		super();
		this.name = name;
		this.gender = gender;
		this.bithdate = bithdate;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getBithdate() {
		return bithdate;
	}

	public void setBithdate(LocalDate bithdate) {
		this.bithdate = bithdate;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "CreateCustomerDTO [name=" + name + ", gender=" + gender + ", bithdate=" + bithdate + "]";
	}

}
