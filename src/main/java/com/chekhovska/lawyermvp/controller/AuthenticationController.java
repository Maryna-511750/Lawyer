package com.chekhovska.lawyermvp.controller;

import com.chekhovska.lawyermvp.config.JwtTokenProvider;
import com.chekhovska.lawyermvp.dto.AuthenticationRequest;
import com.chekhovska.lawyermvp.dto.AuthenticationResponse;
import com.chekhovska.lawyermvp.exception.AuthenticationFailureException;
import com.chekhovska.lawyermvp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtToken;
    @Autowired
    private UserService userServiceImpl;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userServiceImpl.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtToken.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
