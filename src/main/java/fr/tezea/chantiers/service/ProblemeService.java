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

import fr.tezea.chantiers.domain.chantier.Probleme;
import fr.tezea.chantiers.repository.chantier.ProblemeRepository;
import fr.tezea.chantiers.service.dto.chantier.ProblemeDTO;
import fr.tezea.chantiers.service.mapper.chantier.ProblemeMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProblemeService
{
    private final ProblemeMapper problemeMapper;
    private final ProblemeRepository problemeRepository;
    private final SequenceGeneratorService sequenceGenerator;

    public ProblemeService(ProblemeMapper problemeMapper, ProblemeRepository problemeRepository,
            SequenceGeneratorService sequenceGenerator)
    {
        super();
        this.problemeMapper = problemeMapper;
        this.problemeRepository = problemeRepository;
        this.sequenceGenerator = sequenceGenerator;
    }

    public ProblemeDTO getProblemeById(long id)
    {
        Optional<Probleme> probleme = this.problemeRepository.findById(id);

        if (probleme.isPresent())
        {
            return this.problemeMapper.toProblemeDTO(probleme.get());
        }
        return new ProblemeDTO();
    }

    public List<ProblemeDTO> getAllProbleme()
    {
        List<Probleme> probleme = this.problemeRepository.findAll();

        if (!probleme.isEmpty())
        {
            return this.problemeMapper.toProblemeDTO(probleme);
        }
        return new ArrayList<>();
    }

    public long addProbleme(ProblemeDTO problemeDTO)
    {
        Probleme probleme = this.problemeMapper.toProbleme(problemeDTO);
        probleme.setId(sequenceGenerator.generateSequence(Probleme.SEQUENCE_NAME));
        return this.problemeRepository.save(probleme).getId();
    }

    public ProblemeDTO updateProblemeById(long id, ProblemeDTO problemeDTO)
    {
        Optional<Probleme> probleme = this.problemeRepository.findById(id);

        if (probleme.isPresent())
        {
            return this.problemeMapper.toProblemeDTO(
                    problemeRepository.save(this.problemeMapper.updateProblemeFromDTO(problemeDTO, probleme.get())));
        }
        return new ProblemeDTO();
    }

    public void deleteProblemeById(long id)
    {
        Optional<Probleme> probleme = this.problemeRepository.findById(id);

        if (probleme.isPresent())
        {
            this.problemeRepository.delete(probleme.get());
        }
    }
}
