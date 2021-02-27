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

import fr.tezea.chantiers.domain.chantier.RapportChantierRegulier;
import fr.tezea.chantiers.repository.chantier.RapportChantierRegulierRepository;
import fr.tezea.chantiers.service.dto.chantier.RapportChantierRegulierDTO;
import fr.tezea.chantiers.service.mapper.chantier.RapportChantierRegulierMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class RapportChantierRegulierService
{
    private final RapportChantierRegulierMapper rapportChantierRegulierMapper;
    private final RapportChantierRegulierRepository rapportChantierRegulierRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    public RapportChantierRegulierService(RapportChantierRegulierMapper rapportChantierRegulierMapper,
            RapportChantierRegulierRepository rapportChantierRegulierRepository,
            SequenceGeneratorService sequenceGeneratorService)
    {
        super();
        this.rapportChantierRegulierMapper = rapportChantierRegulierMapper;
        this.rapportChantierRegulierRepository = rapportChantierRegulierRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    public RapportChantierRegulierDTO getRapportChantierRegulierById(long id)
    {
        Optional<RapportChantierRegulier> rapportChantierRegulier = this.rapportChantierRegulierRepository.findById(id);

        if (rapportChantierRegulier.isPresent())
        {
            return this.rapportChantierRegulierMapper.toRapportChantierRegulierDTO(rapportChantierRegulier.get());
        }
        return new RapportChantierRegulierDTO();
    }

    public List<RapportChantierRegulierDTO> getAllRapportChantierRegulier()
    {
        List<RapportChantierRegulier> rapportChantierRegulier = this.rapportChantierRegulierRepository.findAll();

        if (!rapportChantierRegulier.isEmpty())
        {
            return this.rapportChantierRegulierMapper.toRapportChantierRegulierDTO(rapportChantierRegulier);
        }
        return new ArrayList<>();
    }

    public long addRapportChantierRegulier(RapportChantierRegulierDTO rapportChantierRegulierDTO)
    {
        RapportChantierRegulier rapportChantierRegulier = this.rapportChantierRegulierMapper
                .toRapportChantierRegulier(rapportChantierRegulierDTO);
        rapportChantierRegulier
                .setId(this.sequenceGeneratorService.generateSequence(RapportChantierRegulier.SEQUENCE_NAME));
        return this.rapportChantierRegulierRepository.save(rapportChantierRegulier).getId();
    }

    public RapportChantierRegulierDTO updateRapportChantierRegulierById(long id,
            RapportChantierRegulierDTO rapportChantierRegulierDTO)
    {
        Optional<RapportChantierRegulier> rapportChantierRegulier = this.rapportChantierRegulierRepository.findById(id);

        if (rapportChantierRegulier.isPresent())
        {
            return this.rapportChantierRegulierMapper
                    .toRapportChantierRegulierDTO(this.rapportChantierRegulierRepository
                            .save(this.rapportChantierRegulierMapper.updateRapportChantierRegulierFromDTO(
                                    rapportChantierRegulierDTO, rapportChantierRegulier.get())));
        }
        return new RapportChantierRegulierDTO();
    }

    public void deleteRapportChantierRegulierById(long id)
    {
        Optional<RapportChantierRegulier> rapportChantierRegulier = this.rapportChantierRegulierRepository.findById(id);

        if (rapportChantierRegulier.isPresent())
        {
            this.rapportChantierRegulierRepository.delete(rapportChantierRegulier.get());
        }
    }
}
