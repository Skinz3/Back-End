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
package fr.tezea.chantiers.service;

import fr.tezea.chantiers.domain.school.Professeur;
import fr.tezea.chantiers.repository.school.ProfesseurRepository;
import fr.tezea.chantiers.repository.school.StudentRepository;
import fr.tezea.chantiers.service.dto.school.ProfesseurDTO;
import fr.tezea.chantiers.service.mapper.school.ProfesseurMapper;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProfesseurService
{
    private final ProfesseurMapper professeurMapper;
    private final ProfesseurRepository professeurRepository;
    private final StudentRepository studentRepository;
    private final SequenceGeneratorService sequenceGenerator;

    public ProfesseurService(ProfesseurMapper professeurMapper, ProfesseurRepository professeurRepository,
            StudentRepository studentRepository, SequenceGeneratorService sequenceGenerator)
    {
        super();
        this.professeurMapper = professeurMapper;
        this.professeurRepository = professeurRepository;
        this.studentRepository = studentRepository;
        this.sequenceGenerator = sequenceGenerator;
    }

    public ProfesseurDTO getProfesseurById(long id)
    {
        Optional<Professeur> professeur = this.professeurRepository.findById(id);

        if (professeur.isPresent())
        {
            return this.professeurMapper.toProfesseurDTO(professeur.get());
        }
        return new ProfesseurDTO();
    }

    public long addProfesseur(ProfesseurDTO professeurDTO)
    {
        Professeur professeur = this.professeurMapper.toProfesseur(professeurDTO);
        professeur.setId(sequenceGenerator.generateSequence(Professeur.SEQUENCE_NAME));
        return this.professeurRepository.save(professeur).getId();
    }

    public ProfesseurDTO updateProfesseurById(long id, ProfesseurDTO professeurDTO)
    {
        Optional<Professeur> professeur = this.professeurRepository.findById(id);

        if (professeur.isPresent())
        {
            return this.professeurMapper.toProfesseurDTO(professeurRepository
                    .save(this.professeurMapper.updateProfesseurFromDTO(professeurDTO, professeur.get())));
        }
        return new ProfesseurDTO();
    }

    public void deleteProfesseurById(long id)
    {
        Optional<Professeur> professeur = this.professeurRepository.findById(id);

        if (professeur.isPresent())
        {
            this.professeurRepository.deleteById(id);
        }
    }
}
