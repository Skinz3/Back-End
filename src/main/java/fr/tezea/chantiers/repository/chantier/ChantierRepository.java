package fr.tezea.chantiers.repository.chantier;

import fr.tezea.chantiers.domain.chantier.Chantier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChantierRepository extends MongoRepository<Chantier, Long>
{
}
