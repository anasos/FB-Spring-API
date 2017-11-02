package com.example.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.model.Person;
import com.example.repository.PersonRepository;
import com.google.gson.JsonObject;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static java.util.Collections.emptyList;

import java.io.IOException;;

@Service
public class TokenAuthenticationService {
	
	static final long EXPIRATIONTIME = 864_000_000; // 10 days
	  static final String SECRET = "ThisIsASecret";
	  static final String TOKEN_PREFIX = "Bearer";
	  static final String HEADER_STRING = "Authorization";

	  public static void addAuthentication(HttpServletResponse res, String username) throws IOException {
	    String JWT = Jwts.builder()
	        .setSubject(username)
	        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
	        .signWith(SignatureAlgorithm.HS512, SECRET)
	        .compact();
	    res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	    res.addHeader("accept","application/json");

	    String message;
	    JsonObject json = new JsonObject();
		json.addProperty("token", TOKEN_PREFIX + " " + JWT);
				
	    message = json.toString();

	    res.getWriter().print(message);

	    res.getWriter().flush();
	    res.getWriter().close();
	  }

	  public static Authentication getAuthentication(HttpServletRequest request) {
	    String token = request.getHeader(HEADER_STRING);
	    if (token != null) {
	      // parse the token.
	      String user = Jwts.parser()
	          .setSigningKey(SECRET)
	          .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
	          .getBody()
	          .getSubject();

	      return user != null ?
	          new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
	          null;
	    }
	    return null;
	  }
}
