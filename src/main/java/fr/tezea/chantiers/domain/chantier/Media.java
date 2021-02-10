package fr.tezea.chantiers.domain.chantier;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Media {
    @Id
    private long id;
    @Transient
    public static final String SEQUENCE_NAME = "document_sequence";
    private String nomDocument;
    private List<String> imagesUrl;
    private DocumentType documentType;
}
