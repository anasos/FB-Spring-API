package com.example.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.model.Authentication;

public interface AuthenticationService {
    Authentication loadUserByUsername(String var1) throws UsernameNotFoundException;
}
