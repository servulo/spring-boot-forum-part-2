package br.com.sprj.forum.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.sprj.forum.modelo.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
    
    @Value("${forum.jwt.expiration}")
    private String expiration;
    
    @Value("${forum.jwt.secret}")
    private String secret;      

    public String generateToken(Authentication authentication) {
	
	User logged = (User) authentication.getPrincipal();
	Date today = new Date();
	Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));
	return Jwts.builder()
		.setIssuer("Forum API")
		.setSubject(logged.getId().toString())
		.setIssuedAt(today)
		.setExpiration(expirationDate)
		.signWith(SignatureAlgorithm.HS256, secret)
		.compact();
    }

}
