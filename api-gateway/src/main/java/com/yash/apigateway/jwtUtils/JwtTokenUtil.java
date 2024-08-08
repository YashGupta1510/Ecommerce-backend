package com.yash.apigateway.jwtUtils;

import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {

	// Requirement:
	public static final long JWT_TOKEN_VALIDITY = 1000 * 5 * 60 * 60;
	private final String SECRET = "secretkeythinsdnfdnenfaunerfnaerufnerofnuoaenfoanfuondofnajndviujneufnvaienfoUBEGFBEAWEFGHBEUWGFWEGFaegfwegwegwegeg";
	
	public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
