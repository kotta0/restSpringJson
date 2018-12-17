package com.karamihaylov.rest.webservices.restfulwebservices.daydream;

import javax.validation.constraints.Size;

public class DayDream {

	private Integer ID;
	
	@Size(min=2, message="Name should have at lease 2 letters")
	private String name;
	
	protected DayDream() {
	}
	
	public DayDream(int ID, String name) {
		super();
		this.name = name;
		this.ID = ID;
	}

	public Integer getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
