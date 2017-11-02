package com.example.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;

import javax.naming.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.service.PersonService;
import com.example.service.PersonServiceImpl;
import com.example.service.TokenAuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter{
	
	public JWTLoginFilter(String url, AuthenticationManager authManager) {
	    super(new AntPathRequestMatcher(url));
	    setAuthenticationManager(authManager);
	  }

	  @Override
	  public Authentication attemptAuthentication(
	      HttpServletRequest req, HttpServletResponse res)
	      throws IOException, ServletException {

	  AccountCredentials creds = new ObjectMapper()
	        .readValue(req.getInputStream(), AccountCredentials.class);
	    return getAuthenticationManager().authenticate(
	        new UsernamePasswordAuthenticationToken(
	            creds.getUsername(),
	            creds.getPassword(),
	            Collections.emptyList()
	        )
	    );
	  }

	  @Override
	  protected void successfulAuthentication(
	      HttpServletRequest req,
	      HttpServletResponse res, FilterChain chain,
	      Authentication auth) throws IOException, ServletException {
	     TokenAuthenticationService
	        .addAuthentication(res, auth.getName());
	  }

}
