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

import fr.tezea.chantiers.domain.user.Utilisateur;
import fr.tezea.chantiers.repository.user.UtilisateurRepository;
import fr.tezea.chantiers.service.dto.user.UtilisateurDTO;
import fr.tezea.chantiers.service.mapper.user.UtilisateurMapper;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService
{
    private final UtilisateurMapper utilisateurMapper;
    private final UtilisateurRepository utilisateurRepository;
    private final SequenceGeneratorService sequenceGenerator;

    public UtilisateurService(UtilisateurMapper utilisateurMapper, UtilisateurRepository utilisateurRepository,
            SequenceGeneratorService sequenceGenerator)
    {
        super();
        this.utilisateurMapper = utilisateurMapper;
        this.utilisateurRepository = utilisateurRepository;
        this.sequenceGenerator = sequenceGenerator;
    }

    public UtilisateurDTO getUtilisateurById(long id)
    {
        Optional<Utilisateur> utilisateur = this.utilisateurRepository.findById(id);

        if (utilisateur.isPresent())
        {
            return this.utilisateurMapper.toUtilisateurDTO(utilisateur.get());
        }
        return new UtilisateurDTO();
    }

    public UtilisateurDTO getUtilisateurByUsername(String username)
    {
        Optional<Utilisateur> utilisateur = this.utilisateurRepository.findByUsername(username);

        if (utilisateur.isPresent())
        {
            return this.utilisateurMapper.toUtilisateurDTO(utilisateur.get());
        }
        return new UtilisateurDTO();
    }

    public long addUtilisateur(UtilisateurDTO utilisateurDTO)
    {
        Utilisateur utilisateur = this.utilisateurMapper.toUtilisateur(utilisateurDTO);
        utilisateur.setId(sequenceGenerator.generateSequence(Utilisateur.SEQUENCE_NAME));
        return this.utilisateurRepository.save(utilisateur).getId();
    }

    public UtilisateurDTO updateUtilisateurById(long id, UtilisateurDTO utilisateurDTO)
    {
        Optional<Utilisateur> utilisateur = this.utilisateurRepository.findById(id);

        if (utilisateur.isPresent())
        {
            return this.utilisateurMapper.toUtilisateurDTO(utilisateurRepository
                    .save(this.utilisateurMapper.updateUtilisateurFromDTO(utilisateurDTO, utilisateur.get())));
        }
        return new UtilisateurDTO();
    }

    public void deleteUtilisateurById(long id)
    {
        Optional<Utilisateur> utilisateur = this.utilisateurRepository.findById(id);

        if (utilisateur.isPresent())
        {
            this.utilisateurRepository.deleteById(id);
        }
    }
}
