package com.karamihaylov.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public MappingJacksonValue retriveSomeBean() {
		SomeBean someBean = new SomeBean("value1","value2","value3");
		MappingJacksonValue mapping = new  MappingJacksonValue(someBean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","filed2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter  );
		
		mapping.setFilters(filters);
		return mapping;
	}
	
	@GetMapping("/filtering-list")
	public List<SomeBean> retriveListOfSomeBean() {
		return Arrays.asList( new SomeBean("value1","value2","value3") ,
							  new SomeBean("value12","value22","value32"));
	}
}
