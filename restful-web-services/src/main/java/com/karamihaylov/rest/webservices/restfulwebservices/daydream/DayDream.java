package com.karamihaylov.rest.webservices.restfulwebservices.daydream;


import java.util.Date;

import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the DayDream")
public class DayDream {

	private Integer ID;
	
	@Size(min=2, message="Name should have at lease 2 letters")
	@ApiModelProperty(notes="Name should have at lease 2 letters")
	private String name;
	
	@ApiModelProperty(notes="Day of Creation of Dream")
	private Date createDate;
	

	public DayDream(Integer iD, @Size(min = 2, message = "Name should have at lease 2 letters") String name,
			Date createDate) {
		super();
		ID = iD;
		this.name = name;
		this.createDate = createDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

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
