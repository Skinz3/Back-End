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
import fr.tezea.chantiers.repository.client.ClientRepository;
import fr.tezea.chantiers.repository.site.SiteRepository;
import fr.tezea.chantiers.service.dto.chantier.DemandeDeChantierDTO;
import fr.tezea.chantiers.service.dto.chantier.DemandeDeChantierGetDTO;
import fr.tezea.chantiers.service.mapper.chantier.DemandeDeChantierMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DemandeDeChantierService
{
    private final DemandeDeChantierMapper demandeDeChantierMapper;
    private final DemandeDeChantierRepository demandeDeChantierRepository;
    private final SiteRepository siteRepository;
    private final ClientRepository clientRepository;
    private final SequenceGeneratorService sequenceGenerator;

    public DemandeDeChantierService(DemandeDeChantierMapper demandeDeChantierMapper,
            DemandeDeChantierRepository demandeDeChantierRepository, SiteRepository siteRepository,
            ClientRepository clientRepository, SequenceGeneratorService sequenceGenerator)
    {
        super();
        this.demandeDeChantierMapper = demandeDeChantierMapper;
        this.demandeDeChantierRepository = demandeDeChantierRepository;
        this.siteRepository = siteRepository;
        this.clientRepository = clientRepository;
        this.sequenceGenerator = sequenceGenerator;
    }

    public DemandeDeChantierGetDTO getDemandeDeChantierById(long id)
    {
        Optional<DemandeDeChantier> demandeDeChantier = this.demandeDeChantierRepository.findById(id);

        if (demandeDeChantier.isPresent())
        {
            return this.demandeDeChantierMapper.toDemandeDeChantierGetDTO(demandeDeChantier.get());
        }
        return new DemandeDeChantierGetDTO();
    }

    public List<DemandeDeChantierGetDTO> getAllDemandeDeChantier()
    {
        List<DemandeDeChantier> demandeDeChantier = this.demandeDeChantierRepository.findAll();

        if (!demandeDeChantier.isEmpty())
        {
            return this.demandeDeChantierMapper.toDemandeDeChantierGetDTO(demandeDeChantier);
        }
        return new ArrayList<>();
    }

    public long addDemandeDeChantier(DemandeDeChantierDTO demandeDeChantierDTO)
    {
        DemandeDeChantier demandeDeChantier = this.demandeDeChantierMapper.toDemandeDeChantier(demandeDeChantierDTO,
                this.clientRepository, this.siteRepository);
        demandeDeChantier.setId(sequenceGenerator.generateSequence(DemandeDeChantier.SEQUENCE_NAME));
        return this.demandeDeChantierRepository.save(demandeDeChantier).getId();
    }

    public DemandeDeChantierDTO updateDemandeDeChantierById(long id, DemandeDeChantierDTO demandeDeChantierDTO)
    {
        Optional<DemandeDeChantier> demandeDeChantier = this.demandeDeChantierRepository.findById(id);

        if (demandeDeChantier.isPresent())
        {
            return this.demandeDeChantierMapper.toDemandeDeChantierDTO(demandeDeChantierRepository
                    .save(this.demandeDeChantierMapper.updateDemandeDeChantierFromDTO(demandeDeChantierDTO,
                            demandeDeChantier.get(), this.clientRepository, this.siteRepository)));
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
