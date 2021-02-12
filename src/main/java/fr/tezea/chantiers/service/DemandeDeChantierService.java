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

import fr.tezea.chantiers.domain.chantier.DemandeDeChantier;
import fr.tezea.chantiers.repository.chantier.DemandeDeChantierRepository;
import fr.tezea.chantiers.service.dto.chantier.DemandeDeChantierDTO;
import fr.tezea.chantiers.service.mapper.chantier.DemandeDeChantierMapper;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DemandeDeChantierService
{
    private final DemandeDeChantierMapper demandeDeChantierMapper;
    private final DemandeDeChantierRepository demandeDeChantierRepository;
    private final SequenceGeneratorService sequenceGenerator;

    public DemandeDeChantierService(DemandeDeChantierMapper demandeDeChantierMapper,
            DemandeDeChantierRepository demandeDeChantierRepository, SequenceGeneratorService sequenceGenerator)
    {
        super();
        this.demandeDeChantierMapper = demandeDeChantierMapper;
        this.demandeDeChantierRepository = demandeDeChantierRepository;
        this.sequenceGenerator = sequenceGenerator;
    }

    public DemandeDeChantierDTO getDemandeDeChantierById(long id)
    {
        Optional<DemandeDeChantier> demandeDeChantier = this.demandeDeChantierRepository.findById(id);

        if (demandeDeChantier.isPresent())
        {
            return this.demandeDeChantierMapper.toDemandeDeChantierDTO(demandeDeChantier.get());
        }
        return new DemandeDeChantierDTO();
    }

    public long addDemandeDeChantier(DemandeDeChantierDTO demandeDeChantierDTO)
    {
        DemandeDeChantier demandeDeChantier = this.demandeDeChantierMapper.toDemandeDeChantier(demandeDeChantierDTO);
        demandeDeChantier.setId(sequenceGenerator.generateSequence(DemandeDeChantier.SEQUENCE_NAME));
        return this.demandeDeChantierRepository.save(demandeDeChantier).getId();
    }

    public DemandeDeChantierDTO updateDemandeDeChantierById(long id, DemandeDeChantierDTO demandeDeChantierDTO)
    {
        Optional<DemandeDeChantier> demandeDeChantier = this.demandeDeChantierRepository.findById(id);

        if (demandeDeChantier.isPresent())
        {
            return this.demandeDeChantierMapper
                    .toDemandeDeChantierDTO(demandeDeChantierRepository.save(this.demandeDeChantierMapper
                            .updateDemandeDeChantierFromDTO(demandeDeChantierDTO, demandeDeChantier.get())));
        }
        return new DemandeDeChantierDTO();
    }

    public void deleteDemandeDeChantierById(long id)
    {
        Optional<DemandeDeChantier> demandeDeChantier = this.demandeDeChantierRepository.findById(id);

        if (demandeDeChantier.isPresent())
        {
            this.demandeDeChantierRepository.deleteById(id);
        }
    }
}
