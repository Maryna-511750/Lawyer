package com.chekhovska.lawyerclient.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    //@Value("${myjwttoken.app.jwtSecret}")
    private String jwtSecret = "stringSecretKey"; //stringSecretKeyThatIsGreaterThanTheKeySpecifiedInThePresentationOrTheSame";
    //@Value("${myjwttoken.app.jwtExpirationMs}")
    private int jwtExpirationMs = 86400000;

    public String generateToken(UserDetails userDetails) {
        List<String> roles = userDetails.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toList());
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", roles)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(key)
                .compact();
    }
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: {}" + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: {}" + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: {}" + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: {}" + e.getMessage());
        } catch (SignatureException e) {
            System.out.println("Signature validation failed" + e);
        }
        return false;
    }
    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }
    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }
}
