package com.backend.springboot.docker.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.springboot.docker.model.Location;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationRepositoryIntegrationTest {

	@Autowired
	LocationRepository repository;

	@Test
	public void testFindAllLocationShouldReturnNotEmptyList() {
		// Act
		Iterable<Location> locations = repository.findAll();

		// Assert
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByIdShouldReturnLocation() {
		// Arrange
		final Integer id = 1;

		// Act
		final Optional<Location> optLocation = repository.findById(id);

		// Assert
		assertNotNull(optLocation.get());
		assertEquals("Madrid", optLocation.get().getCity());
	}

	@Test
	public void testfindByCityAndRegionAndCountryAndLatitudeAndLongitudeShouldReturnLocation() {
		// Arrange
		final Location location = new Location();
		location.setCity("Madrid");
		location.setCountry("Spain");

		// Act
		final List<Location> locations = repository.findByCityAndRegionAndCountryAndLatitudeAndLongitude(
				location.getCity(), location.getRegion(), location.getCountry(), location.getLatitude(),
				location.getLongitude());

		// Assert
		assertNotNull(locations);
		assertEquals(1, locations.size());
		assertEquals("Madrid", locations.get(0).getCity());
		assertEquals("Madrid", locations.get(0).getRegion());
		assertEquals("Spain", locations.get(0).getCountry());
		assertEquals("40°23′N", locations.get(0).getLatitude());
		assertEquals("3°43′W", locations.get(0).getLongitude());
	}
	
	@Test
	public void testfindByCityAndRegionAndCountryAndLatitudeAndLongitudeShouldReturnEmpty() {
		// Arrange
		final Location location = new Location();
		location.setCity("Córdoba");

		// Act
		final List<Location> locations = repository.findByCityAndRegionAndCountryAndLatitudeAndLongitude(
				location.getCity(), location.getRegion(), location.getCountry(), location.getLatitude(),
				location.getLongitude());

		// Assert
		assertNotNull(locations);
		assertEquals(0, locations.size());
	}

}
