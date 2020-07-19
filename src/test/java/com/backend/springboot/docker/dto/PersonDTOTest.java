package com.backend.springboot.docker.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Date;
import java.text.ParseException;

import org.junit.Test;

import com.backend.springboot.docker.model.Location;
import com.backend.springboot.docker.model.Person;

public class PersonDTOTest {

	@Test
	public void testPersonDtoIsNotNull() {
		// Act & assert
		assertNotNull(new PersonDTO());
	}

	@Test
	public void testMapToEntityReturnsPersonEntity() throws Exception {
		// Arrange
		final PersonDTO personDto = new PersonDTO();
		personDto.setBirthday("1990-10-10");
		personDto.setId(1);
		personDto.setLocation(new LocationDTO());
		personDto.setName("name");
		
		// Act
		final Person person = personDto.mapToEntity();
		
		// Assert
		assertNotNull(person);
		assertEquals(personDto.getBirthday(), person.getBirthday().toString());
		assertEquals(personDto.getId(), person.getId());
		assertNotNull(person.getLocation());
		assertEquals(personDto.getName(), person.getName());
	}
	
	@Test(expected = ParseException.class)
	public void testMapToEntityThrowsParseExceptionWhenInvalidDate() throws Exception {
		// Arrange
		final PersonDTO personDto = new PersonDTO();
		personDto.setBirthday("aaa");
		personDto.setId(1);
		personDto.setLocation(new LocationDTO());
		personDto.setName("name");
		
		// Act & assert
		personDto.mapToEntity();
	}
	
	@Test
	public void testMapToEntityWithNullLocationReturnsEntityWithNullLocation() throws Exception {
		// Arrange
		final PersonDTO personDto = new PersonDTO();
		personDto.setBirthday("1990-10-10");
		personDto.setId(1);
		personDto.setLocation(null);
		personDto.setName("name");
		
		// Act
		final Person person = personDto.mapToEntity();
		
		// Assert
		assertNotNull(person);
		assertNull(person.getLocation());
	}
	
	@Test
	public void testMapFromEntityReturnsPersonDto() {
		// Arrange
		final Person person = new Person();
		person.setBirthday(new Date(1L));
		person.setId(1);
		person.setLocation(new Location());
		person.setName("name");
		
		// Act
		final PersonDTO personDto = PersonDTO.mapFromEntity(person);
		
		// Assert
		assertNotNull(personDto);
		assertEquals(person.getBirthday().toString(), personDto.getBirthday());
		assertEquals(person.getId(), personDto.getId());
		assertNotNull(person.getLocation());
		assertEquals(person.getName(), personDto.getName());
	}
	
	@Test
	public void testMapFromEntityWithNullLocationReturnsPersonDtoWithNullLocation() {
		// Arrange
		final Person person = new Person();
		person.setBirthday(new Date(1L));
		person.setId(1);
		person.setLocation(null);
		person.setName("name");
		
		// Act
		final PersonDTO personDto = PersonDTO.mapFromEntity(person);
		
		// Assert
		assertNotNull(personDto);
		assertNull(person.getLocation());
	}
	
	@Test(expected = NullPointerException.class)
	public void testMapFromEntityThrowsNullPointerExceptionIfNullEntity() {
		// Act & assert
		PersonDTO.mapFromEntity(null);
	}
	
}
