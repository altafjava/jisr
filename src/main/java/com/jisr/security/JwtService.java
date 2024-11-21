package com.jisr.security;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.jisr.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	private static final String TOKEN_TYPE_ACCESS = "access_token";
	private static final String TOKEN_TYPE_REFRESH = "refresh_token";
	private static final String JWT_TYPE = "JWT";
	private final SecretKey secretKey;
	
	@Value("${jwt.expirationMs}")
	private int jwtExpirationMs;
	@Value("${jwt.refreshExpirationMs}")
	private int refreshExpirationMs;

	public JwtService(@Value("${jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

	public String generateAccessToken(User user, String emailOrPhone) {
		return generateToken(user, emailOrPhone, TOKEN_TYPE_ACCESS, jwtExpirationMs);
	}

	public String generateRefreshToken(User user, String emailOrPhone) {
		return generateToken(user, emailOrPhone, TOKEN_TYPE_REFRESH, refreshExpirationMs);
	}

	public boolean validateToken(String token) {
		try {
			getJwtParser().parseSignedClaims(token).getPayload();
			return true;
		} catch (JwtException e) {
			return false;
		}
	}

	public String getSubjectFromToken(String token) {
		return getClaimsFromToken(token).getSubject();
	}

	public Claims getClaimsFromToken(String token) {
		return getJwtParser().parseSignedClaims(token).getPayload();
	}

	private String generateToken(User user, String subject, String tokenType, int expirationMs) {
        List<String> roles = mapRoles(user);
        return Jwts.builder()
        		.header().type(JWT_TYPE).and()
                .claim("id", user.getId())
                .claim("roles", roles)
                .claim("token_type", tokenType)
                .subject(subject)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(expirationMs)))
                .signWith(secretKey)
                .compact();
    }

	private JwtParser getJwtParser() {
		return Jwts.parser().verifyWith(secretKey).build();
	}

	private List<String> mapRoles(User user) {
		return user.getRoles().stream().map(role -> "ROLE_" + role.getName()).collect(Collectors.toList());
	}
}
