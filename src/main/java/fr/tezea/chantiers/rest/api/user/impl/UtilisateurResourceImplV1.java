package fr.tezea.chantiers.rest.api.user.impl;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.tezea.chantiers.rest.api.user.api.UtilisateurResourceV1;
import fr.tezea.chantiers.service.UtilisateurService;
import fr.tezea.chantiers.service.dto.user.UtilisateurDTO;


@RestController
public class UtilisateurResourceImplV1 implements UtilisateurResourceV1{
	private final UtilisateurService utilisateurService;

    public UtilisateurResourceImplV1(UtilisateurService utilisateurService)
    {
        super();
        this.utilisateurService = utilisateurService;
    }

    @Override
    public ResponseEntity<UtilisateurDTO> getUtilisateurById(@PathVariable("id") long id)
    {
        return ResponseEntity.ok(this.utilisateurService.getUtilisateurById(id));
    }

    @Override
    public ResponseEntity<URI> addUtilisateur(@RequestBody UtilisateurDTO utilisateurDTO)
    {
        URI location = URI.create(String.format("/get/%s", this.utilisateurService.addUtilisateur(utilisateurDTO)));
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<UtilisateurDTO> updateUtilisateurById(@PathVariable("id") long id, @RequestBody UtilisateurDTO utilisateurDTO)
    {
        return ResponseEntity.ok(this.utilisateurService.updateUtilisateurById(id, utilisateurDTO));
    }

    @Override
    public ResponseEntity<Void> deleteUtilisateurById(@PathVariable("id") long id)
    {
        this.utilisateurService.deleteUtilisateurById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
