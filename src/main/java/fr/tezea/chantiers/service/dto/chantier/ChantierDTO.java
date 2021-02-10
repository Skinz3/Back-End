package fr.tezea.chantiers.service.dto.chantier;

import fr.tezea.chantiers.domain.chantier.StatusType;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ChantierDTO {
    private long id;
    private long site;
    private long client;
    private List<ProblemeDTO> problemes;
    private List<MediaDTO> documents;
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
