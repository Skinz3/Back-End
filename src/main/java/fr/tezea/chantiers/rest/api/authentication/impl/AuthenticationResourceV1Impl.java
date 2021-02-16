package fr.tezea.chantiers.rest.api.authentication.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;

import fr.tezea.chantiers.rest.api.authentication.api.AuthenticationResourceV1;
import fr.tezea.chantiers.security.models.AuthenticationRequest;
import fr.tezea.chantiers.security.models.AuthenticationResponse;
import fr.tezea.chantiers.security.services.MyUserDetailsService;
import fr.tezea.chantiers.security.util.JwtUtil;

public class AuthenticationResourceV1Impl implements AuthenticationResourceV1{
	
	@Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

	@Override
    public ResponseEntity<?> createAuthentication(@RequestBody AuthenticationRequest authenticationRequest)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                authenticationRequest.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
