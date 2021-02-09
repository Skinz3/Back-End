package fr.tezea.chantiers.repository.chantier;

import fr.tezea.chantiers.domain.chantier.DemandeDeChantier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeDeChantierRepository extends MongoRepository<DemandeDeChantier, Long>
{
}
