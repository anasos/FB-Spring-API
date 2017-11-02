package com.example.service;

import org.bson.types.ObjectId;

import com.example.model.Authentication;
import com.example.model.Person;

public interface PersonService {
	
	public Person find(String id);
	
	public Person create(Person person);
	
	public Person findByUsername(String username);
	
	public Person updateFacebookToken(Person current_person, String facebookToken);

}
