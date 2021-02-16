package fr.tezea.chantiers.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.tezea.chantiers.domain.user.Utilisateur;
import fr.tezea.chantiers.repository.user.UtilisateurRepository;
import fr.tezea.chantiers.service.dto.user.UtilisateurDTO;
import fr.tezea.chantiers.service.mapper.user.UtilisateurMapper;

@Service
public class UtilisateurService {
	
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
            return this.utilisateurMapper
                    .toUtilisateurDTO(utilisateurRepository.save(this.utilisateurMapper.updateUtilisateurFromDTO(utilisateurDTO, utilisateur.get())));
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
