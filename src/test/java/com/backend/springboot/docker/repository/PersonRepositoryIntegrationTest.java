package com.backend.springboot.docker.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.springboot.docker.model.Location;
import com.backend.springboot.docker.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryIntegrationTest {

	@Autowired
	PersonRepository repository;

	@Test
	public void testFindAllPersonShouldReturnNotEmptyList() {
		// Act
		Iterable<Person> persons = repository.findAll();

		// Assert
		assertThat(persons).isNotEmpty();
	}

	@Test
	public void testFindByIdShouldReturnPerson() {
		// Arrange
		final Integer id = 1;

		// Act
		final Optional<Person> optPerson = repository.findById(id);

		// Assert
		assertNotNull(optPerson.get());
		assertEquals("Name_1", optPerson.get().getName());
	}

	@Test
	public void testSaveShouldSaveAPerson() {
		// Arrange
		final Location location = new Location();
		location.setCity("cityTest");
		location.setCountry("countryTest");
		location.setLatitude("latitudeTest");
		location.setLongitude("longitudeTest");
		location.setRegion("regionTest");

		final Person person = new Person();
		person.setBirthday(new Date(1L));
		person.setLocation(location);
		person.setName("test");

		// Act
		final Person result = repository.save(person);

		// Assert
		assertNotNull(result);
		assertThat(result).hasFieldOrPropertyWithValue("name", "test");
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void testSaveShouldFailAsDuplicateLocation() {
		// Arrange
		final Location location = new Location();
		location.setCity("cityTest");
		location.setCountry("countryTest");
		location.setLatitude("latitudeTest");
		location.setLongitude("longitudeTest");
		location.setRegion("regionTest");

		final Person person = new Person();
		person.setBirthday(new Date(1L));
		person.setLocation(location);
		person.setName("test2");
		
		final Person person2 = new Person();
		person.setBirthday(new Date(1L));
		person.setLocation(location);
		person.setName("test22");

		// Act
		repository.save(person);
		repository.save(person2);
	}

}
