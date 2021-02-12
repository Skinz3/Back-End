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
import fr.tezea.chantiers.service.dto.chantier.ChantierDTO;
import fr.tezea.chantiers.service.mapper.chantier.ChantierMapper;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ChantierService
{
    private final ChantierMapper chantierMapper;
    private final ChantierRepository chantierRepository;
    private final SequenceGeneratorService sequenceGenerator;

    public ChantierService(ChantierMapper chantierMapper, ChantierRepository chantierRepository,
            SequenceGeneratorService sequenceGenerator)
    {
        super();
        this.chantierMapper = chantierMapper;
        this.chantierRepository = chantierRepository;
        this.sequenceGenerator = sequenceGenerator;
    }

    public ChantierDTO getChantierById(long id)
    {
        Optional<Chantier> chantier = this.chantierRepository.findById(id);

        if (chantier.isPresent())
        {
            return this.chantierMapper.toChantierDTO(chantier.get());
        }
        return new ChantierDTO();
    }

    public long addChantier(ChantierDTO chantierDTO)
    {
        Chantier chantier = this.chantierMapper.toChantier(chantierDTO);
        chantier.setId(sequenceGenerator.generateSequence(Chantier.SEQUENCE_NAME));
        return this.chantierRepository.save(chantier).getId();
    }

    public ChantierDTO updateChantierById(long id, ChantierDTO chantierDTO)
    {
        Optional<Chantier> chantier = this.chantierRepository.findById(id);

        if (chantier.isPresent())
        {
            return this.chantierMapper.toChantierDTO(
                    chantierRepository.save(this.chantierMapper.updateChantierFromDTO(chantierDTO, chantier.get())));
        }
        return new ChantierDTO();
    }

    public void deleteChantierById(long id)
    {
        Optional<Chantier> chantier = this.chantierRepository.findById(id);

        if (chantier.isPresent())
        {
            this.chantierRepository.deleteById(id);
        }
    }
}
