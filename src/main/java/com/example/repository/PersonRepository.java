package com.example.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.model.Person;

public interface PersonRepository extends MongoRepository<Person, String>{
	
	public Person findByUsername(String username);

}
