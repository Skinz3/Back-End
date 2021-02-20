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
package fr.tezea.chantiers.rest.api.user.impl;

import fr.tezea.chantiers.domain.user.RoleType;
import fr.tezea.chantiers.repository.user.UtilisateurRepository;
import fr.tezea.chantiers.rest.api.user.api.UtilisateurResourceV1;
import fr.tezea.chantiers.service.UtilisateurService;
import fr.tezea.chantiers.service.dto.user.UtilisateurDTO;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilisateurResourceImplV1 implements UtilisateurResourceV1
{
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
    public ResponseEntity<UtilisateurDTO> updateUtilisateurById(@PathVariable("id") long id,
            @RequestBody UtilisateurDTO utilisateurDTO)
    {
        return ResponseEntity.ok(this.utilisateurService.updateUtilisateurById(id, utilisateurDTO));
    }

    @Override
    public ResponseEntity<Void> deleteUtilisateurById(@PathVariable("id") long id)
    {
        this.utilisateurService.deleteUtilisateurById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

	@Override
	public ResponseEntity<UtilisateurDTO> getAuthentifiedUser() {
		return ResponseEntity.ok(this.utilisateurService.getUtilisateurByUsername(
				SecurityContextHolder.getContext().getAuthentication().getName()));
	}
}
