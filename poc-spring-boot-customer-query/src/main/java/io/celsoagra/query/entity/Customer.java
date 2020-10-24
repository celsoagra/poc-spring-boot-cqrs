package io.celsoagra.query.entity;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * Entidade relacionada ao registro de Clientes. Variaveis: nome; sexo; data de
 * nascimento; idade; e cidade.
 * 
 * @author celsoagra
 */
@Document(collection = "customer")
public class Customer {

	@Id
	private String id;
	private String name;
	private String gender;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate birthdate;
	
	@Transient
	private Integer age;

	public Customer() {

	}

	public String getName() {
		return name;
	}

	public Customer setName(String name) {
		this.name = name;
		return this;
	}

	public String getGender() {
		return gender;
	}

	public Customer setGender(String gender) {
		this.gender = gender;
		return this;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public Customer setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
		return this;
	}

	public String getId() {
		return id;
	}
	
	public Integer getAge() {
		return (this.birthdate != null) ? Period.between(this.birthdate, LocalDate.now()).getYears() : -1;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", gender=" + gender + ", birthdate=" + birthdate + "]";
	}

}
