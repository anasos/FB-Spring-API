package com.example.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.model.Person;
import com.example.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	private PersonService personservice;
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@GetMapping("/user/{id}")
	public Person findPerson(@PathVariable("id") String id){
		Person person = personservice.find(id);
		return person;
	}
	
	@PostMapping("create")
	public Person add(@RequestBody Person person, UriComponentsBuilder builder) {
	   Person p = personservice.create(person);
	   return p;
	} 
	
	@PostMapping("/sign-up")
    public String signUp(@RequestBody Person user) {
        user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        Person u = personservice.create(user);
        return "yes";
    }
	
	
	@GetMapping("me")
	public Person me() {
		System.out.println("here");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Person current_user = personservice.findByUsername(currentPrincipalName);
		return current_user;
	}
	
	
	@PostMapping("/update_facebook_token")
	public String update_facebook_token(@RequestBody Person user) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Person current_user = personservice.findByUsername(currentPrincipalName);
		String facebookToken = user.getFacebookToken();
		personservice.updateFacebookToken(current_user, facebookToken);
		System.out.println("ghh");
		return "200";
	}
}
