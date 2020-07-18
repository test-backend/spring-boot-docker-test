package com.backend.springboot.docker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.docker.dto.PersonDTO;
import com.backend.springboot.docker.service.PersonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path="/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping(value = "/status")
	public String status() {
		return "OK";
	}
	
	@PostMapping
	public PersonDTO addPerson(@RequestBody PersonDTO person) throws Exception{
        try {
			return personService.save(person.mapToEntity());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	@GetMapping(value = "/{id}")
	public PersonDTO getPersonAndLocationById(@PathVariable("id") Integer id) {
		try {
			return personService.findById(id);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

}
