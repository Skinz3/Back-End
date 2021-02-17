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

import fr.tezea.chantiers.domain.chantier.Chantier;
import fr.tezea.chantiers.repository.chantier.ChantierRepository;
import fr.tezea.chantiers.repository.chantier.MediaRepository;
import fr.tezea.chantiers.repository.chantier.ProblemeRepository;
import fr.tezea.chantiers.repository.chantier.RapportChantierRegulierRepository;
import fr.tezea.chantiers.repository.client.ClientRepository;
import fr.tezea.chantiers.repository.site.SiteRepository;
import fr.tezea.chantiers.service.dto.chantier.ChantierDTO;
import fr.tezea.chantiers.service.dto.chantier.ChantierGetDTO;
import fr.tezea.chantiers.service.mapper.chantier.ChantierMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ChantierService
{
    private final ChantierMapper chantierMapper;
    private final ChantierRepository chantierRepository;
    private final SiteRepository siteRepository;
    private final ClientRepository clientRepository;
    private final ProblemeRepository problemeRepository;
    private final MediaRepository mediaRepository;
    private final RapportChantierRegulierRepository rapportChantierRegulierRepository;
    private final SequenceGeneratorService sequenceGenerator;

    public ChantierService(ChantierMapper chantierMapper, ChantierRepository chantierRepository,
            SiteRepository siteRepository, ClientRepository clientRepository,
            SequenceGeneratorService sequenceGenerator, ProblemeRepository problemeRepository,
            MediaRepository mediaRepository, RapportChantierRegulierRepository rapportChantierRegulierRepository)
    {
        super();
        this.chantierMapper = chantierMapper;
        this.chantierRepository = chantierRepository;
        this.siteRepository = siteRepository;
        this.clientRepository = clientRepository;
        this.problemeRepository = problemeRepository;
        this.mediaRepository = mediaRepository;
        this.rapportChantierRegulierRepository = rapportChantierRegulierRepository;
        this.sequenceGenerator = sequenceGenerator;
    }

    public ChantierGetDTO getChantierById(long id)
    {
        Optional<Chantier> chantier = this.chantierRepository.findById(id);

        if (chantier.isPresent())
        {
            return this.chantierMapper.toChantierGetDTO(chantier.get());
        }
        return new ChantierGetDTO();
    }

    public List<ChantierGetDTO> getAllChantier()
    {
        List<Chantier> chantier = this.chantierRepository.findAll();

        if (!chantier.isEmpty())
        {
            return this.chantierMapper.toChantierGetDTO(chantier);
        }
        return new ArrayList<>();
    }

    public long addChantier(ChantierDTO chantierDTO)
    {
        Chantier chantier = this.chantierMapper.toChantier(chantierDTO, this.clientRepository, null,
                this.siteRepository, null, this.problemeRepository, this.mediaRepository,
                this.rapportChantierRegulierRepository);
        chantier.setId(sequenceGenerator.generateSequence(Chantier.SEQUENCE_NAME));
        return this.chantierRepository.save(chantier).getId();
    }

    public ChantierDTO updateChantierById(long id, ChantierDTO chantierDTO)
    {
        Optional<Chantier> chantier = this.chantierRepository.findById(id);

        if (chantier.isPresent())
        {
            return this.chantierMapper.toChantierDTO(chantierRepository
                    .save(this.chantierMapper.updateChantierFromDTO(chantierDTO, chantier.get(), this.clientRepository,
                            chantier.get().getClient(), this.siteRepository, chantier.get().getSite(),
                            this.problemeRepository, this.mediaRepository, rapportChantierRegulierRepository)));
        }
        return new ChantierDTO();
    }

    public void deleteChantierById(long id)
    {
        Optional<Chantier> chantier = this.chantierRepository.findById(id);

        if (chantier.isPresent())
        {
            this.chantierRepository.delete(chantier.get());
        }
    }
}
