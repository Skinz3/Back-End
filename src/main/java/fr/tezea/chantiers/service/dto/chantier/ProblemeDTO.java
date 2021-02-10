package fr.tezea.chantiers.service.dto.chantier;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
public class ProblemeDTO {
    @Id
    private long id;
    private String description;
    private List<String> imageUrl;
}
