package com.backend.springboot.docker.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
	
	@Test
	public void getPersonAndLocationByIdWorksThroughAllLayers() throws Exception {
		// Act & assert
		mockMvc.perform(get("/person/{id}", 3)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("3"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Name_3"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.location").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.location.id").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.location.city").isNotEmpty());
	}
	
	@Test
	public void getPersonsByLocationWorksThroughAllLayers() throws Exception {
		// Arrange
		final LocationDTO locationDto = new LocationDTO();
		locationDto.setCity("Madrid");
		locationDto.setCountry("Spain");

		// Act & assert
		mockMvc.perform(post("/person/findByLocation")
				.content(new Gson().toJson(locationDto))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].birthday").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].location").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].location.id").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].location.city").value("Madrid"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].location.region").value("Madrid"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].location.country").value("Spain"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].location.latitude").value("40°23′N"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].location.longitude").value("3°43′W"));

	}


}
