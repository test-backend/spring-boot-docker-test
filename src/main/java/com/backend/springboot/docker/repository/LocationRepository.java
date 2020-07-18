package com.backend.springboot.docker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.springboot.docker.model.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {

	@Query("SELECT l FROM Location l " 
			+ "WHERE (:city is null or l.city = :city) "
			+ "AND (:region is null or l.region = :region)"
			+ "AND (:country is null or l.country = :country)"
			+ "AND (:latitude is null or l.latitude = :latitude)"
			+ "AND (:longitude is null or l.longitude = :longitude)")
	List<Location> findByCityAndRegionAndCountryAndLatitudeAndLongitude(String city, String region, String country,
			String latitude, String longitude);

}
