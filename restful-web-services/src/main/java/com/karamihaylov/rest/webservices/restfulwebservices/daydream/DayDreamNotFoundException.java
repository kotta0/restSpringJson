package com.karamihaylov.rest.webservices.restfulwebservices.daydream;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DayDreamNotFoundException extends RuntimeException {

	public DayDreamNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
