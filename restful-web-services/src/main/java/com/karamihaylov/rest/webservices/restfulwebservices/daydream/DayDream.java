package com.karamihaylov.rest.webservices.restfulwebservices.daydream;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Generated;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the DayDream")
@Entity
public class DayDream {
	
	@Id
	@GeneratedValue
	private Integer ID;
	
	@Size(min=2, message="Name should have at lease 2 letters")
	@ApiModelProperty(notes="Name should have at lease 2 letters")
	private String name;
	
	
	@ApiModelProperty(notes="Day of Creation of Dream")
	private Date createDate;
	
	@OneToMany(mappedBy="dayDream")
	private List<Post> posts;

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

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
