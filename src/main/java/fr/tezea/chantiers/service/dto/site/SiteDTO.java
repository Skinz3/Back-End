package fr.tezea.chantiers.service.dto.site;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class SiteDTO {
    @Id
    private long id;
    private String nomSite;
    private String nomChef;
    private String prenomChef;
    private String adresse;
    private String mail;
    private String telephone;
}
