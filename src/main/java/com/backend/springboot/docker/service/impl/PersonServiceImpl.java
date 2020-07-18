package com.backend.springboot.docker.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.docker.dto.PersonDTO;
import com.backend.springboot.docker.model.Location;
import com.backend.springboot.docker.model.Person;
import com.backend.springboot.docker.repository.LocationRepository;
import com.backend.springboot.docker.repository.PersonRepository;
import com.backend.springboot.docker.service.PersonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Override
	public PersonDTO save(final Person person) {
		try {
			final Person personEntity = personRepository.save(person);
			return PersonDTO.mapFromEntity(personEntity);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public PersonDTO findById(final Integer id) {
		try {
			PersonDTO personDto = null;
			final Optional<Person> optPersonEntity = personRepository.findById(id);
			if (optPersonEntity.isPresent()) {
				personDto = PersonDTO.mapFromEntity(optPersonEntity.get());
			}
			return personDto;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public List<PersonDTO> findByLocation(final Location location) {
		try {
			final List<Location> locationList = locationRepository.findByCityAndRegionAndCountryAndLatitudeAndLongitude(
					location.getCity(), 
					location.getRegion(), 
					location.getCountry(), 
					location.getLatitude(),
					location.getLongitude());
			return locationList.stream()
					.map(loc -> loc.getPerson())
					.flatMap(persons -> persons.stream())
					.map(person -> PersonDTO.mapFromEntity(person))
					.collect(Collectors.toList());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

}
