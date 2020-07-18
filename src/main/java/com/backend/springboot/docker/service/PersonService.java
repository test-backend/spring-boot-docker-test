package com.backend.springboot.docker.service;

import com.backend.springboot.docker.dto.PersonDTO;
import com.backend.springboot.docker.model.Person;

public interface PersonService {

	public PersonDTO save(final Person person);

}
