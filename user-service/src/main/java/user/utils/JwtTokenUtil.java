package user.utils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {

	// Requirement:
	public static final long JWT_TOKEN_VALIDITY = 1000 * 5 * 60 * 60;
	private final String SECRET = "secretkeythinsdnfdnenfaunerfnaerufnerofnuoaenfoanfuondofnajndviujneufnvaienfoUBEGFBEAWEFGHBEUWGFWEGFaegfwegwegwegeg";

	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
				.signWith(getSignKey(), SignatureAlgorithm.HS512).compact();
	}

	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userName);
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	// Validate token with
	public void validateToken(final String token) {
		Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
	}
}
