package com.backend.springboot.docker.service.impl;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.backend.springboot.docker.dto.PersonDTO;
import com.backend.springboot.docker.model.Location;
import com.backend.springboot.docker.model.Person;
import com.backend.springboot.docker.repository.PersonRepository;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest {
	
	@InjectMocks
	private PersonServiceImpl personService;
	
	@Mock
	private PersonRepository repository;

	@Test
	public void testSaveReturnsPersonDto() {
		// Arrange
		final Person person = new Person();
		person.setBirthday(new Date(1L));
		person.setId(1);
		person.setLocation(new Location());
		person.setName("name");
		
		Mockito.when(repository.save(Mockito.any(Person.class))).thenReturn(person);
		
		// Act
		final PersonDTO result = personService.save(person);
		
		// Assert
		assertNotNull(result);
	}
	
	@Test(expected = RuntimeException.class)
	public void testSaveThrowsExceptionWhenRepositoryFails() {
		// Arrange
		Mockito.when(repository.save(Mockito.any(Person.class))).thenThrow(RuntimeException.class);
		
		// Act & assert
		personService.save(new Person());
	}

}