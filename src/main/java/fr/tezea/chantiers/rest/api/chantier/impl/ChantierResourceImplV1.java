/*
 * MIT License
 *
 * Copyright (c) 2021 TEZEA-Chantiers
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package fr.tezea.chantiers.rest.api.chantier.impl;

import fr.tezea.chantiers.rest.api.chantier.api.ChantierResourceV1;
import fr.tezea.chantiers.security.models.AuthenticationRequest;
import fr.tezea.chantiers.security.models.AuthenticationResponse;
import fr.tezea.chantiers.security.services.MyUserDetailsService;
import fr.tezea.chantiers.security.util.JwtUtil;
import fr.tezea.chantiers.service.ChantierService;
import fr.tezea.chantiers.service.dto.chantier.ChantierDTO;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChantierResourceImplV1 implements ChantierResourceV1
{
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    private final ChantierService chantierService;

    public ChantierResourceImplV1(ChantierService chantierService)
    {
        super();
        this.chantierService = chantierService;
    }

    @Override
    public ResponseEntity<ChantierDTO> getChantierById(@PathVariable("id") long id)
    {
        return ResponseEntity.ok(this.chantierService.getChantierById(id));
    }

    @Override
    public ResponseEntity<URI> addChantier(@RequestBody ChantierDTO chantierDTO)
    {
        URI location = URI.create(String.format("/get/%s", this.chantierService.addChantier(chantierDTO)));
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<ChantierDTO> updateChantierById(@PathVariable("id") long id,
            @RequestBody ChantierDTO chantierDTO)
    {
        return ResponseEntity.ok(this.chantierService.updateChantierById(id, chantierDTO));
    }

    @Override
    public ResponseEntity<Void> deleteChantierById(@PathVariable("id") long id)
    {
        this.chantierService.deleteChantierById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

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
