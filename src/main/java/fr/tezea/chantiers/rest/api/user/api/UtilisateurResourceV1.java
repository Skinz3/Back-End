package fr.tezea.chantiers.rest.api.user.api;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.tezea.chantiers.service.dto.user.UtilisateurDTO;


@RequestMapping("/api/v1/utilisateur")
public interface UtilisateurResourceV1 {

	@GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<UtilisateurDTO> getUtilisateurById(@PathVariable("id") long id);

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<URI> addUtilisateur(@RequestBody UtilisateurDTO utilisateurDTO);

    @PutMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<UtilisateurDTO> updateUtilisateurById(@PathVariable("id") long id, @RequestBody UtilisateurDTO utilisateurDTO);

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteUtilisateurById(@PathVariable("id") long id);
}
