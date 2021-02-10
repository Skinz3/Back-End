package fr.tezea.chantiers.service.dto.chantier;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class DemandeDeChantier {
    @Id
    private long id;
    private long site;
    private long client;
    private String employee;
    private String material;
    private String adresse;
    private String regularite;
    private int estimationTemps;
    private String particularite;
    private String description;
    private String infoInterne;
}

