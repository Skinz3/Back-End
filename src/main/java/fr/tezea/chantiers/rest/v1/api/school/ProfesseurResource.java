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
package fr.tezea.chantiers.rest.v1.api.school;

import fr.tezea.chantiers.service.ProfesseurService;
import fr.tezea.chantiers.service.dto.school.ProfesseurDTO;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/professeur")
public class ProfesseurResource
{
    private final ProfesseurService professeurService;

    public ProfesseurResource(ProfesseurService professeurService)
    {
        super();
        this.professeurService = professeurService;
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<ProfesseurDTO> getProfesseurById(@PathVariable("id") long id)
    {
        return ResponseEntity.ok(this.professeurService.getProfesseurById(id));
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<URI> addProfesseur(@RequestBody ProfesseurDTO professeurDTO)
    {
        URI location = URI.create(String.format("/get/%s", this.professeurService.addProfesseur(professeurDTO)));
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<ProfesseurDTO> updateProfesseurById(@PathVariable("id") long id,
            @RequestBody ProfesseurDTO professeurDTO)
    {
        return ResponseEntity.ok(this.professeurService.updateProfesseurById(id, professeurDTO));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteProfesseurById(@PathVariable("id") long id)
    {
        this.professeurService.deleteProfesseurById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
