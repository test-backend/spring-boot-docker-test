package com.backend.springboot.docker.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.backend.springboot.docker.dto.LocationDTO;
import com.backend.springboot.docker.dto.PersonDTO;
import com.backend.springboot.docker.model.Person;
import com.backend.springboot.docker.service.PersonService;

@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest {
	
	@InjectMocks
	private PersonController personController;
	
	@Mock
	private PersonService personService;

	@Test
	public void testStatus() {
		// Act & assert
		assertEquals("OK", personController.status());
	}
	
	@Test
	public void testAddPersonReturnsPerson() throws Exception {
		// Arrange
		final PersonDTO personDto = new PersonDTO();
		personDto.setBirthday("1990-10-10");
		personDto.setId(null);
		personDto.setLocation(new LocationDTO());
		personDto.setName("name");
		
		Mockito.when(personService.save(Mockito.any(Person.class))).thenReturn(personDto);
		
		// Act
		final PersonDTO result = personController.addPerson(personDto);
		
		// Assert
		assertNotNull(result);
		assertEquals(personDto.getBirthday(), result.getBirthday());
		assertEquals(personDto.getId(), result.getId());
		assertEquals(personDto.getLocation(), result.getLocation());
		assertEquals(personDto.getName(), result.getName());
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddPersonThrowsNullPointerExceptionWhenNullPerson() throws Exception {
		// Act & assert
		personController.addPerson(null);
	}
	
}
