package fr.tezea.chantiers.domain.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import fr.tezea.chantiers.domain.site.Site;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document
public class Utilisateur
{
    @Transient
    public static final String SEQUENCE_NAME = "site_sequence";
    @Id
    private long id;
    private String username;
    private String password;
    private RoleType role;
}
