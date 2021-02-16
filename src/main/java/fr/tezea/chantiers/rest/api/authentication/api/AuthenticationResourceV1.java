package fr.tezea.chantiers.rest.api.authentication.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.tezea.chantiers.security.models.AuthenticationRequest;

@RequestMapping("/authentification")
public interface AuthenticationResourceV1 {

	@PostMapping()
    public ResponseEntity<?> createAuthentication(
            @RequestBody AuthenticationRequest authenticationRequest) /* throws Exception */;
}
