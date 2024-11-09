package com.jisr.security;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtUtil {

	private final SecretKey jwtSecret;
	@Value("${jwt.expirationMs}")
	private int jwtExpirationMs;

	public JwtUtil(@Value("${jwt.secret}") String secret) {
		this.jwtSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
	}

	public String generateToken(String username) {
		return Jwts.builder().subject(username).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + jwtExpirationMs)).signWith(jwtSecret)
				.compact();
	}

	public boolean validateToken(String token) {
		try {
			getJwtParser().parseSignedClaims(token).getPayload();
			return true;
		} catch (ExpiredJwtException e) {
			return false;
		} catch (SignatureException e) {
			return false;
		}
	}

	public String getUsernameFromToken(String token) {
		Claims claims = getJwtParser().parseSignedClaims(token).getPayload();
		return claims.getSubject();
	}

	private JwtParser getJwtParser() {
		return Jwts.parser().verifyWith(jwtSecret).build();
	}
}
