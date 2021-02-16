package fr.tezea.chantiers.service.dto.user;


import fr.tezea.chantiers.domain.user.RoleType;
import lombok.Data;

@Data
public class UtilisateurDTO {
	private long id;
    private String username;
    private RoleType role;
}
