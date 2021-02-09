package fr.tezea.chantiers.domain.site;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Site {
    @Id
    private long id;
    @Transient
    public static final String SEQUENCE_NAME = "site_sequence";
    private String nomSite;
    private String nomChef;
    private String prenomChef;
    private String adresse;
    private String mail;
    private String telephone;
}
