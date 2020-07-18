package com.backend.springboot.docker.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.backend.springboot.docker.model.Location;

public class LocationDTOTest {

	@Test
	public void testLocationDtoIsNotNull() {
		// Act & assert
		assertNotNull(new LocationDTO());
	}
	
	@Test
	public void testMapToEntityReturnsLocationEntity() {
		// Arrange
		final LocationDTO locationDto = new LocationDTO();
		locationDto.setCity("city");
		locationDto.setCountry("country");
		locationDto.setId(1);
		locationDto.setLatitude("latitude");
		locationDto.setLongitude("longitude");
		locationDto.setRegion("region");
		
		// Act
		final Location location = locationDto.mapToEntity();
		
		// Assert
		assertNotNull(location);
		assertEquals(locationDto.getCity(), location.getCity());
		assertEquals(locationDto.getCountry(), location.getCountry());
		assertEquals(locationDto.getId(), location.getId());
		assertEquals(locationDto.getLatitude(), location.getLatitude());
		assertEquals(locationDto.getLongitude(), location.getLongitude());
		assertEquals(locationDto.getRegion(), location.getRegion());
	}

}
