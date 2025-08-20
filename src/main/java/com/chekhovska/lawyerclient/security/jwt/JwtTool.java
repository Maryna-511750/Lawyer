package com.chekhovska.lawyerclient.security.jwt;

import com.chekhovska.lawyerclient.dto.UserVO;
import com.chekhovska.lawyerclient.model.Role;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
@Component
public class JwtTool {
    private final Integer accessTokenValidTimeInMinutes;
    private final Integer refreshTokenValidTimeInMinutes;
    private final String accessTokenKey;

    @Autowired
    public JwtTool(@Value("${accessTokenValidTimeInMinutes}") Integer accessTokenValidTimeInMinutes,
                   @Value("${refreshTokenValidTimeInMinutes}") Integer refreshTokenValidTimeInMinutes,
                   @Value("${tokenKey}") String accessTokenKey) {
        this.accessTokenValidTimeInMinutes = accessTokenValidTimeInMinutes;
        this.refreshTokenValidTimeInMinutes = refreshTokenValidTimeInMinutes;
        this.accessTokenKey = accessTokenKey;
    }

    public String createAccessToken(String email, Role role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", email);
        claims.put("role", Collections.singleton(role.getName()));

        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MINUTE, accessTokenValidTimeInMinutes);
        return io.jsonwebtoken.Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(calendar.getTime())
                .signWith(Keys.hmacShaKeyFor(
                        accessTokenKey.getBytes(StandardCharsets.UTF_8)), io.jsonwebtoken.SignatureAlgorithm.HS256)
                .compact();
    }

    public String createRefreshToken(UserVO user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", user.getEmail());
        claims.put("role", Collections.singleton(user.getRole()));

        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MINUTE, refreshTokenValidTimeInMinutes);
        return io.jsonwebtoken.Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(calendar.getTime())
                .signWith(
                        Keys.hmacShaKeyFor(user.getRefreshTokenKey().getBytes(StandardCharsets.UTF_8)),
                        io.jsonwebtoken.SignatureAlgorithm.HS256)
                .compact();
    }

    public String getEmailOutOfAccessToken(String token) {
        String[] splitToken = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(splitToken[1]));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(payload);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON payload", e);
        }
        return jsonNode.path("sub").asText();
    }

    public boolean isTokenValid(String token, String tokenKey) {
        boolean isValid = false;
        SecretKey key = Keys.hmacShaKeyFor(tokenKey.getBytes());
        try {
            io.jsonwebtoken.Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            isValid = true;
        } catch (Exception e) {
            log.info("Given token is not valid: " + e.getMessage());
            log.error("Exception:", e);
            log.info("token " + token + "token key " + tokenKey);
        }
        return isValid;
    }

    public String getAccessTokenKey() {
        return accessTokenKey;
    }

    public String getTokenFromHttpServletRequest(HttpServletRequest servletRequest) {
        return Optional
                .ofNullable(servletRequest.getHeader("Authorization"))
                .filter(authHeader -> authHeader.startsWith("Bearer "))
                .map(token -> token.substring(7))
                .orElse(null);
    }

    public String generateTokenKey() {
        return UUID.randomUUID().toString();
    }

    public String generateTokenKeyWithCodedDate() {
        Date date = new Date();
        Long dateLong = date.getTime();
        dateLong += 86400000L;
        String input = dateLong + "." + UUID.randomUUID().toString();
        return Base64.getEncoder().encodeToString(input.getBytes());
    }
}