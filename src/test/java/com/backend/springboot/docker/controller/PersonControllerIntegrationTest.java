package com.backend.springboot.docker.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.backend.springboot.docker.dto.LocationDTO;
import com.backend.springboot.docker.dto.PersonDTO;
import com.backend.springboot.docker.service.PersonService;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PersonService personService;

	@Test
	public void addPersonWorksThroughAllLayers() throws Exception {
		// Arrange
		final LocationDTO locationDto = new LocationDTO();
		locationDto.setCity("city");
		locationDto.setCountry("country");
		locationDto.setId(null);
		locationDto.setLatitude("latitude");
		locationDto.setLongitude("longitude");
		locationDto.setRegion("region");

		final PersonDTO personDto = new PersonDTO();
		personDto.setBirthday("1990-10-10");
		personDto.setId(null);
		personDto.setLocation(locationDto);
		personDto.setName("name");

		// Act & assert
		mockMvc.perform(post("/person")
				.content(new Gson().toJson(personDto))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("name"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.birthday").value("1990-10-10"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.location").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.location.id").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.location.city").value("city"));

	}

}