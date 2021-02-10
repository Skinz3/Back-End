package fr.tezea.chantiers.service.dto.chantier;

import fr.tezea.chantiers.domain.chantier.DocumentType;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.annotation.Transient;

import java.util.List;

@Data
public class MediaDTO {
    @Id
    private long id;
    private String nomDocument;
    private List<String> imagesUrl;
    private DocumentType documentType;
}
