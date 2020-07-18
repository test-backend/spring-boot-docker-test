package com.backend.springboot.docker.dto;

import java.text.SimpleDateFormat;

import com.backend.springboot.docker.model.Person;

public class PersonDTO {
	
	private Integer id;
	
	private String name;
	
	private String birthday;
	
	private LocationDTO location;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public LocationDTO getLocation() {
		return location;
	}

	public void setLocation(LocationDTO location) {
		this.location = location;
	}
	
	public Person mapToEntity() throws Exception {
		
		final Person person = new Person();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateBirthday = sdf.parse(this.birthday);
		java.sql.Date sqlBirthday = new java.sql.Date(dateBirthday.getTime()); 
		
		person.setBirthday(sqlBirthday);
		person.setId(this.id);
		person.setLocation(this.location.mapToEntity());
		person.setName(this.name);
		
		return person;
	}
	
	public static PersonDTO mapFromEntity(final Person person) {
		final PersonDTO personDto = new PersonDTO();
		personDto.setBirthday(person.getBirthday().toString());
		personDto.setId(person.getId());
		personDto.setLocation(LocationDTO.mapFromEntity(person.getLocation()));
		personDto.setName(person.getName());
		return personDto;
	}
}
