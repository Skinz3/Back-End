package fr.tezea.chantiers.domain.chantier;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@Document
public class Probleme {
    @Id
    private long id;
    @Transient
    public static final String SEQUENCE_NAME = "probleme_sequence";
    private String description;
    private List<String> imageUrl;
}
