package fr.tezea.chantiers.domain.client;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Client {
    @Id
    private long id;
    @Transient
    public static final String SEQUENCE_NAME = "client_sequence";
    private String n;
    private String prenom;
    private String adresse;
    private String mail;
    private String infoComplementaire;
}
