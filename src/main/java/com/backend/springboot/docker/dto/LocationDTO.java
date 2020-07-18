package com.backend.springboot.docker.dto;

import com.backend.springboot.docker.model.Location;

public class LocationDTO {
	
	private Integer id;
	
	private String city;
	
	private String region;
	
	private String country;
	
	private String latitude;
	
	private String longitude;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public Location mapToEntity() {
		final Location location = new Location();
		location.setCity(this.city);
		location.setCountry(this.country);
		location.setId(this.id);
		location.setLatitude(this.latitude);
		location.setLongitude(this.longitude);
		location.setRegion(this.region);
		return location;
	}
	
	public static LocationDTO mapFromEntity(final Location location) {
		final LocationDTO locationDTO = new LocationDTO();
		locationDTO.setCity(location.getCity());
		locationDTO.setCountry(location.getCountry());
		locationDTO.setId(location.getId());
		locationDTO.setLatitude(location.getLatitude());
		locationDTO.setLongitude(location.getLongitude());
		locationDTO.setRegion(location.getRegion());
		return locationDTO;
	}
}
