package fr.tezea.chantiers.domain.chantier;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;

import java.util.List;

@Data
@org.springframework.data.mongodb.core.mapping.Document
public class Document {
    @Id
    private long id;
    private String nomDocument;
    private List<String> imagesUrl;
    private DocumentType documentType;
}
