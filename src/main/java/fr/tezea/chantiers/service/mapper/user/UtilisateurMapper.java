package fr.tezea.chantiers.service.mapper.user;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import fr.tezea.chantiers.domain.user.Utilisateur;
import fr.tezea.chantiers.service.dto.user.UtilisateurDTO;



@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UtilisateurMapper {
	
	@Mapping(target = "id", ignore = true)
    Utilisateur toUtilisateur(UtilisateurDTO utilisateurDTO);

    @InheritInverseConfiguration(name = "toUtilisateur")
    UtilisateurDTO toUtilisateurDTO(Utilisateur utilisateur);

    List<UtilisateurDTO> toUtilisateurDTO(List<Utilisateur> utilisateurs);

    @Mapping(target = "id", ignore = true)
    Utilisateur updateUtilisateurFromDTO(UtilisateurDTO utilisateurDTO, @MappingTarget Utilisateur utilisateur);

}
