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

import fr.tezea.chantiers.rest.api.chantier.api.RapportChantierRegulierRessourceV1;
import fr.tezea.chantiers.service.RapportChantierRegulierService;
import fr.tezea.chantiers.service.dto.chantier.RapportChantierRegulierDTO;
import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RapportChantierRegulierImplV1 implements RapportChantierRegulierRessourceV1
{
    private final RapportChantierRegulierService rapportChantierRegulierService;

    public RapportChantierRegulierImplV1(RapportChantierRegulierService rapportChantierRegulierService)
    {
        super();
        this.rapportChantierRegulierService = rapportChantierRegulierService;
    }

    @Override
    public ResponseEntity<RapportChantierRegulierDTO> getRapportChantierRegulierById(long id)
    {
        return ResponseEntity.ok(this.rapportChantierRegulierService.getRapportChantierRegulierById(id));
    }

    @Override
    public ResponseEntity<List<RapportChantierRegulierDTO>> getAllRapportChantierRegulier()
    {
        return ResponseEntity.ok(this.rapportChantierRegulierService.getAllRapportChantierRegulier());
    }

    @Override
    public ResponseEntity<URI> addRapportChantierRegulier(RapportChantierRegulierDTO rapportChantierRegulierDTO)
    {
        URI location = URI.create(String.format("/get/%s",
                this.rapportChantierRegulierService.addRapportChantierRegulier(rapportChantierRegulierDTO)));
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<RapportChantierRegulierDTO> updateRapportChantierRegulierById(long id,
            RapportChantierRegulierDTO rapportChantierRegulierDTO)
    {
        return ResponseEntity.ok(
                this.rapportChantierRegulierService.updateRapportChantierRegulierById(id, rapportChantierRegulierDTO));
    }

    @Override
    public ResponseEntity<Void> deleteRapportChantierRegulierById(long id)
    {
        this.rapportChantierRegulierService.deleteRapportChantierRegulierById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
