package com.jisr.security;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.jisr.entity.User;
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
	@Value("${jwt.refreshExpirationMs}")
	private int refreshExpirationMs;

	public JwtUtil(@Value("${jwt.secret}") String secret) {
		this.jwtSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
	}

	public String generateAccessToken(User user, String emailOrPhone) {
		List<String> roles = user.getRoles().stream()
                .map(role -> "ROLE_" + role.getName())
                .collect(Collectors.toList()); 
		Map<String, Object> headers = new HashMap<>();
	    headers.put("typ", "JWT");
	    return Jwts.builder()
	    			.header().add("typ", "JWT").and()
	    			.claim("id", user.getId())
	    			.claim("roles", roles)
	    			.subject(emailOrPhone)
	    			.issuedAt(new Date())
	    			.expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
	    			.signWith(jwtSecret)
	    			.compact();
	}
	
	public String generateRefreshToken(User user, String emailOrPhone) {
		List<String> roles = user.getRoles().stream()
                .map(role -> "ROLE_" + role.getName())
                .collect(Collectors.toList()); 
        return Jwts.builder()
	    		   	.header().add("typ", "JWT").and()
	    		   	.claim("id", user.getId())
	    		   	.claim("roles", roles)
	    		   	.claim("token_type", "refresh")
	    		   	.subject(emailOrPhone)
	    		   	.issuedAt(new Date())
	    		   	.expiration(new Date(System.currentTimeMillis() + refreshExpirationMs))
	    		   	.signWith(jwtSecret)
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

	public String getSubjectFromToken(String token) {
		Claims claims = getJwtParser().parseSignedClaims(token).getPayload();
		return claims.getSubject();
	}

	private JwtParser getJwtParser() {
		return Jwts.parser().verifyWith(jwtSecret).build();
	}
	
	public Claims getClaimsFromToken(String token) {
		return getJwtParser().parseSignedClaims(token).getPayload();
	}

}
