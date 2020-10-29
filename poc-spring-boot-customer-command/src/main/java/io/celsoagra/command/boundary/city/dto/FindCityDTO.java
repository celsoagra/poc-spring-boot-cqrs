package io.celsoagra.command.boundary.city.dto;

public class FindCityDTO {
	private String name;
	private String state;

	public FindCityDTO() {

	}

	public FindCityDTO(String name, String state) {
		super();
		this.name = name;
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
