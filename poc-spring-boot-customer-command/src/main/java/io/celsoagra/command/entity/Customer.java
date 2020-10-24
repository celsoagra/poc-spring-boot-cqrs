package io.celsoagra.command.entity;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import io.celsoagra.command.utils.enumeration.Gender;

/**
 * Entidade relacionada ao registro de Clientes. Variaveis: nome; sexo; data de
 * nascimento; idade; e cidade.
 * 
 * @author celsoagra
 */
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Length(min = 2, max = 30, message = "The name must be greater than {min} and less than {max} characters.")
	private String name;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@NotNull
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthdate;

	/**
	 * informação apenas para realizar o calculo da idade do cliente. Não necessita
	 * armazenar estado
	 */
	@Transient
	private Integer age;

	public Customer() {

	}

	public Customer(String name, Gender gender, LocalDate birthdate) {
		super();
		this.name = name;
		this.gender = gender;
		this.birthdate = birthdate;
	}

	public String getName() {
		return name;
	}

	public Customer setName(String name) {
		this.name = name;
		return this;
	}

	public Gender getGender() {
		return gender;
	}

	public Customer setGender(Gender gender) {
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

	public Integer getAge() {
		return (this.birthdate != null) ? Period.between(this.birthdate, LocalDate.now()).getYears() : -1;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", gender=" + gender + ", birthdate=" + birthdate + "]";
	}

}
