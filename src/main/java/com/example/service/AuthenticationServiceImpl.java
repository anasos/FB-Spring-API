package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.model.Authentication;
import com.example.model.Person;

public class AuthenticationServiceImpl{

	@Autowired
    PersonService userService;
    
}
