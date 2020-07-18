package com.backend.springboot.docker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.docker.dto.PersonDTO;
import com.backend.springboot.docker.model.Person;
import com.backend.springboot.docker.repository.PersonRepository;
import com.backend.springboot.docker.service.PersonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository repository;

	@Override
	public PersonDTO save(final Person person) {
		try {
			final Person personEntity = repository.save(person);
			return PersonDTO.mapFromEntity(personEntity);
       } catch (Exception e) {
    	   	log.error(e.getMessage());
    	   	throw e;
       }
	}
}
