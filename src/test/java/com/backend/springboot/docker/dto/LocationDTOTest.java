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
	
	@Test
	public void testMapFromEntityReturnsLocationDto() {
		// Arrange
		final Location location = new Location();
		location.setCity("city");
		location.setCountry("country");
		location.setId(1);
		location.setLatitude("latitude");
		location.setLongitude("longitude");
		location.setRegion("region");
		
		// Act
		final LocationDTO locationDto = LocationDTO.mapFromEntity(location);
		
		// Assert
		assertNotNull(locationDto);
		assertEquals(location.getCity(), locationDto.getCity());
		assertEquals(location.getCountry(), locationDto.getCountry());
		assertEquals(location.getId(), locationDto.getId());
		assertEquals(location.getLatitude(), locationDto.getLatitude());
		assertEquals(location.getLongitude(), locationDto.getLongitude());
		assertEquals(location.getRegion(), locationDto.getRegion());
	}
	
	@Test(expected = NullPointerException.class)
	public void testMapFromEntityThrowsNullPointerExceptionIfNullEntity() {
		// Act & assert
		LocationDTO.mapFromEntity(null);
	}

}
