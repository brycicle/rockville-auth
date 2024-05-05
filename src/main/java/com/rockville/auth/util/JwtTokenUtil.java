package com.rockville.auth.util;

import com.rockville.auth.model.dto.UserDetailsDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public final class JwtTokenUtil implements Serializable {
    public static final long JWT_TOKEN_VALIDITY = 900 * 60 * 60;
    private static final long serialVersionUID = 42L;

    @Value("${jwt.secret}")
    private String secret;
    private Key key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String getUsernameFromToken(final String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(final String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(final String token, final Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(final String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(final String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(final UserDetailsDto userJson) {
        final Map<String, Object> claims = new HashMap<>();
        claims.put("uuid", userJson.getId());
        claims.put("firstName", userJson.getFirstName());
        claims.put("middleName", userJson.getMiddleName());
        claims.put("lastName", userJson.getLastName());
        claims.put("roles", userJson.getUser().getAuthorities());
        claims.put("sub", userJson.getUser().getUsername());
        return doGenerateToken(claims, userJson.getUsername());
    }
    private String doGenerateToken(final Map<String, Object> claims, final String subject) {
        final int millisToSeconds = 1000;
        return Jwts
                .builder()
                .setSubject(subject)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * millisToSeconds))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS512)
                .compact();
    }

    public Boolean validateToken(final String token, final UserDetailsDto userDetails) {
        final String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUser().getUsername()) && !isTokenExpired(token);
    }
}
