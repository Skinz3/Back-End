package fr.tezea.chantiers.domain.chantier;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document
public class Chantier {
    @Id
    private long id;
    @Transient
    public static final String SEQUENCE_NAME = "chantier_sequence";
    private long site;
    private long client;
    private List<Long> problemes;
    private List<Long> documents;
    private String adresse;
    private List<String> ouvriers;
    private String materiel;
    private Date dateDebut;
    private Date dateFin;
    private Date heureDemarrage;
    private int estimationTemps;
    private String telephone;
    private StatusType statusChantier;
    private String nomChantier;
    private String informationsInterne;
    private String description;
}
