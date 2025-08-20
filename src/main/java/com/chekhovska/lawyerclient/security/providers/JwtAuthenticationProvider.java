package com.chekhovska.lawyerclient.security.providers;

import com.chekhovska.lawyerclient.security.jwt.JwtTool;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtTool jwtTool;

    public JwtAuthenticationProvider(JwtTool jwtTool) {
        this.jwtTool = jwtTool;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        String token = (String) authentication.getPrincipal();
        SecretKey key = Keys.hmacShaKeyFor(jwtTool.getAccessTokenKey().getBytes());

        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        Claims claims = claimsJws.getBody();

        String email = claims.getSubject();
        @SuppressWarnings({"unchecked, rawtype"})
        List<String> authorities = (List<String>) claims.get("role");

        return new UsernamePasswordAuthenticationToken(
                email,
                "",
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

