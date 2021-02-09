package fr.tezea.chantiers.domain.client;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Client {
    @Id
    private long id;
    private String n;
    private String prenom;
    private String adresse;
    private String mail;
    private String infoComplementaire;
}
