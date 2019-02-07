package com.karamihaylov.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2("Bob Charlie");
		
	}
	
	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1(new Name("Bob","Charlie"));
	}
}
