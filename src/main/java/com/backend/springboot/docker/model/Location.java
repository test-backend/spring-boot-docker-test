package com.backend.springboot.docker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "LOCATION", 
		indexes = { @Index(name = "idx_location", columnList = "LATITUDE, LONGITUDE") }, 
		uniqueConstraints = { @UniqueConstraint(columnNames = { "CITY", "REGION", "COUNTRY", "LATITUDE", "LONGITUDE" }) 
	})
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "CITY", nullable = false)
	private String city;

	@Column(name = "REGION", nullable = false)
	private String region;

	@Column(name = "COUNTRY", nullable = false)
	private String country;

	@Column(name = "LATITUDE", nullable = false)
	private String latitude;

	@Column(name = "LONGITUDE", nullable = false)
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

}
