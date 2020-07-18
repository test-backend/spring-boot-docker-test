package com.backend.springboot.docker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.springboot.docker.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

}
