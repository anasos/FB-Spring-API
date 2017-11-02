package com.example.service;

import static java.util.Collections.emptyList;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.Authentication;
import com.example.model.Person;
import com.example.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService, UserDetailsService{

	@Autowired
	private PersonRepository personRepository;
	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Person find(String id) {
		return personRepository.findOne(id);
	}

	@Override
	public Person create(Person person) {
		//person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
		return personRepository.save(person);
	}
	
	@Override
	public Person findByUsername(String username) {
		return personRepository.findByUsername(username);
	}
	
	@Override
	public Person updateFacebookToken(Person current_person, String facebookToken) {
//		Person p = personRepository.findByUsername(current_person.getUsername());
		current_person.setFacebookToken(facebookToken);
		return personRepository.save(current_person);
	}
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Person user = personRepository.findByUsername(name);
		String password = user != null ? user.getPassword() : ""; 
        return new org.springframework.security.core.userdetails.User(name, password, emptyList());
	}
	
	
}
