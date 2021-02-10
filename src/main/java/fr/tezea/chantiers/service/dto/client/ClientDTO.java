package fr.tezea.chantiers.service.dto.client;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class ClientDTO {
    @Id
    private long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String mail;
    private String infoComplementaire;
}
