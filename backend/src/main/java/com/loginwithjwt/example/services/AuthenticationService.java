package com.loginwithjwt.example.services;

import com.loginwithjwt.example.dto.JwtRequest;
import com.loginwithjwt.example.dto.JwtResponse;
import com.loginwithjwt.example.security.JwtHelper;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    public ResponseEntity<JwtResponse> authenticateUser(JwtRequest jwtRequest){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        UserDetails user = (UserDetails) authentication.getPrincipal();
        UserDetails user = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String jwtToken = jwtHelper.generateToken(user.getUsername());
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setUsername(user.getUsername());
        jwtResponse.setJwtToken(jwtToken);
//        System.out.println(jwtToken);
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }
}
