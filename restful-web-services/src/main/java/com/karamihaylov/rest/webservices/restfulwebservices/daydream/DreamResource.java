package com.karamihaylov.rest.webservices.restfulwebservices.daydream;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
//import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class DreamResource {

	@Autowired
	private DayDreamDaoService service;
	//GET retrieve All users
	@GetMapping("/dreams")
	public List<DayDream> retrieveAllDreams() {

		return service.findAll();
	}
	
	@GetMapping("/dreams/{id}")
	public Resource<DayDream> retrieveDream(@PathVariable int id) {
		DayDream dayDream = service.findOne(id);
		if (dayDream == null) {
			throw new DayDreamNotFoundException("id - " + id);
		}
		
		Resource<DayDream> resource = new Resource<>(dayDream);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllDreams());
		resource.add(linkTo.withRel("all-dreams"));
		
		return resource;
	}
	
	//input details of dream
	//output - Created & Return the created URI
	@PostMapping("/dreams")
	public ResponseEntity<Object> createDream(@Valid @RequestBody DayDream dream) {
		DayDream savedDream = service.save(dream);
		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedDream.getID()).toUri();
				

		return ResponseEntity.created(location).build();
	}
	
	
	@DeleteMapping("/dreams/{id}")
	public void removeDream(@PathVariable int id) {		
		 DayDream result =  service.removeById(id);
			if (result == null) {
				throw new DayDreamNotFoundException("id - " + id);
			}
	}
}
