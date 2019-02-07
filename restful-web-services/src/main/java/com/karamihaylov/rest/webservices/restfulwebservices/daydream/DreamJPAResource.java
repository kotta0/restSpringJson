package com.karamihaylov.rest.webservices.restfulwebservices.daydream;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class DreamJPAResource {

	@Autowired
	private DayDreamDaoService service;
	
	@Autowired
	private DayDreamRepository dayDreamRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	//GET retrieve All users
	@GetMapping("/jpa/dreams")
	public List<DayDream> retrieveAllDreams() {

		return dayDreamRepository.findAll();
	}
	
	@GetMapping("/jpa/dreams/{id}")
	public Resource<DayDream> retrieveDream(@PathVariable int id) {
		Optional<DayDream> dayDream = dayDreamRepository.findById(id);
		
		if (dayDream == null) {
			throw new DayDreamNotFoundException("id - " + id);
		}
		
		Resource<DayDream> resource = new Resource<>(dayDream.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllDreams());
		resource.add(linkTo.withRel("all-dreams"));
		
		return resource;
	}
	
	//input details of dream
	//output - Created & Return the created URI
	@PostMapping("/jpa/dreams")
	public ResponseEntity<Object> createDream(@Valid @RequestBody DayDream dream) {
		DayDream savedDream = dayDreamRepository.save(dream);
		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/jpa/{id}")
						.buildAndExpand(savedDream.getID()).toUri();
				

		return ResponseEntity.created(location).build();
	}
	
	
	@DeleteMapping("/jpa/dreams/{id}")
	public void removeDream(@PathVariable int id) {		
		dayDreamRepository.deleteById(id);
/*			if (result == null) {
				throw new DayDreamNotFoundException("id - " + id);
			}*/
	}
	
	
	@GetMapping("/jpa/dreams/{id}/posts")
	public List<Post> retrieveAllDream(@PathVariable int id) {
		Optional<DayDream> dayDream = dayDreamRepository.findById(id);
		
		if ( !dayDream.isPresent()) {
			throw new DayDreamNotFoundException("id - " + id);
		}
		
		return dayDream.get().getPosts();
	}
	
	
	@PostMapping("/jpa/dreams/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		Optional<DayDream> dayDreamOptional = dayDreamRepository.findById(id);
		
		if (!dayDreamOptional.isPresent()) {
			throw new DayDreamNotFoundException("id - " + id);
		}
/*		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/jpa/{id}")
						.buildAndExpand(dayDream.getID()).toUri();
				*/
		DayDream dayDream = dayDreamOptional.get();
		post.setDayDream(dayDream);
		postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/jpa/{id}/posts")
				.buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
}
