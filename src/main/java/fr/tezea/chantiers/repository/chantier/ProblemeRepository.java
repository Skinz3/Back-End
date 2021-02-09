package fr.tezea.chantiers.repository.chantier;

import fr.tezea.chantiers.domain.chantier.Probleme;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemeRepository extends MongoRepository<Probleme, Long>
{
}

