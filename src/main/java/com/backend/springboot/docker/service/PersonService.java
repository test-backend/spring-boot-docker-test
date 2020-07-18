package com.backend.springboot.docker.service;

import java.util.List;

import com.backend.springboot.docker.dto.PersonDTO;
import com.backend.springboot.docker.model.Location;
import com.backend.springboot.docker.model.Person;

public interface PersonService {

	public PersonDTO save(final Person person);
	
	public PersonDTO findById(Integer id);
	
	public List<PersonDTO> findByLocation(Location location);

}
