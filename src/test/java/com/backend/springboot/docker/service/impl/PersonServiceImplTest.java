package com.backend.springboot.docker.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.backend.springboot.docker.dto.LocationDTO;
import com.backend.springboot.docker.dto.PersonDTO;
import com.backend.springboot.docker.model.Location;
import com.backend.springboot.docker.model.Person;
import com.backend.springboot.docker.repository.LocationRepository;
import com.backend.springboot.docker.repository.PersonRepository;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest {

	@InjectMocks
	private PersonServiceImpl personService;

	@Mock
	private PersonRepository personRepository;

	@Mock
	private LocationRepository locationRepository;

	@Test
	public void testSaveReturnsPersonDto() {
		// Arrange
		final Person person = new Person();
		person.setBirthday(new Date(1L));
		person.setId(1);
		person.setLocation(new Location());
		person.setName("name");

		Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);

		// Act
		final PersonDTO result = personService.save(person);

		// Assert
		assertNotNull(result);
	}

	@Test(expected = RuntimeException.class)
	public void testSaveThrowsExceptionWhenRepositoryFails() {
		// Arrange
		Mockito.when(personRepository.save(Mockito.any(Person.class))).thenThrow(RuntimeException.class);

		// Act & assert
		personService.save(new Person());
	}

	@Test
	public void testFindByIdReturnsPersonDto() {
		// Arrange
		final Person person = new Person();
		person.setBirthday(new Date(1L));
		person.setId(3);
		person.setLocation(new Location());
		person.setName("name");

		Mockito.when(personRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(person));

		// Act
		final PersonDTO result = personService.findById(3);

		// Assert
		assertNotNull(result);
		assertEquals(person.getId(), result.getId());
		assertEquals(person.getName(), result.getName());
	}

	@Test
	public void testFindByIdReturnsNullIfPersonDoesNotExist() {
		// Arrange
		final Person person = new Person();
		person.setBirthday(new Date(1L));
		person.setId(3);
		person.setLocation(new Location());
		person.setName("name");

		Mockito.when(personRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

		// Act
		final PersonDTO result = personService.findById(3);

		// Assert
		assertNull(result);
	}

	@Test(expected = RuntimeException.class)
	public void testFindByIdThrowsExceptionWhenRepositoryFails() {
		// Arrange
		Mockito.when(personRepository.findById(Mockito.anyInt())).thenThrow(RuntimeException.class);

		// Act & assert
		personService.findById(3);
	}

	@Test
	public void testFindByLocationReturnsPersonDto() {
		// Arrange
		final Person person = new Person();
		person.setBirthday(new Date(1L));
		person.setId(3);
		person.setLocation(new Location());
		person.setName("name");
		
		final List<Person> personList = new ArrayList<Person>();
		personList.add(person);
		
		final Location location = new Location();
		location.setCity("city");
		location.setCountry("country");
		location.setId(1);
		location.setLatitude("latitude");
		location.setLongitude("longitude");
		location.setRegion("region");
		location.setPerson(personList);
		
		final List<Location> locationList = new ArrayList<Location>();
		locationList.add(location);

		Mockito.when(locationRepository.findByCityAndRegionAndCountryAndLatitudeAndLongitude(Mockito.anyString(),
				Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(locationList);

		// Act
		final List<PersonDTO> result = personService.findByLocation(location);
		
		// Assert
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(person.getName(), result.get(0).getName());
	}
	
	@Test
	public void testFindByNotPresentLocationReturnsEmptyList() {
		// Arrange
		final Location location = new Location();
		location.setCity("city");
		location.setCountry("country");
		location.setId(1);
		location.setLatitude("latitude");
		location.setLongitude("longitude");
		location.setRegion("region");
		
		final List<Location> locationList = new ArrayList<Location>();

		Mockito.when(locationRepository.findByCityAndRegionAndCountryAndLatitudeAndLongitude(Mockito.anyString(),
				Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(locationList);

		// Act
		final List<PersonDTO> result = personService.findByLocation(location);
		
		// Assert
		assertNotNull(result);
		assertEquals(0, result.size());
	}
	
	@Test(expected = NullPointerException.class)
	public void testFindByLocationThrowsExceptionWhenNullLocation() {
		// Act & assert
		personService.findByLocation(null);
	}
}
