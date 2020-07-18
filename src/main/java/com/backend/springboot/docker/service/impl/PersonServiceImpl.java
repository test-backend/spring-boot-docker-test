package com.backend.springboot.docker.service.impl;

import java.util.Optional;

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

	@Override
	public PersonDTO findById(Integer id) {
		try {
			PersonDTO personDto = null;
			final Optional<Person> optPersonEntity = repository.findById(id);
			if(optPersonEntity.isPresent()) {
				personDto = PersonDTO.mapFromEntity(optPersonEntity.get());
			}
			return personDto;
		} catch (Exception e) {
    	   	log.error(e.getMessage());
    	   	throw e;
       }
	}
}
