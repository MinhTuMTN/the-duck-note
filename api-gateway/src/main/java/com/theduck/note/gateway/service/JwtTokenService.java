package com.theduck.note.gateway.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.theduck.note.gateway.dto.AuthenticationRequest;
import com.theduck.note.gateway.util.JwtUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JwtTokenService {

    private AuthenticationManager authenticationManager;

    private CustomUserDetailsService userDetailsService;

    private JwtUtils jwtUtil;

    public String createJwtToken(AuthenticationRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                authenticationRequest.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        return jwtUtil.generateToken(userDetails);
    }
}
